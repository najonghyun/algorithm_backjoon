import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_1504_특정한최단경로
 * 설명 : 일단 최단경로 문제이고 중간에 두 정점을 들려야 하고 거기에 정점 들리고 중복이 되므로 정점 도착마다 새로 최단경로를 돌려야 한다. 
 * 그래서 다익스트라 보다는 플로이드-워샬 알고리즘이 더 코드적으로도 깔끔하다고 판단되어 그렇게 했고, 거기에 어느 정점부터 들려야 최소 인지를 알기위해
 * 2가지로 나누어 최소 경로를 계산하여 해결하였다.
 * @author 나종현
 *
 */
public class Main {
	static int N, E, v1, v2;
	static int[][] adjMatrix;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		E = parseInt(st.nextToken());
		
		adjMatrix = new int[N+1][N+1];
		
		for(int[] arr : adjMatrix) {
			Arrays.fill(arr, INF);			
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			int c = parseInt(st.nextToken());
			adjMatrix[a][b] = adjMatrix[b][a] = c;	
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		v1 = parseInt(st.nextToken());
		v2 = parseInt(st.nextToken());
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k == i) {
					adjMatrix[k][i] = 0;
					continue;
				}
				for(int j=1; j<=N; j++) {
					if(k == j || i == j || adjMatrix[i][k] == INF || adjMatrix[k][j] == INF) {
						continue;
					}
					adjMatrix[i][j] = Math.min(adjMatrix[i][k] + adjMatrix[k][j], adjMatrix[i][j]);
				}
			}
		}
		
		int result = INF;
		if(adjMatrix[1][v1] != INF && adjMatrix[v1][v2] != INF && adjMatrix[v2][N] != INF) { // v1 먼저 들리는 경우 + 경로가 없을 수도 있으므로
			result = adjMatrix[1][v1] + adjMatrix[v1][v2] + adjMatrix[v2][N];
		}
		if(adjMatrix[1][v2] != INF && adjMatrix[v2][v1] != INF && adjMatrix[v1][N] != INF) { // v2 먼저 들리는 경우
			result = Math.min(adjMatrix[1][v2] + adjMatrix[v2][v1] + adjMatrix[v1][N], result);
		}
		
		System.out.println(result == INF ? -1 : result);
		
		
		
	}
}