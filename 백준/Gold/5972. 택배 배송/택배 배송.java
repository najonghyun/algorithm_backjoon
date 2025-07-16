import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 문제: BJ_5972_택배배송
 * 설명: 일반적인 최단 경로 문제라 다익스트라를 사용했다. 하지만 N이 50000이라서 일반 다익스트라를 적용하면 O^(2) 라 시간초과가 난다.
 * 그래서 우선순위 큐를 사용해 비용이 작은 것부터 처리했다.
 * 
 * @author 나종현
 */
public class Main {
	static int MAX = Integer.MAX_VALUE;
	
	public static int dijkstra(ArrayList<int[]>[] adjList, PriorityQueue<int[]> pq, int N, int start) {
		int[] distance = new int[N+1];
		
		Arrays.fill(distance, MAX);
		distance[start] = 0;
		pq.offer(new int[] {start, distance[start]});
		
		while(!pq.isEmpty()) {
			int current = pq.peek()[0];
			int min = pq.peek()[1];
			pq.poll();
			
			for(int[] arr : adjList[current]) {
				int next = arr[0];
				int cow = arr[1];
				if(distance[next] > min + cow) {
					distance[next] = min + cow;
					pq.offer(new int[] {next, distance[next]}); // 최소일때만 넣어줌!
				}
			}
		}
		
		return distance[N];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		
		ArrayList<int[]>[] adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			int c = parseInt(st.nextToken());
			adjList[a].add(new int[] {b, c});
			adjList[b].add(new int[] {a, c});
		}
		
		// 비용 오름차순
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		int result = dijkstra(adjList, pq, N, 1);
		System.out.println(result);
		
	}
}
