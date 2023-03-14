import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Open an SMTP connection to a mailserver and send one mail.
 *
 */
public class SMTPConnection {
    /* The socket to the server */
    private Socket connection;

    /* Streams for reading and writing the socket */
    private BufferedReader fromServer;
    private DataOutputStream toServer;

    private static final int SMTP_PORT = 2526;
    private static final String CRLF = "\r\n";

    /* Are we connected? Used in close() to determine what to do. */
    private boolean isConnected = false;

    /* Create an SMTPConnection object. Create the socket and the 
       associated streams. Initialize SMTP connection. */
    public SMTPConnection(Envelope envelope) throws IOException {
        /* Fill in */;
        connection = new Socket(envelope.DestHost, SMTP_PORT);
        /* Fill in */;
        fromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        /* Fill in */;
        toServer =   new DataOutputStream(connection.getOutputStream());

        /* Fill in */
        String lineFromServer = fromServer.readLine();
	/* Read a line from server and check that the reply code is 220.
	   If not, throw an IOException. */
        /* Fill in */
        if (!lineFromServer.startsWith("220"))
        {
            throw new IOException("220 no reply from server.");
        }
	/* SMTP handshake. We need the name of the local machine.
	   Send the appropriate SMTP handshake command. */
        String localhost = InetAddress.getLocalHost().getHostName();
        sendCommand("HELO " + localhost, 250);

        isConnected = true;
    }

    /* Send the message. Write the correct SMTP-commands in the
       correct order. No checking for errors, just throw them to the
       caller. */
    public void send(Envelope envelope) throws IOException {
        /* Fill in */
	/* Send all the necessary commands to send a message. Call
	   sendCommand() to do the dirty work. Do _not_ catch the
	   exception thrown from sendCommand(). */
        /* Fill in */
    }

    /* Close the connection. First, terminate on SMTP level, then
       close the socket. */
    public void close() {
        isConnected = false;
        try {
            sendCommand("QUIT",221);
            connection.close();
        } catch (IOException e) {
            System.out.println("Unable to close connection: " + e);
            isConnected = true;
        }
    }

    /* Send an SMTP command to the server. Check that the reply code is
       what is is supposed to be according to RFC 821. */
    private void sendCommand(String command, int rc) throws IOException {

        /* Fill in */
        /* Write command to server and read reply from server. */
        toServer.writeBytes(command + CRLF);
        String response = fromServer.readLine();
        System.out.println(response);
        if (parseReply(response) != rc) {
            throw new IOException("Unexpected response from server " + response);
        }


        /* Fill in */

    }

    /* Parse the reply line from the server. Returns the reply code. */
    private int parseReply(String reply) {
        /* Fill in */
    }

    /* Destructor. Closes the connection if something bad happens. */
    protected void finalize() throws Throwable {
        if(isConnected) {
            close();
        }
        super.finalize();
    }
}