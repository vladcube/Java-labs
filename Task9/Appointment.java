import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
    private String clientName;
    private boolean isBooked;

    public Appointment(Date date) {
        this.date = date;
        clientName = null;
        isBooked = false;
    }

    public Appointment(Date date, String clientName, boolean isBooked) {
        this.date = date;
        this.clientName = clientName;
        this.isBooked = isBooked;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public boolean isBooked() {
    	return isBooked;
    }
    
    public void setIsBooked(boolean isBooked) {
    	this.isBooked = isBooked;
    }
    
}