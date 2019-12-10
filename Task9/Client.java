import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
        	System.out.println("Enter IP:");
        	Scanner scan = new Scanner(System.in);
        	String ip = scan.nextLine();
            Socket client=new Socket(ip,4232);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println(in.readUTF());
            String clientName = scan.nextLine();

            if(clientName.isEmpty())
                clientName="NO NAME";

            out.writeUTF(clientName);
            out.flush();

            while(!client.isClosed())
            {
                System.out.println(in.readUTF());

                String input=scan.nextLine();
                out.writeUTF(input);
                out.flush();

                if(input.equalsIgnoreCase("quit"))
                {
                    System.out.println(in.readUTF());
                    break;
                }
            }
            client.close();
            scan.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}