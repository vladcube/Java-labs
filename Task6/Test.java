import java.util.Arrays;

public class Test {
    static public void main(String[] args) {
        FuzzyNumber[] fuzzyNumbers = {
                new FuzzyNumber(0.8, 10, 0.2),
                new FuzzyNumber(0.2, 2, 0.3),
                new FuzzyNumber(0.3, 5, 0.4),
                new FuzzyNumber(0.4, 6, -0.1),
                new FuzzyNumber(0.5, -4, 0.6)
        };
        System.out.println("�������� ������ �������� �����:");
        for(FuzzyNumber e: fuzzyNumbers) {
            System.out.println(e.toString());
        }
        System.out.println("���������� �������� ����� �� x:");
        Arrays.sort(fuzzyNumbers);
        for(FuzzyNumber e: fuzzyNumbers) {
            for(Object object: e) {     // ���������� Iterator
                System.out.print(object + " ");
            }
            System.out.println();
        }
        System.out.println("���������� �������� ����� �� e1:");
        Arrays.sort(fuzzyNumbers,new FuzzyNumber.CompareToE1());
            for(FuzzyNumber e: fuzzyNumbers) {
                System.out.println(e.toString());
        }
        System.out.println("���������� �������� ����� �� e2:");
        Arrays.sort(fuzzyNumbers, new FuzzyNumber.CompareToE2()); //���������� �� e2
            for(FuzzyNumber e: fuzzyNumbers) {
                System.out.println(e.toString());
        }

        FuzzyNumber a = new FuzzyNumber(0.4,5,0.2);
        FuzzyNumber b = new FuzzyNumber(0.1,7,0.3);
        FuzzyNumber c;
        System.out.println("\n���������� ������ �������� ����� ��� ������ ������ �������:\n" +"a = " + a + "\nb = " + b);
        System.out.println("����� ���� �������� �����:");
        c = a.addition(b);  
        System.out.println(c.toString());
        System.out.println("�������� ���� �������� �����:");
        c = a.subtraction(b);
        System.out.println(c.toString());
        System.out.println("������������ ���� �������� �����:");
        c = a.multiplication(b);
        System.out.println(c.toString());
        System.out.println("������� ���� �������� �����:");
        c = a.division(b);
        System.out.println(c.toString());
        System.out.println("�������� �������� ����� a:");
        c = a.reverse();
        System.out.println(c.toString());
        System.out.println("�������� �������� ����� b:");
        c = b.reverse();
        System.out.println(c.toString());

        String string = "-0.2 3 0.1";
        System.out.println("\n������� ������ ������������ �� ������� ������" + string);
        FuzzyNumber stringConstructot= new FuzzyNumber(string);
        System.out.println(stringConstructot.toString());

    }
}