import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] buildings;
	
	private static void seeing(int[] array, boolean reverse) {
		
		if(!reverse) {
			for(int i=0; i<N; i++) {
				int j = i+1;
				if(j < N && buildings[j] <= buildings[i]) {
					double preY = Integer.MIN_VALUE;
					while(j < N) {
						double y = (double)(buildings[j]-buildings[i])/(j-i);
//						System.out.println(y);
						if(y > 0) {
							break;			
						}else if(y == 0) {
							array[i] += 1; 
							break;
						}
						j++;
						if(y <= preY) {
							continue;
						}
						array[i] += 1; 
						preY = y;
					}
				}
				if(j < N && buildings[j] >= buildings[i]){
					double preY = Integer.MIN_VALUE;
					while(j < N) {
						double y = (double)(buildings[j]-buildings[i])/(j-i);
						j++;
//						System.out.println(y);
						if(y <= 0 || y <= preY) {
							continue;			
						}
						array[i] += 1; 
						preY = y;
					}
				}
		
			}
		}else {
			for(int i=N-1; i>=0; i--) {
				int j = i-1;
				if(j >= 0 && buildings[j] <= buildings[i]) {
					double preY = Integer.MAX_VALUE;
					while(j >= 0) {
						double y = (double)(buildings[i]-buildings[j])/(i-j);
//						System.out.println(y);
						if(y < 0) {
							break;			
						}else if(y == 0) {
							array[i] += 1; 
							break;
						}
						j--;
						if(y >= preY) {
							continue;
						}
						array[i] += 1; 
						preY = y;
					}
				}
				if(j >= 0 && buildings[j] >= buildings[i]) {
					double preY = Integer.MAX_VALUE;
					while(j >= 0) {
						double y = (double)(buildings[i]-buildings[j])/(i-j);
//						System.out.println(y);
						j--;
						if(y >= 0 || y >= preY) {
							continue; 
						}
						array[i] += 1; 
						preY = y;
					}
				}
		
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		buildings = new int[N];
		int[] right = new int[N];
		int[] left = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			buildings[i] = parseInt(st.nextToken());
		}
		
		seeing(right, false);
		seeing(left, true);
		
		int maxCount = 0;
		for(int i=0; i<N; i++) {
			maxCount = Math.max(maxCount, right[i]+left[i]);
		}		
		
//		System.out.println(Arrays.toString(right));
		System.out.println(maxCount);

	}

}