import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class CustomerThread implements Runnable {

    Socket client;

    public CustomerThread(Socket client) {
        this.client = client;
    }


    //--------------------------RUN------------------------
    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            String introduction = "**** Welcome to meeting service!****\nEnter your name: ";
            out.writeUTF(introduction);
            out.flush();
            String customerName = in.readUTF();
            System.out.println(customerName + " initialized!");
            Customer customer = new Customer(customerName);
            Server.serverB.customers.add(customer);


            //------------Main part------------------
            StringBuffer title = new StringBuffer();
            title.append("___" + customer.getCustomerName() + ", what would you like to do?___\n");
            title.append("Write \"make\" to make an appointment.\n");
            title.append("Write \"my\" to look at your appointments.\n");
            title.append("Write \"deny\" to deny your appointment.\n");
            title.append("Write \"meet\" to make meeting with another person.\n");
            title.append("Write \"clients\" to show list of all clients.\n");
            title.append("Write \"free time\" to show free time of another person.\n");
            title.append("Write \"request\" to make request for meeting.\n");
            title.append("Write \"requests\" to show requests for you for meeting.\n");
            title.append("Write \"meetings\" to show your meetings.\n");
            title.append("Write \"delete meeting\" to delete meeting.\n");
            title.append("Write \"quit\" to disconnect.\n");

            out.writeUTF(title.toString());
            while (!client.isClosed()) {

                String request = in.readUTF();
                System.out.println(customer.getCustomerName() + " requested: " + request);

                switch (request) {
                    case "make":

                        out.writeUTF(Server.serverB.showCalendar(customer) + '\n' + "Write in format \"dd.MM.yyyy HH:mm\"\n");
                        out.flush();

                        String dateAppointment = in.readUTF();

                        if (Server.serverB.addAppointment(ServerB.toDate(dateAppointment), customer)) {
                            out.writeUTF("You are assigned to the specified time!\n\n" + title.toString());
                            System.out.println(customer.getCustomerName() + " appointed for: " + dateAppointment);
                        } else {
                            System.out.println(customer.getCustomerName() + " failed to make an appointment for: " + dateAppointment);
                            out.writeUTF("Sorry, this time is already assigned! Try another free time!\n\n" + title.toString());

                        }
                        break;

                    case "my":
                        System.out.println(customer.getCustomerName() + " requested the list of his/her appointments!");
                        out.writeUTF(customer.appointmentsToString() + "\n\n" + title.toString());
                        break;

                    case "deny":
                        out.writeUTF("Enter time you want to deny. Write in format \"dd.MM.yyyy HH:mm\"\n");
                        out.flush();


                        dateAppointment = in.readUTF();
                        String denied = Server.serverB.denyAppointment(ServerB.toDate(dateAppointment), customer, true);
                        out.writeUTF(denied + '\n' + title.toString());
                        System.out.println(customer.getCustomerName() + " tries to deny: " + dateAppointment);
                        System.out.println(customer.getCustomerName() + " got in result: " + denied);
                        break;
                        
                    case "meet":
                    	out.writeUTF("Enter name with him you want to meet:");
                    	out.flush();
                    	
                    	String name = in.readUTF();
                    	String meeting = Server.serverB.meeting(customer, name);
                    	out.writeUTF(meeting + '\n' + title.toString());
                    	System.out.println(customer.getCustomerName() + " tries to meet: " + name);
                        System.out.println(customer.getCustomerName() + " got in result: " + meeting);
                        break;
                        
                    case "free time":
                    	out.writeUTF("Enter name whos free time you want to show:");
                    	out.flush();
                    	
                    	String clientName = in.readUTF();
                    	String freeTime = Server.serverB.freeTime(clientName);
                    	out.writeUTF(freeTime + '\n' + title.toString());
                    	System.out.println(customer.getCustomerName() + " tries to show " + clientName + "'s free time!");
                        System.out.println(customer.getCustomerName() + " got in result: " + freeTime);
                        break;
                        
                    case "request":
                    	out.writeUTF("Enter name with him you want to meet:");
                    	out.flush();
                    	String cname = in.readUTF();
                    	out.writeUTF("Write date in format \"dd.MM.yyyy HH:mm\" when you want to meet!\n");
                        out.flush();
                        String d = in.readUTF();
                        Date date = ServerB.toDate(d);
                    	
                    	String r = Server.serverB.request(customer, cname, date);
                    	out.writeUTF(r + '\n' + title.toString());
                    	System.out.println(customer.getCustomerName() + " tries to show " + cname + "'s free time!");
                        System.out.println(customer.getCustomerName() + " got in result: " + r);
                        break;
                        
                    case "requests":
                    	
                    	String rs = Server.serverB.requests(customer);
                    	if (!rs.equals("You don't have requests!\n")) {
                    		System.out.println(customer.getCustomerName() + " tries to show requests");
                            System.out.println(customer.getCustomerName() + " got in result: " + rs);
                            out.writeUTF(rs + "\n\nEnter name with him you want to meet or not:");
                            out.flush();
                            String n = in.readUTF();
                            out.writeUTF("Enter date: ");
                            out.flush();
                            String d1 = in.readUTF();
                            Date d2 = ServerB.toDate(d1);
                            out.writeUTF("Enter your answer (y/n): ");
                            out.flush();
                            String ans = in.readUTF();   
                            rs = Server.serverB.checkAns(customer, n, d2, ans);
                            out.writeUTF(rs + '\n' + title.toString());
                    	}
                    	else {
                    		System.out.println(customer.getCustomerName() + " tries to show requests");
                            System.out.println(customer.getCustomerName() + " got in result: " + rs);
                            out.writeUTF(rs + '\n' + title.toString());
                    	}
                        break;
                        
                        
                    case "clients":
                    	String clients = Server.serverB.getListClients();
                    	System.out.println(customer.getCustomerName() + " tries to show list of clients");
                        System.out.println(customer.getCustomerName() + " got in result: " + clients);
                    	out.writeUTF(clients + '\n' + title.toString());
                        break;
                        
                    case "meetings":
                    	String meetings = Server.serverB.getMeetings(customer);
                    	System.out.println(customer.getCustomerName() + " tries to show list of meetings");
                        System.out.println(customer.getCustomerName() + " got in result: " + meetings);
                    	out.writeUTF(meetings + '\n' + title.toString());
                        break;
                        
                    case "delete meeting":
                    	out.writeUTF("Enter name with him you want to delete:");
                    	out.flush();
                    	name = in.readUTF();
                    	out.writeUTF("Write date in format \"dd.MM.yyyy HH:mm\" when you want to meet!\n");
                        out.flush();
                        d = in.readUTF();
                        date = ServerB.toDate(d);
                    	String del = Server.serverB.deleteMeeting(customer, name, date);
                    	System.out.println(customer.getCustomerName() + " tries to delete meeting");
                        System.out.println(customer.getCustomerName() + " got in result: " + del);
                    	out.writeUTF(del + '\n' + title.toString());
                        break;

                    case "quit":
                        out.writeUTF("You have disconnected! We hope to see you soon!");
                        System.out.println("!!! " + customer.getCustomerName() + " has disconnected!!!\n");
                        Server.serverB.customers.remove(customer);
                        in.close();
                        out.close();
                        client.close();
                        break;
                    default:
                        out.writeUTF(title.toString());
                        break;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    } 	
    
}