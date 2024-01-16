import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 문제 : BJ_9251_LCS_나종현
 * 설명 : 먼저 재귀로 푸니깐 시간초과가 났다. 그래서 dp로 메모이제이션을 하여 전에것을 저장하는 방식으로 해결하였다.
 * @author 나종현
 *
 */
public class Main {
	static int N, M;
	static String sentence1, sentence2;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sentence1 = in.readLine();
		sentence2 = in.readLine();
		
		N = sentence1.length();
		M = sentence2.length();
		
		memo = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++){  // i,j 순서대로 하나씩 전진해 나가면서
			for(int j=1; j<=M; j++) {
				if(sentence1.charAt(i-1) == sentence2.charAt(j-1)) { // 만약 같은 알파벳이 나오면 
					memo[i][j] = memo[i-1][j-1]+1; // 해당번째의 전번째 + 1 (j는 해당 위치 1개전에 몇개까지 있었나 + 1)
				}else { // 아니라면 그전거 가져옴(이때 열 바로 전것이랑 행 바로전것 비교해서 더 높은 값 가져오기)
					memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
				}
			}
		}
		
		System.out.println(memo[N][M]);
	}

}