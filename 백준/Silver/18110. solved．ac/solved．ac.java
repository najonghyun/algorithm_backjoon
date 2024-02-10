import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(in.readLine());
		
		if(n == 0) {
			System.out.println(0);
		}else {
			int[] levels = new int[n];
			for(int i=0; i<n; i++) {
				levels[i] = parseInt(in.readLine());
			}
			Arrays.sort(levels);
			
			float fnum = (float) (n * 0.15);
//			float fnum = (float) 0.5;
//			System.out.println("fnum : " + fnum);
			int num = (int) (fnum+0.5);
			
			
//			System.out.println("num : " + num);
			long sum = 0;
			float size = 0;
			for(int i=0; i<n; i++) {
				if(i < num || i >= n-num) {
//					System.out.println("!!");
					continue;
				}
				sum += levels[i];
				size++;
			}
			
			double result = sum/size;
			System.out.println(Math.round(result));
		}
		
		

	}

}