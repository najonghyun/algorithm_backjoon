import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_14728_벼락치기
 * 설명 : 전형적인 냅색 문제이다.
 * @author 나종현
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int T = parseInt(st.nextToken());
		
		int[] time = new int[N+1];
		int[] score = new int[N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			time[i] = parseInt(st.nextToken());
			score[i] = parseInt(st.nextToken());
		}
		
		// dp[i][time] = score
		int[][] dp = new int[N+1][T+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=T; j++) {
				if(j >= time[i]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-time[i]] + score[i]);					
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][T]);

	}

}
