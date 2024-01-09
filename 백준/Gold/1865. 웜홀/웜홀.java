import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int INF = 10001;
	static int N, M, W;
	static ArrayList<int[]>[] adjList;
	static int[] distance;
	
	private static boolean bellmanFord(int start) {
		distance = new int[N+1];
		Arrays.fill(distance, INF);
		distance[start] = 0;

		for(int c=1; c<=N; c++) {
			for(int i=1; i<=N; i++) {
				for(int[] node : adjList[i]) {
					if(distance[node[0]] > distance[i] + node[1]){
						distance[node[0]] = distance[i] + node[1];
						if(c == N) return false;
					}						
				}
			}
			
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = parseInt(in.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case=0; test_case<TC; test_case++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = parseInt(st.nextToken());
			M = parseInt(st.nextToken());
			W = parseInt(st.nextToken());
			adjList = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<int[]>();
			}
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int S = parseInt(st.nextToken());
				int E = parseInt(st.nextToken());
				int T = parseInt(st.nextToken());
				adjList[S].add(new int[] {E, T});
				adjList[E].add(new int[] {S, T});
//				adjMatrix[S][E] = adjMatrix[E][S] = T;
			}
			for(int i=1; i<=W; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int S = parseInt(st.nextToken());
				int E = parseInt(st.nextToken());
				int T = parseInt(st.nextToken());
				adjList[S].add(new int[] {E, -T});
//				adjMatrix[S][E] = -T;
			}
			
//			boolean cycle = false;
//			for(int i=1; i<=N; i++) {
//				if(!bellmanFord(i)) {
//					sb.append("YES").append("\n");
//					cycle = true;
//					break;
//				}
//			}
//			if(!cycle) {
//				sb.append("NO").append("\n");
//			}
		
			if(!bellmanFord(1)) {
				sb.append("YES").append("\n");
			}else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
	}
}