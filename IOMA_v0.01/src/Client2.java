import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client2
{
    public static void main (String[] args)
    {
        try {

            DatagramSocket socket = new DatagramSocket ();
            byte[] buf = new byte[256];
            byte[] messageBuf = new byte[1024];
            Scanner scanner = new Scanner(System.in);
            
            //Username
            System.out.println("What is your username? \n");
            String user = scanner.nextLine();
            buf = user.getBytes ();
            InetAddress address = InetAddress.getByName ("127.0.0.1");
            
            //Constructs a datagram packet for sending packets of length buf.length to the specified port number on the specified host.
            DatagramPacket usernamePacket = new DatagramPacket (buf, buf.length, address, 40000);
            socket.send(usernamePacket);
            
            //message
            String message = scanner.nextLine();
            messageBuf = message.getBytes();
            DatagramPacket packet = new DatagramPacket (messageBuf, messageBuf.length, address, 40000);
            //Send packet
            socket.send(packet);
            
            //Close socket connection
            socket.close();
            //Close Scanner
            scanner.close();
        }
        catch (IOException e) {

        }
    }
}