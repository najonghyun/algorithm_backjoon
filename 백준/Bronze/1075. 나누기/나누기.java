import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		int F = parseInt(in.readLine());
		
		int min = 0;
		int num = N / 100;
		for(int i=0; i<100; i++) {
			int temp = num * 100 + i;
			if(temp % F == 0) {
				min = i;
				break;
			}
		}
		
		if(min < 10) {
			System.out.println("0"+min);
		}else {
			System.out.println(min);
		}
		
		
	}
}