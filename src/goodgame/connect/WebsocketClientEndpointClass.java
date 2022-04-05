package goodgame.connect;

import javax.websocket.*;
import java.net.URI;

    @ClientEndpoint
    public class WebsocketClientEndpointClass {

        Session userSession = null;
        public MessageHandler messageHandler;
        public URI uri;

        public WebsocketClientEndpointClass(URI uri) {
            this.uri = uri;
            WebsocketClientEndpoint();
        }

        public void WebsocketClientEndpoint() {
            try {
                URI endpointURI = uri;
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                container.connectToServer(this, endpointURI);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Callback hook for Connection open events.
         *
         * @param userSession the userSession which is opened.
         */
        @OnOpen
        public void onOpen(Session userSession) {
            System.out.println("opening websocket");
            this.userSession = userSession;
            System.out.println("connected");
        }

        /**
         * Callback hook for Connection close events.
         *
         * @param userSession the userSession which is getting closed.
         * @param reason      the reason for connection close
         */
        @OnClose
        public void onClose(Session userSession, CloseReason reason) {
            System.out.println("closing websocket");
            this.userSession = null;
            System.out.println("disconnected");
        }

        /**
         * Callback hook for Message Events. This method will be invoked when a client send a message.
         *
         * @param message The text message
         */
        @OnMessage
        public void onMessage(String message) {
            if (this.messageHandler != null) {
                this.messageHandler.handleMessage(message);
            }
        }

        /**
         * register message handler
         *
         * @param msgHandler
         */
        public void addMessageHandler(MessageHandler msgHandler) {
            this.messageHandler = msgHandler;
        }

        /**
         * Send a message.
         *
         * @param message
         */
        public void sendMessage(String message) {
            this.userSession.getAsyncRemote().sendText(message);
        }

        /**
         * Message handler.
         *
         * @author Jiji_Sasidharan
         */
        public static interface MessageHandler {

            public void handleMessage(String message);
        }
    }
