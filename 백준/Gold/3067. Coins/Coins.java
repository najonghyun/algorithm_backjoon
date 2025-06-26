import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_3067_Coins
 * 설명 : 문제가 9084 동전과 거의 같다.
 * @author 나종현
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = parseInt(in.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int N = parseInt(in.readLine());
			
			int[] coins = new int[N];
			StringTokenizer st;
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0; i<N; i++) {
				coins[i] = parseInt(st.nextToken());
			}
			
			int M = parseInt(in.readLine());
			
			// dp[만들어야할 금액] = 개수
			long[] dp = new long[10001];
			dp[0] = 1;
			
			for(int i=0; i<N; i++) {
				for(int j=coins[i]; j<=10000; j++) {
					dp[j] += dp[j-coins[i]]; // 갯수 모두 구하는 것이니 전에온 개수를 그대로 다음개수에 반영해야 하므로 += 를 쓴다!
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb);

	}

}
