import java.io.*;
import java.util.*;

public class Main {
    private final static int SIZE = 5;

    public static void main(String[] args) {
        List<Employee> empArr = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("Input.txt"));
            for(int i = 0; i < SIZE; i++) {
                    empArr.add(new Employee(
                            (i + 1), 
                            (int)(Math.random() * 7) + 1,
                            (in.hasNextLine()) ? in.nextLine() : "_", 
                            Math.random() * 2000 + 1, 
                            Math.random() * 1000 + 1, 
                            Math.random() 
                            ));
            }
            for(Employee emp : empArr) {
                System.out.println(emp.toString());
            }

            Connector c = new Connector("out.dat");
            List<Long> indexList = c.createIndexList(empArr);
            System.out.println("Backward:");
            for(int i = indexList.size() - 1; i >= 0; i--) {
                Employee tmpEmp = c.readPos(i, indexList);
                System.out.println(tmpEmp.toString());
            }
            in.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
