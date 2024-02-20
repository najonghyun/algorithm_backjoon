import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int size;
	static String array;
	static int[] dp;
	static boolean[][] palindrome;
	static int INF = Integer.MAX_VALUE;
	
	private static void checkPalindrome() {
		for(int i=1; i<=size; i++) {
			for(int j=i; j<=size; j++) {
				boolean flag = true;
				int s = i-1;
				int e = j-1;
				while(s <= e) {
					if(array.charAt(s++) != array.charAt(e--)) {
						flag = false;
						break;
					}
				}
				if(flag) palindrome[i][j] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		array = in.readLine();
		size = array.length();
		dp = new int[size+1];
		palindrome = new boolean[size+1][size+1];
		
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		checkPalindrome();
		
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=i; j++) {
				if(palindrome[j][i]) {
					dp[i] = Math.min(dp[i], dp[j-1] + 1);					
				}
			}
		}
		
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[size]);
		
		

	}

}