import java.util.Comparator;
import java.util.Iterator;

public class FuzzyNumber implements Comparable<FuzzyNumber>, Iterator<Object>, Iterable<Object> {
    private double e1;
    private double x;
    private double e2;
    private int iteratorIndex = 0;
    
    public FuzzyNumber() {
        e1 = 0;
        x = 0;
        e2 = 0;
    }
    
    public FuzzyNumber(double e1, double x, double e2) {

        this.e1 = e1;
        this.x = x;
        this.e2 = e2;
    }
    
    public FuzzyNumber(String string) {
        String[] parts = string.split("\\s");
        e1 = Double.parseDouble(parts[0]);
        x = Double.parseDouble(parts[1]);
        e2 = Double.parseDouble(parts[2]);
    }

    public double getX() {
        return x;
    }

    public double getE1() {
        return e1;
    }

    public double getE2() {
        return e2;
    }
    
    public FuzzyNumber addition (FuzzyNumber fuzzyNumber) {
        return new FuzzyNumber(this.x + fuzzyNumber.x - (fuzzyNumber.e1 + this.e1),this.x + fuzzyNumber.x , this.x + fuzzyNumber.x + (fuzzyNumber.e2 + this.e2));
    }
    
    public FuzzyNumber subtraction (FuzzyNumber fuzzyNumber) {
        return new FuzzyNumber(this.x - fuzzyNumber.x - (fuzzyNumber.e1 + this.e1),this.x - fuzzyNumber.x , this.x - fuzzyNumber.x + (fuzzyNumber.e2 + this.e2));

    }
    public FuzzyNumber multiplication (FuzzyNumber fuzzyNumber) {
        return new FuzzyNumber((this.x - this.e1) * (fuzzyNumber.x - fuzzyNumber.e1),this.x * fuzzyNumber.x,(this.x + this.e2) * (fuzzyNumber.x + fuzzyNumber.e2));
    }
    
    public  FuzzyNumber division (FuzzyNumber fuzzyNumber) {
     assert(fuzzyNumber.x > 0);
     return  new FuzzyNumber((this.x - this.e1)/(fuzzyNumber.x + fuzzyNumber.e2),this.x / fuzzyNumber.x, (this.x + this.e2) / (fuzzyNumber.x - fuzzyNumber.e1) );
    }
    
    public FuzzyNumber reverse () {
        assert(this.x > 0);
        return new FuzzyNumber(1 / (this.x + this.e2),1 / this.x, 1/(this.x - this.e1));
    }

    public int compareTo(FuzzyNumber o) {
        if((x - o.getX()) > 0)
        {
            return 1;
        }
        else if((x - o.getX()) < 0)
        {
            return -1;
        }
        return 0;
    }
    
    public String toString ()
    {
        return "( " + getE1() + ", " + getX() + ", " + getE2() + " )";
    }


    static class CompareToE1 implements Comparator <FuzzyNumber> {
        public int compare(FuzzyNumber o1, FuzzyNumber o2) {
            if((o1.getE1() - o2.getE1()) > 0) {
                return 1;
            }
            else if((o1.getE1() - o2.getE1()) < 0) {
                return -1;
            }
            return 0;
        }
    }
    
    static class CompareToE2 implements Comparator <FuzzyNumber> {
        public int compare(FuzzyNumber o1, FuzzyNumber o2) {
            if((o1.getE2() - o2.getE2()) > 0) {
                return 1;
            }
            else if((o1.getE2() - o2.getE2()) < 0) {
                return -1;
            }
            return 0;
        }
    }

    private void reset() {
        iteratorIndex = 0;
    }

    public boolean hasNext() {
        return iteratorIndex < 3;
    }

    public Object next() {
        switch(iteratorIndex){
            case 0:
                iteratorIndex++;
                return getE1();
            case 1:
                iteratorIndex++;
                return getX();
            case 2:
                iteratorIndex++;
                return getE2();
            default:
                reset();
                return null;
        }
    }

    public void remove() {

    }
    
    public Iterator<Object> iterator() {
        return this;
    }
}