package chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkrec_000 on 06/06/2017.
 */
public class ChatSession {
    private List<ChatListener> registeredChatListeners;

    public void register(ChatWindow chatWindow) {
        if (registeredChatListeners == null) {
            registeredChatListeners = new ArrayList<ChatListener>();
        }
        registeredChatListeners.add(chatWindow);
    }

    public List<ChatListener> getRegisteredChatListeners() {
        return registeredChatListeners;
    }

    public void setRegisteredChatWindows(List<ChatListener> registeredChatListeners) {
        this.registeredChatListeners = registeredChatListeners;
    }

    public void incomingMessage(int chatListenerId, Message message) {
        publish(message);
    }

    protected void publish(Message messageToPublish) {
        if (registeredChatListeners != null) {
            for (ChatListener eachListener : registeredChatListeners) {
                eachListener.notify(messageToPublish);
            }
        }
    }
}
