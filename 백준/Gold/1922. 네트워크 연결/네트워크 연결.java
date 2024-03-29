import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1922_네트워크연결
 * 설명 : 프림 알고리즘으로 해결하였다. 인접리스트 사용했다
 * @author 나종현
 *
 */
public class Main {
	
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		int M = parseInt(in.readLine());
		ArrayList<int[]>[] adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		int[] minEdge = new int[N+1];
		boolean[] visited = new boolean[N+1];
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			int c = parseInt(st.nextToken());
			
			adjList[a].add(new int[] {b, c});
			adjList[b].add(new int[] {a, c});
		}
		
		Arrays.fill(minEdge, INF);
		minEdge[1] = 0;
		int result = 0;
		for(int c=0; c<N; c++) {
			int current = -1;
			int min = INF;
			for(int i=1; i<=N; i++) {
				if(!visited[i] && minEdge[i] < min) {
					min = minEdge[i];
					current = i;
				}
			}
			
			if(current == -1) break;
			visited[current] = true;
			result += min;
			
			for(int[] arr : adjList[current]) {
				if(!visited[arr[0]] && arr[1] < minEdge[arr[0]]) {
					minEdge[arr[0]] = arr[1];
				}
			}
		}
		
		System.out.println(result);
		
		
		
	}

}