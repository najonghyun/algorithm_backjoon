import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_11054_가장긴바이토닉부분수열
 * 설명 : 처음 재귀로 풀었는데 아니나 다를까 시간초과가 났다. 그래서 이 전 문제인 가장 긴 증가하는 부분수열에서 좀더 확장한 문제인 dp 문제이다. 
 * 먼저 각 수도 부분수열에 일부이므로 dp에 1씩  할당한다. 그다음 어떤 수를 각 기준으로 잡고 처음 수부터 기준 수 까지 비교하면서 만약 기준수보다 작은 수가 있으면 
 * 원래 저장된 dp[i]랑 dp[j]+1 중에 더 최대값을 할당하는 방식으로 해결한다. 여기서 바이토닉부분수열은 다시 작아지는 것도 있기 때문에 dp를 반대로 한 역 dp도 만들어서 할당하고
 * 결과값을 출력할 때는 그 두 dp를 합친 값을 출력한다. 이때  기준 수가 겹치므로 -1을 해줘야한다. 
 *   
 * @author 나종현
 *
 */
public class Main {
	static int N, maxCount;
	static int[] S;
	static int[] dp, rdp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		S = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			S[i] = parseInt(st.nextToken());
		}
		dp = new int[N];
		rdp = new int[N];
		
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			rdp[N-1-i] = 1;
			for(int j=0; j<=i; j++) {
				if(S[i] > S[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			for(int j=N-1; j>=N-1-i; j--) {
				if(S[N-1-i] > S[j]) {
					rdp[N-1-i] = Math.max(rdp[N-1-i], rdp[j] + 1);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			maxCount = Math.max(maxCount, dp[i] + rdp[i] - 1);
		}
		
		System.out.println(maxCount);
	}

}