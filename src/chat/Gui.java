package chat;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gui extends JFrame {

    private static JFrame frame;
    public static JTextField input;
    public static JTextArea messages;
    public static JPanel panel;
    public static JPanel chatBox;
    public static JButton connect;
    public static JButton name;
    public static JColorChooser textColorPicker;
    public static JButton start;
    public static boolean clientMode = false;
    public static boolean serverMode = false;
    public static String port = "";

    public Gui() {
        frame = new JFrame("Console");
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) { }

        messages = new JTextArea();
        messages.setBounds(20,60,540,600);
        messages.setLineWrap(true);
        messages.setEditable(false);
        messages.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        messages.setForeground(new Color(244, 119, 255));

        JScrollPane scrollPane = new JScrollPane(messages);
        scrollPane.setBounds(20,60,540,600);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
        panel.setBackground(new Color(39, 40, 40));

        chatBox = new JPanel();
        chatBox.setBounds(5, 75, 440, 570);
        chatBox.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        chatBox.setBackground(new Color(62, 62, 65));

        input = new JTextField(6);
        input.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        input.setBounds(20,680,300,40);
        input.setBackground(new Color(255, 255, 255));
        input.addKeyListener(new KeyAdapter(){public void keyPressed(KeyEvent ke)
        { if(ke.getKeyCode() == KeyEvent.VK_ENTER){ handleText(); }}});

        connect = new JButton();
        connect.setBounds(0,0,90,40);
        connect.setBackground(new Color(0, 255, 227));
        connect.add(new JLabel("Connect"));
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ports = JOptionPane.showInputDialog("Input the hosts network ID",null);
                port = ports;
                System.out.println(port);
                clientMode = false;
                serverMode = true;
            }
        });

        start = new JButton();
        start.setBounds(80,0,90,40);
        start.setBackground(new Color(0, 255, 37));
        start.add(new JLabel("Start"));
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serverMode = false;
                clientMode = true;
            }
        });

        name = new JButton();
        name.setBounds(160,0,100,40);
        name.setBackground(new Color(0, 255, 37));
        name.add(new JLabel("UserName"));
        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Input your name",null);
                UserSettingsManager.userName = name;
                main.fileManager.save();
            }
        });

        frame.add(scrollPane);
        frame.add(start);
        frame.add(connect);
        frame.add(input);
        frame.add(name);
        frame.add(chatBox);
        frame.add(panel,BorderLayout.NORTH);
        frame.setVisible(true);
    }


    public void handleText() {
        if(!input.getText().equals("")) {
            messages.setText(messages.getText() + "\n" + UserSettingsManager.userName + " - " + input.getText());
        }
        try{
            if(clientMode){
                ClientManager.out.writeUTF(UserSettingsManager.userName + " - " + input.getText());
            }

            if(serverMode){
                ServerManager.out.writeUTF(UserSettingsManager.userName + " - " + input.getText());
            }
        }catch(Exception e){
            System.out.println(e);
        }


        input.setText("");
    }

    public static String addConsoleChat(String s){
        messages.setText(messages.getText() + "\n" + s);
        return s;
    }

    public static void addUserChat(String s){
        messages.setText(messages.getText() + "\n" + s);
    }


}