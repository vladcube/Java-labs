public class laba5 {
	public static void main(String args[]) {
		if (args.length != 2) {
			System.out.println("Invalid number of argumnts!");
			System.exit(0);
		}
        double a = Double.parseDouble(args[0]);
        double b =Double.parseDouble(args[1]);
        double[] kf = new double[]{-1, 2};
        NonLinearEquation equation = new NonLinearEquation(kf);
        System.out.println("Solution of the system of equations x^3-3*x^2+3=0:");
        System.out.println(equation.Solve(a, b));
    }
}

