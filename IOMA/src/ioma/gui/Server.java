package ioma.gui;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public static void main(String args []) throws IOException {
        ArrayList<Users> usersList = new ArrayList<Users>();

        DatagramSocket socket = new DatagramSocket (40000);
        socket.setBroadcast(true);

        byte[] buf = new byte[256];
        while (true){
            System.out.println("Waiting for message...");
            DatagramPacket packet = new DatagramPacket (buf, buf.length);
            socket.receive(packet);
            String recieve = new String (packet.getData());

            if(recieve.contains("A: ")){
                System.out.println("Adding " + recieve + " : " + packet.getAddress());

            }else if(recieve.contains("B: ")){
                System.out.println("Removing " + recieve + " : " + packet.getAddress());

            }else{
                System.out.println("Other info");
                System.out.println(recieve);
            }
        }


    }
}
