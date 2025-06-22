import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * 문제 : BJ_2629_양팔저울
 * 설명 : 이거는 처음에는 조합으로 넣고 안넣고를 판별하려다가 시간초과가 나니  dp를 사용해 rest를 만들어서 재귀로 큰거부터 작은순으로 넣었다. 근데 짜피 저울을 기준으로 diff = 두 저울의 차이 
 * 로 보고 계산했다.
 * + i를 기준으로 해당 추가 1. 왼쪽에 놓을때, 2. 오른쪽에 놓을 때, 3. 넣지 않을 때 세가지를 넣었다.
 * + 판별할때는 모든 i에 대해서 하나라도 만족하는지 비교해봐야 한다.
 * + 무게차이가 아무리 넣어봤자 15000을 넘지않으므로 15000이상의 무게 판별은 무조건 "N"이다.
 * @author 나종현
 *
 */
public class Main {
	static int N;
	static boolean[][] dp;
	
	public static void dfs(int[] weight, int i, int diff) {
		if(dp[i][diff]) return;
		dp[i][diff] = true;
		if(i == N) return;
		
		dfs(weight, i+1, diff + weight[i]);
		dfs(weight, i+1, Math.abs(diff - weight[i]));
		dfs(weight, i+1, diff);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = parseInt(in.readLine());
		int[] weight = new int[N];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			weight[i] = parseInt(st.nextToken());
		}
		int M = parseInt(in.readLine());
		int[] result = new int[M];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<M; i++) {
			result[i] = parseInt(st.nextToken());			
		}
		
		// dp[i][diff]		
		dp = new boolean[N+1][15001];
		
		dfs(weight, 0, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			if(result[i] > 15000) {
				sb.append("N").append(" ");
				continue;
			}
			boolean canMake = false;
			for(int j=0; j<=N; j++) {
				if(dp[j][result[i]]) {
					canMake = true;
					break;
				}
			}
			sb.append(canMake ? "Y" : "N").append(" ");
		}
		
		System.out.println(sb);
		

	}

}
