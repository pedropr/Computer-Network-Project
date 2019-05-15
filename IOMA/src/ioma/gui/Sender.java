import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Enumeration;

public class Sender {
    public static void main (String[] args) throws Exception {
            DatagramSocket socket = new DatagramSocket ();
            byte[] buf = new byte[256];
            byte[] messageBuf = new byte[1024];
            Scanner scanner = new Scanner(System.in);

            //Username
            System.out.println("What is your username? \n");
            String user = scanner.nextLine();
            buf = user.getBytes ();
            InetAddress address = InetAddress.getByName ("10.0.0.61");

            //Constructs a datagram packet for sending packets of length buf.length to the specified port number on the specified host.
            DatagramPacket usernamePacket = new DatagramPacket (buf, buf.length, address, 40000);
            socket.send(usernamePacket);

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
                        System.out.println(broadcast);
                        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, broadcast, 40000);
                        socket.send(sendPacket);

                    } catch (Exception e) {

                    }
                }

            //Close socket connection
            socket.close();
            //Close Scanner
            scanner.close();
        }
    }
}