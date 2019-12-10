package laba4_2;

import java.util.Random;
import java.util.Scanner;

public class laba4_2 {
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
		Random rnd = new Random();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mtr[i][j] = rnd.nextInt() % (n + 1);
			}
		}
		int[] ups = new int[n];
		int[] downs = new int[n];
		for (int i = 0; i < n; i++) {
			int up_count = 1, down_count = 1, max_up = 0, max_down = 0;
			for (int j = 0; j < n - 1; j++) {
				if (mtr[i][j] < mtr[i][j + 1]) {
					up_count++;
				}
				else up_count = 1;
				if (mtr[i][j] > mtr[i][j+1]) {
					down_count++;
				}
				else down_count = 1;
				if (up_count > max_up && up_count != 1) {
					max_up = up_count;
				}
				if (down_count > max_down && down_count != 1) {
					max_down = down_count;
				}
			}
			ups[i] = max_up;
			downs[i] = max_down;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(mtr[i][j] + "\t");
			}
			System.out.print("  |  Max ups: " + ups[i] + "   |   Max downs: " + downs[i] + "\n");
		}
		System.out.println();
		in.close();
	}
}
