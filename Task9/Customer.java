import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TreeMap;



public class Customer implements Serializable, Comparable<Customer> {
	private static final long serialVersionUID = 1L;
	public String customerName;
	TreeMap<Date, Customer> requests = new TreeMap<>();
	TreeMap<Date, Customer > meetings = new TreeMap<>();
    ArrayList<Appointment> customerAppointments = new ArrayList<>();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    protected static SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
    protected static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
    public int compareTo(Customer c) {
    	return customerName.compareTo(c.getCustomerName());
    }


    //Methods
    public Customer() {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(Server.currentDate);
        for (int i = 0; i < 7; ++i) {
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            for (int j = 1; j < Server.MAX_APPOINTMENTS; ++j) {
                calendar.add(Calendar.MINUTE, 20);
                customerAppointments.add(new Appointment(calendar.getTime()));
            }
        }

    }

    public Customer(String customerName) {
        this.customerName = customerName;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Server.currentDate);
        for (int i = 0; i < 7; ++i) {
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            for (int j = 1; j < Server.MAX_APPOINTMENTS; ++j) {
                calendar.add(Calendar.MINUTE, 20);
                customerAppointments.add(new Appointment(calendar.getTime()));
            }
        }
    }
    
    public ArrayList<Appointment> getAppointments() {
        return customerAppointments;
    }

    public Customer(String customerName, ArrayList<Appointment> customerAppointments) {
        this.customerName = customerName;
        this.customerAppointments = customerAppointments;
    }


    public void addAppointment(Appointment appointment) {
        this.customerAppointments.add(appointment);
    }

    public String appointmentsToString() {
        StringBuffer str = new StringBuffer();
        str.append("_____Appointments of client: " + this.getCustomerName() + " _____\n");

        if (customerAppointments.size() == 0)
            return "You have no appointments!";
        customerAppointments.sort((Appointment a1, Appointment a2) -> a1.getDate().compareTo(a2.getDate()));
        for (Appointment appointment : customerAppointments) {
        	if (appointment.isBooked()) {
        		 str.append(ServerB.dateFormat.format(appointment.getDate()));
                 str.append('\n');
        	}
        }
        str.append("____________________________________________");

        return str.toString();
    }
    
    public String showCalendar() {
    	Collections.sort(customerAppointments, new DateCompare());
        int tempDay = 0;
        int day;
        StringBuffer str = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        for (Appointment appointment : customerAppointments) {
            calendar.setTime(appointment.getDate());
            day = calendar.get(Calendar.DAY_OF_MONTH);
            if (!appointment.isBooked()) {
                if (day != tempDay) {
                    tempDay = day;
                    str.append('\n');
                    str.append(dayFormat.format(appointment.getDate()) + "  ----  | " + timeFormat.format(appointment.getDate()) + " | ");
                } else
                    str.append(timeFormat.format(appointment.getDate()) + " | ");
            } else {
            	if (day != tempDay) {
            		tempDay = day;
            		str.append('\n');
            		str.append(dayFormat.format(appointment.getDate()) + "  ----  | ----- | ");
            	}
            	else 
            		str.append( "----- | ");
            }
            
        }
        return str.toString();
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Appointment> getCustomerAppointments() {
        return customerAppointments;
    }

    public void setCustomerAppointments(ArrayList<Appointment> customerAppointments) {
        this.customerAppointments = customerAppointments;
    }
    
    public void recieveRequest(Customer c, Date d) {
    	requests.put(d, c);
    }

    TreeMap<Date, Customer> getRequests() {
    	return requests;
    }
    
    TreeMap<Date, Customer> getMeetings() {
    	return meetings;
    }
    
    public void addMeeting(Date d, Customer c) {
    	meetings.put(d, c);
    }
    
    public void deleteRequest(Date d) {
    	requests.remove(d);
    }
    
    public void deleteMeeting(Customer c, Date d) {
    	meetings.remove(d, c);
    }
    

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + "\'\n" +
                ", customerAppointments=" + customerAppointments +
                '}';
    }
}