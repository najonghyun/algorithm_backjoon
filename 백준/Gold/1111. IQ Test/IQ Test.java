import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main  {
	static int N;
	static int[] arrays;
	
//	private static void dfs(double b, int now, int next) {
//		double a = (next - b)/now;
//		
//		
//
//	}
	
	public static void main(String[] args) throws IOException, NumberFormatException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		arrays = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			arrays[i] = parseInt(st.nextToken());
		}
		
		int d = 0;
		int r = 0;
		
		if(N == 1) {
			System.out.println("A");
		}else if(N == 2) {
			if(arrays[0] == arrays[1]) {
				System.out.println(arrays[0]);
			}else {
				System.out.println("A");
			}
		}else {
			int n1 = arrays[0]; 
			int n2 = arrays[1]; 
			int n3 = arrays[2];
			
			if(n2-n1 == 0) {
				r = 0;
				d = n2;
				
			}else {
				r = (n3-n2)/(n2-n1);
				d = n2-n1*r;
			}
			
			boolean check = true;
			for(int i=1; i<N; i++) {
				if(arrays[i] != arrays[i-1]*r + d) {
					check = false;
					break;
				}
			}
			
			System.out.println(check ? arrays[N-1]*r + d : "B");
			
		}
		
		
		
		
		
	}

}