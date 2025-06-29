import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_2662_기업투자
 * 설명 : 일반 배낭문제에서 중간에 k라는 선택한 투자 액수를 추가시켰다.
 * 그래서 dp[i][j] = dp[i-1][j-k] + profits[i][k] 이 점화식을 완성 시켰다. 이때 k는 (1 <= k <= j)을 만족해야 한다!
 * 그 후 point라는 배열을 만들어 각 위치에 해당하는 k를 넣어주고 그거를 거꾸로부터 시작해서 추적하여 각 k를 구한다.
 * 
 * @author 나종현
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());

		// profits[기업][투자금액] = 이익
		int[][] profits = new int[M+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			st.nextToken();
			for(int j=1; j<=M; j++) {
				profits[j][i] = parseInt(st.nextToken());
			}
		}
		
		// dp[M+1][N+1] = 이익
		// k = 선택한 투자 액수, point[M+1][N+1]에 저장 
		int[][] dp = new int[M+1][N+1];
		int[][] point = new int[M+1][N+1];
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=N; j++) {
				for(int k=0; k<=j; k++) {
					if(dp[i-1][j-k] + profits[i][k] > dp[i][j]) {
						dp[i][j] = dp[i-1][j-k] + profits[i][k];
						point[i][j] = k;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dp[M][N]).append("\n");
		
		// 거꾸로부터 시작해서 복기
		int[] result = new int[M+1];
		int money = N;
		for(int i=M; i>=1; i--) {
			result[i] = point[i][money];
			money -= result[i];
		}
		
		// 출력
		for(int i=1; i<=M; i++) {
			sb.append(result[i]);
			if(i < M) {
				sb.append(" ");
			}
		}
		
		System.out.println(sb);
		
	}

}