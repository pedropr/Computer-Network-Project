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
            System.out.println("Message recieve: " + recieve);


            if (recieve.contains("A:")) {
                System.out.println("New User: " + recieve + " IP: " + packet.getAddress().toString());
                //Parse goes here
                String user = recieve.replaceFirst("A:", "").replace("\n", "").replace("\r", "");
                System.out.println(user);
                String address = packet.getAddress().getHostAddress();
                //Then send added client to new client
                for (Users selectuser : usersList) {
                    String sendPrevUser = "IP:" + selectuser.getIp() + "A:" + selectuser.getName();
                    byte[] sendPrevbuf = sendPrevUser.getBytes();
                    System.out.println("Sending previous user: " + sendPrevUser + " User to: " + selectuser.getIp());
                    DatagramPacket usernamePacket = new DatagramPacket(sendPrevbuf, sendPrevbuf.length, packet.getAddress(), 50000);
                    socket.send(usernamePacket);
                }
                //Add client to list
                usersList.add(new Users(user, address));
                //Then send to broadcast new user added to previous user

                Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
                String sendMessage = "IP:" + address + "A:" + user;
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

                            System.out.println("Send the new user to other client: " + sendMessage + " by broadcast: " + broadcast.toString());
                            byte[] send = sendMessage.getBytes();
                            System.out.println(sendMessage);
                            System.out.println(send.length);
                            System.out.println(sendMessage.length());
                            DatagramPacket sendPacket = new DatagramPacket(send, send.length, broadcast, 50000);
                            socket.send(sendPacket);
                        } catch (Exception e) {
                        }
                    }
                }


            } else if (recieve.contains("R:")) {
                System.out.println("Removing " + recieve + " : " + packet.getAddress());
                //Parse goes here
                String removeUser = recieve.substring(2);

                if (usersList.contains(new Users(removeUser, packet.getAddress().getHostAddress()))) {
                    usersList.remove(new Users(removeUser, packet.getAddress().getHostAddress()));
                    System.out.println("User remove: " + removeUser);
                    String sendMessage = "IP:" + packet.getAddress().getHostAddress() + "R:" + removeUser;
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
                                byte[] sendBuf = sendMessage.getBytes();
                                System.out.println("Send remove user to client: " + sendMessage + " to broadcast " + broadcast);
                                DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, broadcast, 50000);
                                socket.send(sendPacket);

                            } catch (Exception e) {

                            }
                        }
                    }
                } else {
                    System.out.println("User is already remove or is not in the list");
                }
                //Then send user to remove user to broadcast


            }else{
                System.out.print("Other info: ");
                System.out.println(recieve);
            }
            buf = new byte[256];
        }


    }
}
