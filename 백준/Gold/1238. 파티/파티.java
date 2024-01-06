import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1238_파티
 * 설명 : 일단 최단거리를 구해야 하므로 다익스트라 알고리즘을 써야 한다. 하지만 어떤 학생이 가장 오래 걸렸는지 알려면 모든 정점에서 타겟 노드로의 최단거리를 구해야한다.
 * 그러면 모든 정점에서의 최단거리를 구할 수 있으면서 더 효율적인 코드를 작성할 수 있는 플로이드 워샬 알고리즘이 있어 이를 이용해 문제를 해결하였다.
 * @author 나종현
 *
 */
public class Main {
	static int N, M, X;
	static int[][] adjMatrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		X = parseInt(st.nextToken());
		
		adjMatrix = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				adjMatrix[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = parseInt(st.nextToken());
			int end = parseInt(st.nextToken());
			int time = parseInt(st.nextToken());
			adjMatrix[start][end] = time;
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(adjMatrix[i][k] == Integer.MAX_VALUE || adjMatrix[k][j] == Integer.MAX_VALUE) {
						continue;
					}
					adjMatrix[i][j] = Math.min(adjMatrix[i][k] + adjMatrix[k][j], adjMatrix[i][j]);
				}
			}
		}
		
		int result = 0;
		for(int i=1; i<=N; i++) {
			if(i == X) {
				continue;
			}
			int temp = adjMatrix[i][X] + adjMatrix[X][i];
			result = Math.max(result, temp);
		}
		System.out.println(result);
		

	}

}