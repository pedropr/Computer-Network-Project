package ioma.gui;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

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
                String newUser = recieve;
                //Parse goes here



                //Then send user Previos User added
                //This will change to for loop
                String prevUser = "A:Carlos";
                buf = prevUser.getBytes();
                System.out.println("Sending " + prevUser + "User to: " + packet.getAddress());
                DatagramPacket usernamePacket = new DatagramPacket(buf, buf.length, packet.getAddress(), 50000);
                socket.send(usernamePacket);

                //Then send to broadcast new user added to previous user
                Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
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
                            System.out.println("Send the new user added to chat: " + recieve + " to broadcast " + broadcast);
                            buf = recieve.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, broadcast, 50000);
                            socket.send(sendPacket);

                        } catch (Exception e) {

                        }
                    }
                }
            } else if (recieve.contains("B:")) {
                System.out.println("Removing " + recieve + " : " + packet.getAddress());
                //Parse goes here

                //Then send user to remove user to broadcast

                Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
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
                            System.out.println("Send remove user to client: " + recieve + " to broadcast " + broadcast);
                            buf = recieve.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, broadcast, 50000);
                            socket.send(sendPacket);

                        } catch (Exception e) {

                        }
                    }
                }
            }else{
                System.out.print("Other info: ");
                System.out.println(recieve);
            }
        }


    }
}
