import java.io.*;
import java.net.*;


public class ConcurrentEchoServer
{
    static final int PORT = 7;

    public static void main(String args[])
    {
        try {
            ServerSocket ss = new ServerSocket(PORT);

            while(true) {
                Socket ns = ss.accept();
                WorkerThread t = new WorkerThread(ns);
                t.start();
            }
        }
        catch(IOException e) {
            System.err.println("Error: " + e);
            System.exit(1);
        }
    }
}



class WorkerThread extends Thread
{
    WorkerThread(Socket theSocket)
    {
        this.s = theSocket;
    }

    public void run()
    {
        try {
            BufferedReader networkIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter networkOut = new PrintWriter(s.getOutputStream());

            String line;
            while ((line = networkIn.readLine()) != null) {
                networkOut.println(line);
                networkOut.flush();
            }

            s.close();
        }
        catch(IOException e) {
            System.err.println("Error: " + e);
            System.exit(1);
        }
    }

    private Socket s;
}



