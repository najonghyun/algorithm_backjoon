import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: BJ_1106_호텔
 * 설명: 이거는 고객을 기준으로 dp 배열을 잡고 그 값을 금액으로 잡았다. dp[고객수] = 돈 
 * 그리고 점화식을 잤다. 그 후 무한배낭이므로 0에서부터 차례로 증가했다.
 * ** 이 때 고객 수는 충분히 큰값으로 잡는다! 그리고 C부터 끝까지 보며 최소 금액을 찾아 반환
 * @author 나종현
 *
 */
public class Main {
	static int INF = 100001;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int C = parseInt(st.nextToken());
		int N = parseInt(st.nextToken());
		
		int[] money = new int[N];
		int[] count = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			money[i] = parseInt(st.nextToken());
			count[i] = parseInt(st.nextToken());
		}
		// dp[고객수] = 돈
		int[] dp = new int[3001];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=1; j<=3000; j++) {
				if(j >= count[i]) {
					dp[j] = Math.min(dp[j], dp[j-count[i]] + money[i]);					
				}
			}
		}
		
		int result = INF;
		for(int i=C; i<=3000; i++) {
			result = Math.min(result, dp[i]);
		}

		System.out.println(result);

	}

}
