import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_6497_전력난_나종현
 * 설명 : 절약할 수 있는 최대 액수는 즉 사용을 최소로 하여 모든 길을 연결하라는 뜻이므로 최소 스패닝 트리 문제이다.
 * + 정수 범위 주의
 * @author 나종현
 *
 */
public class Main {
	static int m;
	static ArrayList<int[]>[] adjList;
	static int INF = Integer.MAX_VALUE;	
	
	public static long MST(int start) {
		int[] minEdge = new int[m];
		boolean[] visited = new boolean[m];
		Arrays.fill(minEdge, INF);
		minEdge[start] = 0;
		long result = 0;
		
		for(int c=0; c<m; c++) {
			int current = -1;
			int min = INF;
			for(int i=0; i<m; i++) {
				if(!visited[i] && min > minEdge[i]) {
					current = i;
					min = minEdge[i];
				}
			}
			
			if(current == -1) break;
			visited[current] = true;
			result += min;
			
			for(int[] arr : adjList[current]) {
				// MST는 최단 경로와 다르게 방문했던 곳은 다시 판별하지 말자!!
				if(!visited[arr[0]] && minEdge[arr[0]] > arr[1]) {
					minEdge[arr[0]] = arr[1];
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			m = parseInt(st.nextToken());
			int n = parseInt(st.nextToken());
			if(m == 0 && n == 0) break;
			adjList = new ArrayList[m];
			for(int i=0; i<m; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			long total = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int x = parseInt(st.nextToken());
				int y = parseInt(st.nextToken());
				int z = parseInt(st.nextToken());
				adjList[x].add(new int[] {y, z});
				adjList[y].add(new int[] {x, z});
				total += z;
			}
		
			sb.append(total - MST(0)).append("\n");	
		}
		
		System.out.println(sb);
	}
}
