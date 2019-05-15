package ioma.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;


public class iomaGUI extends JFrame {

    private JPanel contentPane;
    private DatagramSocket socket;
    private InetAddress serverIp;
    private ArrayList<Users> userList = new ArrayList<User>(); // Contains all the avaible user
    private Users thisUser = new Users(); //Himself


    /**
     * Create the frame.
     */
    public iomaGUI(DatagramSocket socket) {
        this.socket = socket;
        setTitle("Inter Office Messaging Application v.1");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 747, 751);
        contentPane = new JPanel();
        contentPane.setAutoscrolls(true);
        contentPane.setFont(new Font("Calibri", Font.PLAIN, 17));
        contentPane.setBackground(new Color(0, 139, 139));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(255, 255, 255));
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(new Color(255, 255, 255));
        separator.setBounds(224, 67, 25, 624);
        contentPane.add(separator);

        JLabel lblPeers = new JLabel("Peers");
        lblPeers.setForeground(new Color(255, 255, 255));
        lblPeers.setHorizontalAlignment(SwingConstants.CENTER);
        lblPeers.setFont(new Font("Calibri", Font.BOLD, 27));
        lblPeers.setBounds(12, 13, 200, 34);
        contentPane.add(lblPeers);

        JLabel lblMessageList = new JLabel("Message List");
        lblMessageList.setForeground(new Color(255, 255, 255));
        lblMessageList.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessageList.setFont(new Font("Calibri", Font.BOLD, 27));
        lblMessageList.setBounds(235, 13, 482, 34);
        contentPane.add(lblMessageList);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(235, 559, 482, -497);
        contentPane.add(scrollPane);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(255, 255, 255));
        separator_1.setBounds(12, 52, 705, 10);
        contentPane.add(separator_1);

        JButton btnSend = new JButton("Send");
        btnSend.setBackground(new Color(51, 255, 102));
        btnSend.setBounds(645, 572, 72, 119);
        contentPane.add(btnSend);

        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(new Color(255, 255, 255));
        separator_2.setBounds(235, 555, 482, 2);
        contentPane.add(separator_2);

        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
        messageTextArea.setBounds(235, 572, 398, 119);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        contentPane.add(messageTextArea);

        JTextPane chatAreaTextPane = new JTextPane();
        chatAreaTextPane.setBackground(new Color(176, 224, 230));
        chatAreaTextPane.setBorder(new LineBorder(null));
        chatAreaTextPane.setBounds(235, 67, 482, 475);
        contentPane.add(chatAreaTextPane);

        JTextPane peersTextPane = new JTextPane();
        peersTextPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        peersTextPane.setBounds(12, 67, 200, 475);
        contentPane.add(peersTextPane);

        JButton btnDownload = new JButton("Download all messages");
        btnDownload.setBackground(new Color(255, 204, 51));
        btnDownload.setBounds(12, 657, 200, 34);
        contentPane.add(btnDownload);

        JSeparator separator_3 = new JSeparator();
        separator_3.setForeground(new Color(255, 255, 255));
        separator_3.setBounds(12, 555, 200, 2);
        contentPane.add(separator_3);

        JButton btnStartConnection = new JButton("Disconnect");
        btnStartConnection.setBackground(Color.RED);
        btnStartConnection.setBounds(12, 614, 200, 34);
        contentPane.add(btnStartConnection);

        JSeparator separator_4 = new JSeparator();
        separator_4.setForeground(new Color(255, 255, 255));
        separator_4.setOrientation(SwingConstants.VERTICAL);
        separator_4.setBounds(224, 13, 2, 34);
        contentPane.add(separator_4);

        JButton button = new JButton("Start Connection");
        button.setBackground(Color.GREEN);
        button.setBounds(12, 572, 200, 34);
        contentPane.add(button);
    }
    public void recieveMessage(String message, String ip){
        System.out.println(message + "from: " +ip);
        for (Users user : userList) {
            if (user.getIp().equals(ip)) {
                user.addMessage(user.getName(), message);
                break;
            }
        }

    }


    public void addUser(String username, String ip){

        if (!thisUser.equals(new Users(username, ip))) {
            if (!userList.contains(new Users(username, ip))) {
                userList.add(new Users(username, ip));
                //Add part to add user to list of gui
            }
        } else {
            System.out.println("User: " + username + " already add, ignoring message");
        }
    }

    public void removeUser(String username, String ip){
        if (userList.contains(new Users(username, ip))) {
            userList.remove(new Users(username, ip));
            //Add part to remove user list of the gui

        }

    }

    public void initializedDiscovery(String username) throws Exception {
        DatagramSocket socket = new DatagramSocket(40000);
        byte[] buf = new byte[256];
        Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
        //Preparing Message to server, to add user.
        String message = "A:" + username;
        buf = message.getBytes();
        //Send Message to broadcast
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                continue; // Don't want to broadcast to the loopback interface
            }

            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                InetAddress broadcast = interfaceAddress.getBroadcast();
                if (broadcast == null) {
                    continue;
                }

                // Send the broadcast package!
                try {
                    System.out.println(broadcast);
                    DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, broadcast, 40000);
                    socket.send(sendPacket);

                } catch (Exception e) {

                }
            }
        }

        socket.close();


    }
}
