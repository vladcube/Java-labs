import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    public static Date currentDate = new Date();
    private static int SERVER_PORT = 4232;
    private static int MAX_CLIENTS = 15;
    public static int MAX_APPOINTMENTS = 15;
    //******* DB *******
    public static ServerB serverB = new ServerB();
    private static boolean stopFlag = false;
    
    static void setStopFlag(boolean value) {
    	stopFlag = value;
    }
    
    static boolean getStopFlag() {
    	return stopFlag;
    }
    
    public static ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
    public static void main(String[] args) {
        try {
        	ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("#####  Server is working! Waiting for clients!  #####");

            //Current Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy  HH:mm");
            System.out.println("The current date: " + dateFormat.format(currentDate));
            System.out.println("-----------------------------------------------------------\n");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            // CREATING NEW THREADS FOR CLIENTS
            ServerStopThread tester = new ServerStopThread();
            tester.start();
            //ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
            executorService.execute(tester);


            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                executorService.execute(new CustomerThread(client));
                System.out.println("A new client connected! ");
                if (getStopFlag()) {
                	break;
                }
            }
            executorService.shutdown();
            serverSocket.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	executorService.shutdown();
        	System.err.println("stopped");
        	System.exit(0);
        }
    }
}

class ServerStopThread extends Thread{
	
	static final String cmd  = "q";
	static final String cmdL = "quit";
	
	Scanner fin; 
	
	public ServerStopThread() {		
		fin = new Scanner( System.in );
		System.out.println( "Enter \'" + cmd + "\' or \'" + cmdL + "\' to stop server\n" );
	}
	
	public void run() {
		
		while (true) {			
			if (!fin.hasNextLine()) {
				continue;		
			}
			String str = fin.nextLine();
			if (str.equals(cmd) || str.equals(cmdL)) {
				System.out.print("Stop server...");
				try {
					Thread.sleep( 1000 );
				} catch (InterruptedException e) {
					break;
				}
				System.out.println("stopped");
				System.exit(0);
				fin.close();
				this.interrupt();
				Server.setStopFlag(true);
			}

		}
	}
}
