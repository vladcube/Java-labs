package laba3_2;
import java.util.Scanner;

public class laba3_2 {
    public static void main( String[] args ) {
        try {
            Scanner in = new Scanner(System.in);
            while(in.hasNextLine()) {
            	String str = in.nextLine(), result = "";
            	int count = 1;
            	for (int i = 0; i < str.length(); i++) {
            		if (Character.isSpaceChar(str.charAt(i))) {
            			i++;
            			result += " ";
            		}
            		if ((i + 1) < str.length() && str.charAt(i) == str.charAt(i + 1)) {
            			count++;
            		}
            		else {
            			result += str.charAt(i) + Integer.toString(count);
            			count = 1;
            		}
            	}
            	System.out.println(result);
            }
            in.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.exit(0);
    }
}



