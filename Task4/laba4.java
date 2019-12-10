package laba4;

import java.util.Scanner;
import java.util.Random;

public class laba4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter n: ");
		if (!in.hasNextInt()) {
			System.out.println("Invalid value!");
			System.exit(1);
		}
		
		int n = in.nextInt();
		if (n <= 1) {
			System.out.println("Invalid dimension!");
			System.exit(1);
		}
		int mtr[][] = new int[n][n];
		int temp[] = new int[n];
		Random rnd = new Random();
		
		System.out.println("Matrix: ");
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < n; j++) {
				mtr[i][j] = rnd.nextInt() % (n + 1);
				sum += mtr[i][j];
				System.out.print(mtr[i][j] + "\t");
			}
			System.out.print("  |  " + sum);
			temp[i] = sum;
			System.out.println();
		}
		System.out.println();
		
		for (int j = n - 1; j >= 0; j--) {
			for (int i = 0; i < j; i++) {
				if (temp[i] > temp[i + 1]) {
					int [] tmp = mtr[i];
					mtr[i] = mtr[i + 1];
					mtr[i + 1] = tmp;
					int t = temp[i];
					temp[i] = temp[i + 1];
					temp[i + 1] = t;
				}
			}
		}
		
		System.out.println("Sorted matrix (sum in rows): ");
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < n; j++) {
				sum += mtr[i][j];
				System.out.print(mtr[i][j] + " \t");
			}
			System.out.print("  |  " + sum);
			System.out.println();
		}
		System.out.println();
		in.close();
	}
}
