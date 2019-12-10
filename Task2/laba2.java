public class laba2 {
	public static void main(String[] args) {
		if (args.length != 4) {
			System.err.println("Invalid number of arguments!");
			System.exit(1);
		}
		int k = Integer.parseInt(args[0]);
		if (k <= 1) {
			System.err.println("Invalid value: " + k);
			System.exit(1);
		}
		
		double a = Double.parseDouble(args[1]), b = Double.parseDouble(args[2]), step = Double.parseDouble(args[3]);
		if (a <= -1 || a >= 1) {
			System.out.println("Invalid value: " + a);
			System.exit(1);
		}
		if (b <= a || b >= 1) {
			System.out.println("Invalid value: " + b);
			System.exit(1);
		}
		if (a + step > b) {
			System.out.println("Invalid value: " + step);
			System.exit(1);
		}
		System.out.printf("%5c", 'x');
		System.out.printf("%15s", "f(x)");
		System.out.printf("%15s", "Math");
		System.out.printf("%25s\n", "Number of steps");
		for (double x = a; x <= b; x += step ) {
			f(x, k); 
		}
		System.exit(0);
	}
	
	public static void f(double x, int k) {
		double eps = 1 / Math.pow(10, k + 1), result = 1, step = 1;
		int n = 1, steps = 0;
		while(Math.abs(step) >= eps) {
			step = ((step * n * x) / (n + 1)) * Math.pow(-1, n);
			result += step;
			n += 2;
			steps++;
		}
		System.out.printf("%7f", x);
		System.out.printf("%15." + k + "f", result);
		System.out.printf("%15." + k + "f", 1 / (Math.sqrt(1 + x)));
		System.out.printf("%15d\n", steps);
	}
}
