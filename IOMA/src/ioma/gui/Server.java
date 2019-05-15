package ioma.gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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

            if (recieve.contains("A:")) {
                System.out.println("New User: " + recieve + " : " + packet.getAddress());
                //Parse goes here


                //Then send user Previos User added
                //This will change to for loop
                String newUser = "A:Carlos";
                buf = newUser.getBytes();
                System.out.println("Sending " + newUser + "User to: " + packet.getAddress());
                DatagramPacket usernamePacket = new DatagramPacket(buf, buf.length, packet.getAddress(), 50000);
                socket.send(usernamePacket);

                //Then send to broadcast new user added to previous user

            } else if (recieve.contains("B:")) {
                System.out.println("Removing " + recieve + " : " + packet.getAddress());
                //Parse goes here

                //Then send user to Added user


                //Then add user to other client



            }else{
                System.out.print("Other info: ");
                System.out.println(recieve);
            }
        }


    }
}
