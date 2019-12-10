public class NonLinearEquation {
    private double[] kf;
    private double degree;

    public NonLinearEquation(double[] kf) {
    	this.kf = kf;
    	this.degree = kf.length - 1;
    }

    private double f(double x) {
    	double f = 0;
    	double deg = degree;
    	for (int i = 0; i < kf.length; i++) {
    		f += Math.pow(x, deg) * kf[i];
    		deg--;
    	}
    	return f;
    }

    public void Print() {
    	double deg = degree;
    	for (int i  = 0; i < kf.length; i++)  {
    		if (i == kf.length - 1 && kf[i] != 0) {
    			System.out.print(kf[i] + "x^" + deg);
    		}
    		else if (kf[i] != 0) {
    			System.out.print(kf[i] + "x^" + deg + "+");
    		}
    	}
    }

    public double Solve(double a, double b) {
    	if (a > b) {
    		double c = a;
    		a = b;
    		b = c;
    	}
        double begin = a;
        double end = b;
        if (f(a) == 0) return a;
        if (f(b) == 0) return b;
        double mid = a;
        assert (a * b < 0);
        double eps = 1e-8;
        while (Math.abs(end - begin) > eps) {
            double dx = (end - begin) / 2;
            mid = begin + dx;
            if (f(begin) * f(mid) < 0) {
                end = mid;
            } else {
            	begin = mid;
            }
        }
        return mid;
    }

}