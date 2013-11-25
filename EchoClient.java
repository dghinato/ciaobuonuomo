import java.io.*;
import java.net.*;


public class EchoClient
{
    public static void main(String args[])
    {
        try {
            if (args.length != 1) {
                System.out.println("Errore! La sintassi corretta e: java EchoClient hostname");
                System.exit(1);
            }
            String hostname = args[0];
            Socket theSocket = new Socket(hostname, 7);
            BufferedReader networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter networkOut = new PrintWriter(theSocket.getOutputStream());
            System.out.println("Connected to echo server");
            while (true) {
                String theLine = userIn.readLine();
                if (theLine.equals(".")) break;
                networkOut.println(theLine);
                networkOut.flush();
                System.out.println(networkIn.readLine());
            }
        }
        catch (IOException e) {
            System.err.println("Errore: " + e);
            System.exit(2);
        }
    }
}
