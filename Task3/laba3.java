package laba3;
import java.util.Scanner;

public class laba3 {
    public static void main( String[] args ) {
        try {
            Scanner in = new Scanner(System.in);
            int  idxFrom, idxTo;
            while (in.hasNextLine()) {
                idxFrom = idxTo = -1;
                String str = in.nextLine(), temp;
                for(int i = 0; i < str.length(); i++) {
                    if(str.charAt(i) == '(') {
                        idxFrom = i;
                    }
                    else if(str.charAt(i)==')') {
                        idxTo = i;
                    }
                    if((idxTo-idxFrom > 0) && (idxTo > 0) && (idxFrom > 0)) {
                        temp = str.substring(0, idxFrom) + str.substring(idxTo + 1);
                        str = temp;
                        i -= (idxTo - idxFrom + 1);
                        idxFrom = idxTo = -1;
                    }
                }
                System.out.println(str);
            }
            in.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.exit(0);
    }
}



