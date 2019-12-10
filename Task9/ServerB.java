import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

final class DateCompare implements Comparator<Appointment> {
    @Override
    public int compare(Appointment o1, Appointment o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

public class ServerB {
	public ArrayList<Customer> customers = new ArrayList<>();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    protected static SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
    protected static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public ServerB() {
    }

    public static Date toDate(String str) {
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String denyAppointment(Date date, Customer customer, boolean isDelete) {

        for (Appointment appointment : customer.getAppointments()) {
            if (appointment.getDate().equals(date))
            	if (appointment.getClientName().equals(customer.getCustomerName())) {
            		if (isDelete) {
            			appointment.setClientName(null);
                        appointment.setIsBooked(false);
            		}
                    return "Time was denied successfully!";
                } else
                    return "This time doesn't belong to you. You can't deny this!";
        }

        return "Error! You can't deny this time!";
    }

    public boolean addAppointment(Date date, Customer customer) {
        for (Appointment appointment : customer.getAppointments()) {
            if (appointment.getDate().equals(date)) {
            	appointment.setClientName(customer.getCustomerName());
            	appointment.setIsBooked(true);
                return true;
        	}

        }
        return false;
    }
    
    public String showCalendar(Customer c) {
    	return c.showCalendar();
    }
    
    public String getListClients() {
    	String ans = new String();
    	ans = "\nClients at server " + customers.size() + "\n";
    	int i = 1;
    	for (Customer c : customers) {
    		ans += i + ") " + c.getCustomerName() + '\n';
    		i++;
    	}
    	return ans;
    }
    
    public ArrayList<Customer> getCustomers() {
    	return customers;
    }
    
    public String meeting(Customer c1, String name) {
    	StringBuffer ans = new StringBuffer();
    	boolean f = false, f1 = true;
    	for (Customer c : customers) {
    		if (c.getCustomerName().equals(name)) {
    			f = true;
    			for (Appointment a1 : c.getAppointments()) {
    				for (Appointment a2 : c1.getAppointments()) {
    					if (a1.isBooked() && a1.isBooked() == a2.isBooked()) {
    						if (a1.getDate().compareTo(a2.getDate()) == 0) { 
    							if (f1) {
    								ans.append("You can meet with client " + name + " at this times:\n");
       							 	f1 = false;
       						 	}
       						 	ans.append(dateFormat.format(a1.getDate()) + "\n");
    						}
    					}
    				}
    			}
    		}
    	}
    	if (f && f1) {
    		return "There is no free time to meeting with " + name + "!\n";
    	}
    	else if (!f){
    		return "There is no client with name " + name + "!\n";
    	}
    	return ans.toString();
    }
    
    
    public String freeTime(String name) {
    	boolean f = true, f1 = false;
    	StringBuffer ans = new StringBuffer();;
    	for (Customer c : customers) {
    		if (c.getCustomerName().equals(name)) {
    			f1 = true;
    			for (Appointment a : c.getCustomerAppointments()) {
    				if (f) {
        				ans.append(name + "'s free time: \n");
        				f = false;
        			}
    				if (a.isBooked()) {
    					ans.append(dateFormat.format(a.getDate()) + "\n");
    				}
    			}
    		}
    	}
    	if (!f1) {
    		return "There is no person with name " + name + "!\n";
    	}

		return ans.toString();
    }
    
    public String request(Customer c1, String name, Date d) {
    	StringBuffer ans = new StringBuffer();
    	boolean f = false, f1 = false;
    	for (Customer c : customers) {
    		if (c.getCustomerName().equals(name)) {
    			f = true;
    			for (Appointment a1 : c.getAppointments()) {
    				for (Appointment a2 : c1.getAppointments()) {
    					if (a1.isBooked() && a1.isBooked() == a2.isBooked()) {
    						if (a1.getDate().compareTo(a2.getDate()) == 0 && a1.getDate().compareTo(d) == 0) { 
    							f1 = true;
       						 	ans.append("Request for this time: " + dateFormat.format(a1.getDate()) + " was successfully send to " + name + "!\n");
       						 	sendRequest(c1,c, d);
    						}
    					}
    				}
    			}
    		}
    	}
    	if (f && !f1) {
    		return "You don't have common free time with " + name + "!\n";
    	}
    	else if (!f){
    		return "There is no client with name " + name + "!\n";
    	}
    	return ans.toString();
    }
    
    public void sendRequest(Customer c1, Customer c, Date d) {
    	c.recieveRequest(c1, d);
    }
    
    public String requests(Customer c) {
    	StringBuffer ans = new StringBuffer();
    	if (!c.getRequests().isEmpty()) {
    		for (Map.Entry<Date, Customer> p : c.getRequests().entrySet()) {
        		ans.append(p.getValue().getCustomerName() + " wants to meet at: " + dateFormat.format(p.getKey()) + "\n");
        	}
    	}
    	else ans.append("You don't have requests!\n");
    	return ans.toString();
    }

    public String checkAns(Customer c, String name, Date d, String a) {
    	if (a.equals("n")) {
    		for (Customer c1 : customers) {
        		if (c1.getCustomerName().equals(name)) {
        			denyAppointment(d, c, false);
        			denyAppointment(d, c1, false);
        			c.deleteRequest(d);
        			c1.deleteRequest(d);
        		}
        	}
    		return "Oops, if you don't want to meet with " + name + ", try to meet with another client!\n";
    	}
    	else if (!a.equals("y")) {
    		return "Incorrect values";
    	}
    	else {
    		boolean f = false;
        	StringBuffer ans = new StringBuffer();
        	for (Customer c1 : customers) {
        		if (c1.getCustomerName().equals(name)) {
        			f = true;
        			denyAppointment(d, c, true);
        			denyAppointment(d, c1, true);
        			c.deleteRequest(d);
        			c1.deleteRequest(d);
        			//add checking dates;
        			c1.addMeeting(d, c);
        			c.addMeeting(d, c1);
        			ans.append("You will meet with " + name + " at: " + dateFormat.format(d) + "\n");
        		}
        	}
        	if (!f) {
        		return "There is no client with name: " + name;
        	}
        	return ans.toString();
    	}
    }
    
    public String getMeetings(Customer c) {
    	StringBuffer ans = new StringBuffer();
    	if (!c.getMeetings().isEmpty()) {
    		for (Map.Entry<Date, Customer> p : c.getMeetings().entrySet()) {
        		ans.append("You will meet with " + p.getValue().getCustomerName() + " at: " + dateFormat.format(p.getKey()) + "\n");
        	}
    	}
    	else ans.append("You don't have meetings!\n");
    	
    	return ans.toString();
    }
    
    public String deleteMeeting(Customer c, String name, Date d) {
    	StringBuffer ans = new StringBuffer();
    	boolean f = false;
    	if (!c.getMeetings().isEmpty()) {
    		for (Map.Entry<Date, Customer> p : c.getMeetings().entrySet()) {
    			if (p.getValue().getCustomerName().equals(name) && p.getKey().equals(d)) {
    				f = true;
        			ans.append("Meeting with " + p.getValue().getCustomerName() + " at: " + dateFormat.format(p.getValue()) + " was deleted!\n");
        			c.deleteMeeting(p.getValue(), d);
        			p.getValue().deleteMeeting(c, d);
    			}
    			
    		}
    		if (!f) ans.append("You don't have meetings with " + name + "\n");
    	}
    	else ans.append("You don't have meetings!\n");
    	
    	return ans.toString();
    	
    }
   


}