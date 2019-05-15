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
                System.out.println("IP address: " + packet.getAddress());
                if (recieved.contains("A:")) { // This to add a user.

                } else if (recieved.contains("R: ")){ //This to remove a user

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
