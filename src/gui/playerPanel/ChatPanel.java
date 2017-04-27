package gui.playerPanel;

import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatPanel extends JPanel {
    //JTextArea chatBox;
    ChatBox chatBox;
    JTextField messageBox;
    JButton sendMessage;
    String username;
    ArrayList<Player> players;

    public ChatPanel(ArrayList<Player> players) {

        this.players = players;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(8);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send");
        sendMessage.addActionListener(new sendMessageButtonListener());

//        chatBox = new JTextArea();
//        chatBox.setEditable(false);
//        chatBox.setFont(new Font("Serif", Font.PLAIN, 12));
//        chatBox.setLineWrap(true);
//        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        chatBox = ChatBox.getInstance();
        ChatScrollPane chatScrollPane = ChatScrollPane.getInstance(chatBox);
        mainPanel.add(chatScrollPane, BorderLayout.CENTER);


        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.setPreferredSize(new Dimension(200, 250));
        mainPanel.add(BorderLayout.SOUTH, southPanel);

        this.add(mainPanel);
        //this.setLayout(new BorderLayout());
        //this.setSize(470, 300);
        this.setVisible(true);
        this.setBorder(null);

    }

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
                for (Player player : players) {
                    chatBox.append("<" + username + ">:  " + messageBox.getText() + "\n");
                }
                messageBox.setText("");
                System.out.println(chatBox.getText());
            }
            messageBox.requestFocusInWindow();

        }
    }
}

class ChatScrollPane extends JScrollPane {
    private static ChatScrollPane instance = null;

    private ChatScrollPane(ChatBox chatBox) {
    }

    public static ChatScrollPane getInstance(ChatBox chatBox) {
        if (instance == null) {
            synchronized (ChatScrollPane.class) {
                if (instance == null)
                    instance = createChatScrollPane(chatBox);
            }
        }
        return instance;
    }

    private static ChatScrollPane createChatScrollPane(ChatBox chatBox) {
        instance = new ChatScrollPane(chatBox);
        return instance;
    }
}

class ChatBox extends JTextArea {
    private static ChatBox instance = null;

    private ChatBox() {
    }

    public static ChatBox getInstance() {
        if (instance == null) {
            synchronized (ChatBox.class) {
                if (instance == null) {
                    instance = createChatBox();
                }
            }
        }
        return instance;
    }

    private static ChatBox createChatBox() {
        instance = new ChatBox();
        instance.setEditable(false);
        instance.setFont(new Font("Serif", Font.PLAIN, 12));
        instance.setLineWrap(true);

        return instance;
    }
}

