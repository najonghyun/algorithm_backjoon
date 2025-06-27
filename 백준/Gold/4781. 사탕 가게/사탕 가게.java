import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_4781_사탕가게
 * 설명 : 실수가 있으므로 안전하게 정수형으로 계산한다. 원래는 100을 곱하면 되지만 단순히 "."을 없애고 정수로 바꿔도 된다
 * 그 후 2개 이상 넣을 수 있는 무한 배낭으로 처리한다. 이 때 최대값은 전에 것이 계속 반영되므로 dp[m]이 무조건 최대값이다.
 * @author 나종현
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(in.readLine(), " ");
			int n = parseInt(st.nextToken());
			int m = parseInt(st.nextToken().replace(".", ""));
			if(n == 0 && m == 0) {
				break;
			}
			int[] c = new int[n+1];
			int[] p = new int[n+1];
			for(int i=1; i<=n; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				c[i] = parseInt(st.nextToken());
				p[i] = parseInt(st.nextToken().replace(".", ""));
			}
			
			// dp[돈] = 칼로리
			int[] dp = new int[m+1];
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(j >= p[i]) {
						dp[j] = Math.max(dp[j], dp[j-p[i]] + c[i]); 					
					}
				}
			}
			
			// 이건 할 필요 없는게  무조건  가장 큰게 최대값이 나옴!
//			int result = 0;
//			for(int i=1; i<=m; i++) {
//				result = Math.max(result, dp[i]);			
//			}
//			sb.append(result).append("\n");
		
			sb.append(dp[m]).append("\n");
		}
		
		System.out.println(sb);
	}

}
