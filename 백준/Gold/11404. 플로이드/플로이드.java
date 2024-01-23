import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_11404_플로이드
 * 설명 : 플로이드 워샬의 대표적인 문제이다. (* 플로이드 워샬은 모든 구역에서의 최소 비용을 구하고 싶을 때 사용하고, 인접행렬일때 그 행렬을 최소비용으로 최신화 시킬때 사용한다.)
 * 주의사항1. 이건 모든 값을 구하고 갈 수 없는 값도 판단해야 하는 문제 이므로  (i!=k) (j!=i, j!=k) 조건을 반드시 지켜야 한다.(그래야 길이 있는 곳만 계산 됨!)
 * 주의사항2. 플로이드 워샬 문제 해결 시 초기 INF는 충분히 크게 설정하여야 한다. 애매할 땐 차라리  최대로 설정하고 (adjMatrix[i][k] != INF || adjMatrix[k][j] != INF) 이조건을 추가시켜서 
 * 최대값 더하는 것을 방지한다.
 * @author 나종현
 *
 */
public class Main {
	static int n,m;
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = parseInt(in.readLine());
		m = parseInt(in.readLine());
		int[][] adjMatrix = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			Arrays.fill(adjMatrix[i], INF);
		}
		
		StringTokenizer st;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			int c = parseInt(st.nextToken());
			
			adjMatrix[a][b] = Math.min(adjMatrix[a][b], c);
			
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(i == k) {
					continue;
				}
				for(int j=1; j<=n; j++) {
					if(j == i || j == k || adjMatrix[i][k] == INF || adjMatrix[k][j] == INF) {
						continue;
					}
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) { // 조건에 맞게 출력
			for(int j=1; j<=n; j++) {
				if(adjMatrix[i][j] == INF ) {
					sb.append(0);
				}else {
					sb.append(adjMatrix[i][j]);
				}
				if(j != n) {
					sb.append(" ");					
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}