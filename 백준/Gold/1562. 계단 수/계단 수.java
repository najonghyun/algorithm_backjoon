import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 문제 : BJ_1562_계단수
 * 설명 : dp이다. 그리고 모르면 못푼다 이거 ㅋㅋㅋ 일단 이차원 배열로 dp[n][k] = dp[n-1][k-1] + dp[n-1][k+1];
 * 여기서 dp[n][k] 뜻은  n:숫자 개수, k:끝에 오는 수  -> 그 값은 그 N-1개수의 수에서 끝에 오는수가 하나 적은거 수 + 많은거 수 해주는 점화식이다.
 * 그리고 이것은 모든 숫자가 한번씩 들어가야 하므로 비트마스킹으로 1<<10까지 해주면서 모든 경우의 수를 넣고 계산 해준다. 이때 k가 0일때와 9일때는 각각 1일때 8일때 밖에 안되므로 그렇게 계산해준다.
 * @author 나종현
 *
 */
public class Main {
	static int N;
	static long[][][] dp;
	static int mod = 1000000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		dp = new long[N+1][10][1<<10];
		
		for(int i=1; i<10; i++) { // 초기에 1자리 수에 각 수는 계단 수 이므로 1을 넣어준다. 이때 1자리수의 0은 안되므로 넣지 않는다.
			dp[1][i][1<<i] = 1;				
			
		}
		
		for(int n=2; n<=N; n++) { // 2부터 시작
			for(int k=0; k<10; k++) { // k는 0부터 해줌
				for(int visit=0; visit<(1<<10); visit++) {
					int newVisit = visit | (1 << k); // 가장 끝에 오는 수랑 합집합을 넣어줌
					
					if(k == 0) { // k가 0일때는 k다 큰수만 넣어줌
						dp[n][k][newVisit] = (dp[n][k][newVisit] + dp[n-1][k+1][visit])%mod;
					}else if(k == 9) { // k가 9일때는 k다 큰수만 넣어줌
						dp[n][k][newVisit] = (dp[n][k][newVisit] + dp[n-1][k-1][visit])%mod;
					}else {  
						dp[n][k][newVisit] = (dp[n][k][newVisit] + (dp[n-1][k-1][visit] + dp[n-1][k+1][visit])%mod)%mod;											
					}
					
				}
			}
		}
	
		long sum = 0;
		for(int k=0; k<10; k++) {
			sum = (sum + dp[N][k][(1<<10)-1])%mod; // 이건 모든 수를 다 쓴 것만 더해줌
		}
		System.out.println(sum);
		

	}

}