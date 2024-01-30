import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] adjMatrix;
	static int[] distance;
	static int INF = Integer.MAX_VALUE;
	
	private static void dijkstra(int start) {
		boolean[] visited = new boolean[N+1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		
		for(int c=0; c<N; c++) {
			int current = -1;
			int min = INF;
			for(int i=1; i<=N; i++) {
				if(!visited[i] && distance[i] < min) {
					current = i;
					min = distance[i];
				}
			}
			
			if(current == -1) break;
			visited[current] = true;
			
			for(int i=1; i<=N; i++) {
				if(adjMatrix[current][i] != -1 && distance[i] > min + adjMatrix[current][i]) {
					distance[i] = min + adjMatrix[current][i];
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		M = parseInt(in.readLine());
		adjMatrix = new int[N+1][N+1];
		for(int[] arr : adjMatrix) {
			Arrays.fill(arr, -1);
		}
		
		distance = new int[N+1];
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = parseInt(st.nextToken());
			int end = parseInt(st.nextToken());
			int price = parseInt(st.nextToken());
			if(adjMatrix[start][end] != -1) {
				adjMatrix[start][end] = Math.min(adjMatrix[start][end], price);				
			}else {
				adjMatrix[start][end] = price;
			}
		}
//		for(int[] arr : adjMatrix) {
//			System.out.println(Arrays.toString(arr));
//		}
		
		st = new StringTokenizer(in.readLine(), " ");
		int departure = parseInt(st.nextToken());
		int arrival = parseInt(st.nextToken());
		
		dijkstra(departure);
		
//		System.out.println(Arrays.toString(a));
		System.out.println(distance[arrival]);
		
		

	}

}