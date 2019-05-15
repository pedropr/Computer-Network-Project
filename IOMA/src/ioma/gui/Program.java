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
            byte[] buf = new byte[256];
            while (frame.isVisible()) {
                System.out.println("Waiting message ... ");
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData());
                System.out.println("Got Message from: IP address: " + packet.getAddress() + "Port: " + packet.getPort());
                if (received.contains("A:")) { // This to add a user.
                    System.out.println("Adding user: " + received + " with ip: " + packet.getAddress());

                    //Here goes parse
                    frame.addUser(received, packet.getAddress().toString());
                } else if (received.contains("R: ")) { //This to remove a user
                    System.out.println("Removing user:" + received + " with ip: " + packet.getAddress());
                    //Here goes parse

                    frame.removeUser(received, packet.getAddress().toString());
                } else { // recieve message
                    System.out.println("Message Receive: " + received);
                    frame.recieveMessage(received, packet.getAddress().toString());

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
