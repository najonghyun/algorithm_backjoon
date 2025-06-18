import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_9084_동전
 * 설명: 일단 그냥 일일히 할라고 하면 무조건 시간초과 난다. 그래서 dp를 써야하는데 먼저 어떻게 방정식을 세울 때
 * 1. 배열이 뭐를 기준으로 잡을지 결정한다. 여기서는 dp[M]에서 M은 금액으로 잡았다.
 * 2. 0이 올수있는 경우의 수는 무조건 1가지므로 dp[0] =  로 잡는다.
 * 3. 오름차순인 코인을 작은거부터 하나씩 꺼내어 하나씩 전이한다. 
 * (만약 코인 받기 전에의 경우의 수가 3이면 코인 을 받고 난 경우에도 같은 경우의 수를 가지게 된다)
 * @author 나종현
 *
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = parseInt(in.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int N = parseInt(in.readLine());
			int[] coins = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int i=0; i<N; i++) {
				coins[i] = parseInt(st.nextToken());
			}
			int price = parseInt(in.readLine());
			
			long[] dp = new long[price+1];
			dp[0] = 1;
			for(int coin : coins) {
				for(int i=coin; i<price+1; i++) {
					dp[i] += dp[i-coin];
				}
			}
			sb.append(dp[price]).append("\n");
		}
		System.out.println(sb);
	}
}
