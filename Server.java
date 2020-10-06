import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{
	public static void main(String[] args)
	{
		try
		{
			// First create the input from the keyboard
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Server Program");
			
			// Get the port to listen on
			System.out.print("Enter port number to listen on: ");
			String port_string = input.readLine();
			
			// The port number needs to be an int, so convert the String to an int
			int port = Integer.parseInt(port_string);
			
			// Create a ServerSocket to listen on this address
			ServerSocket server = new ServerSocket(port);
			
			// Accept an incoming client connection on the server socket
			Socket sock = server.accept();
			
			// Create the output stream to the client
			DataOutputStream network = new DataOutputStream(sock.getOutputStream());
			
			// Create the incoming stream to read messages from
			DataInputStream network1 = new DataInputStream(sock.getInputStream());
			
			String line;
			
			// Loop until the connection closes, reading from the network
			while ((line = network1.readUTF()) != null)
			{
				// send message to server in upper case
				network.writeUTF(line.toUpperCase());
			}
			
			// Close sockets.  This will cause the client to exit
			sock.close();
			server.close();
		}
		catch (IOException ioe)
		{
			System.err.println("Error in I/O");
			System.err.println(ioe.getMessage());
			System.exit(-1);
		}
	}
}
