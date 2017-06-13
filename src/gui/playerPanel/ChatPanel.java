package gui.playerPanel;

import chat.ChatListener;
import chat.ChatSession;
import chat.ChatWindow;
import chat.SimpleMessage;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChatPanel extends JPanel {

    private ChatListener chatListener;
    private JTextField messageBox;
    private JButton sendMessage;
    private ChatSession chatSession;
    private Player player;
    private JScrollPane jScrollPane;

    public ChatPanel(Player player, ChatSession chatSession) {
        this.player = player;
        this.chatSession = chatSession;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(8);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send");
        sendMessage.addActionListener(new sendMessageButtonListener());

        chatListener = new ChatWindow();
        jScrollPane = new JScrollPane((ChatWindow) chatListener);

        mainPanel.add(jScrollPane, BorderLayout.CENTER);


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
        this.setVisible(true);
        this.setBorder(null);

        registerChatWindow();
    }

    private void registerChatWindow() {
        chatSession.register((ChatWindow) chatListener);
    }

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            }else {
                SimpleMessage simpleMessage = new SimpleMessage("<" + player.getName() + ">" + messageBox.getText());
                chatSession.incomingMessage(1, simpleMessage);
                messageBox.setText("");
            }
        }
    }
}


