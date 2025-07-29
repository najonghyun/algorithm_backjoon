import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 */
public class Main {
	
	public static boolean isInstall(int N, int C, int[] home, int dist) {
		int count = 1;
		int last = home[0];
		for(int i=0; i<N; i++) {
			if(home[i] - last >= dist) {
				count++;
				last = home[i];
			}
		}
		
		return count >= C;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int C = parseInt(st.nextToken());
		int[] home = new int[N];
		for(int i=0; i<N; i++) {
			home[i] = parseInt(in.readLine());
		}
		Arrays.sort(home);
		
		// left, right 거리가 될 수 있는 최소, 최대 거리
		int left = 1;
		int right = home[N-1] - home[0];
		int result = 0;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(isInstall(N, C, home, mid)) {
				result = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		
		System.out.println(result);
		
		
	}
}