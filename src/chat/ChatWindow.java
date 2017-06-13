package chat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChatWindow extends JTextArea implements ChatListener{
    private String user;
    private List<Message> messageList;
    private int id;

    public String getUser() {
        return user;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void addMessageToList(Message newMessage) {
        if (this.messageList == null) {
            this.messageList = new ArrayList<>();
        }
        this.messageList.add(newMessage);
    }

    public void notify(Message newMessage) {
        addMessageToList(newMessage);
        printMessage();
    }
    public void printMessage(){
        StringBuilder text = new StringBuilder();
        for (int i = 0; i <this.getMessageList().size(); i++) {
            text.append(this.getMessageList().get(i).getValue()).append("\n");
        }
        this.setText(text.toString());
    }

    public int getId() {
        return id;
    }

}
