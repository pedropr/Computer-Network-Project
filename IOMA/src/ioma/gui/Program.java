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
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String recieved = new String(packet.getData());
                System.out.println("Got Message from: IP address: " + packet.getAddress() + "Port: " + packet.getPort());
                if (recieved.contains("A:")) { // This to add a user.
                    System.out.println("Adding user: " + recieved + " with ip: " + packet.getAddress());

                    //Here goes parse
                    frame.addUser(recieved, packet.getAddress().toString());
                } else if (recieved.contains("R: ")){ //This to remove a user
                    System.out.println("Removing user:" + recieved + " with ip: " + packet.getAddress());
                    //Here goes parse

                    frame.removeUser(recieved, packet.getAddress().toString());
                } else { // recieve message
                    frame.recieveMessage(recieved , packet.getAddress().toString());

                }
                System.out.println("Recieved packet: " + recieved);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
