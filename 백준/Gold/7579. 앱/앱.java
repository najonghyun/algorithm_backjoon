import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 : BJ_7579_앱
 * 설명 : 필요한 메모리  M바이트를 확보하기 위한 앱 비활성화의 최소의 비용을 계산  == (전체 - M)바이트를 넘지 않으면서 최대의 비용을 계산
 * 이러면 이제 기본 배낭문제랑 유사하다. 하지만 이렇게 풀면 10,000,000 * 100 이라 메모리 초과가 난다. 그래서 다른 접근으로 비용의 최대가 100밖에 안되므로 비용을 배열의 기준으로 두고
 * 비용 당 최대 메모리를 집어 넣은 후, 계산 시에는 1부터 비용을 따져가며 처음 최소메모리를 넘었을 때를 출력하여 값을 구했다.
 * @author 나종현
 *
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		
		int[] appSize = new int[N];
		int[] cost = new int[N];
		int costSum = 0;
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			appSize[i] = parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			cost[i] = parseInt(st.nextToken());
			costSum += cost[i];
		}
		
		// dp[cost] = 최대 memory
		int[] dp = new int[costSum+1];
		for(int i=0; i<N; i++) {
			for(int j=costSum; j>=cost[i]; j--) { // 이 때 거꾸로 하는 이유는 0에서부터하면 갱신된 dp가 그 다음 판별에 영향을 끼친다!(중복으로 들어간다) 
				dp[j] = Math.max(dp[j], appSize[i]+dp[j-cost[i]]); 
				
			}			
		}
		
		for(int i=0; i<=costSum; i++) {
			if(dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}

}
