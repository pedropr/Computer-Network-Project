package ioma.gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Program {
    public static void main(String args []) throws IOException {
        DatagramSocket socket = new DatagramSocket(50000);
        iomaGUI frame = new iomaGUI(socket);
        frame.setVisible(true);

        try {
            System.out.println("this running");

            while (frame.isVisible()) {
                byte[] buf = new byte[256];
                System.out.println("Waiting message ... ");
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData());
                System.out.println("Got Message. Message contains: " + received);
                frame.setServerIP(packet.getAddress().getHostAddress());
                if (received.contains("A:")) {// This to add a user.
                    //here goes parser
                    System.out.println("***ADDING****");

                    String[] arrOfStr = received.split("A:");
                    System.out.println(arrOfStr.length);
                    String username = arrOfStr[1];
                    String address = arrOfStr[0].replaceFirst("IP:", "");

                    //System.out.println("Adding Username: " + username + " IP address: " + ip);

                    frame.addUser(username, address);

                } else if (received.contains("R:")) { //This to remove a user
                    //Here goes parser
                    System.out.println("***REMOVING****");

                    String[] arrOfStr = received.split("R:");
                    System.out.println(arrOfStr.length);
                    String username = arrOfStr[1];
                    String address = arrOfStr[0].replaceFirst("IP:", "");
                    frame.removeUser(username, address);
                  
                } else { // recieve message
                    System.out.println("Message Received: " + received);
                    frame.recieveMessage(received, packet.getAddress().getHostAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
