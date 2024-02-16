import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 문제 : 이거 처음에  bfs인줄 알았는데 알고봤더니 양방향인데 사이클이 있어도 되는지는 판단하지 못했다. 이건 최단 경로 문제이고 이제 모든 곳에서 돌아야하므로 다익스트라 보다는 플로이드 워샬
 * 알고리즘을 사용하였다. 그럼 간단하게 해결할 수 있다.
 * @author 나종현
 *
 */
public class Main {
	static int n, m, r;
	static int[] items;
	static int[][] adjMatrix;
	static int maxItem;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		r = parseInt(st.nextToken());
				
		items = new int[n];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<n; i++) {
			items[i] = parseInt(st.nextToken());
		}
		
		adjMatrix = new int[n][n];
		for(int i=0; i<n; i++) {
			Arrays.fill(adjMatrix[i], INF);
		}
		
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = parseInt(st.nextToken());
			int end = parseInt(st.nextToken());
			int distance = parseInt(st.nextToken());
			adjMatrix[start-1][end-1] = adjMatrix[end-1][start-1] = distance;
		}
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				if(k == i) continue;
				for(int j=0; j<n; j++) {
					if(k == j || i == j || adjMatrix[i][k] == INF || adjMatrix[k][j] == INF) continue;
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			int nowItem = items[i];
			for(int j=0; j<n; j++) {
				if(adjMatrix[i][j] != INF && adjMatrix[i][j] <= m) {
					nowItem += items[j];
				}
			}
			maxItem = Math.max(maxItem, nowItem);
		}
		
		System.out.println(maxItem);
	
		
		
	}

}