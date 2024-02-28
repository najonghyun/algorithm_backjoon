import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1647_도시분할계획_나종현
 * 설명 : 일단 이건 모든 노드를 단 한번씩만 지나면서 모든 노드를 지나게 하는 최소 신장 트리를 구해야한다. 나는 그중에서도 프림 알고리즘을 채택하였다.
 * 하지만 이문제는 인접행렬로 풀게되면 메모리초과가 나므로 인접리스트형태로 풀었고, 
 * 또 일반적인 프림 알고리즘으로는 시간초과가 나므로 우선순위 큐를 이용하여 최소 거리를 앞으로 빼서 모든 노드를 좀 더 빨리 방문할 수 있게끔 구현하여 해결하였다.
 * @author 나종현
 *
 */
public class Main {
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int INF = Integer.MAX_VALUE;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		ArrayList<int[]>[] adjList = new ArrayList[N];
		
		
		for(int i=0; i<N; i++){
			adjList[i] = new ArrayList<>(); 
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int A = parseInt(st.nextToken())-1;
			int B = parseInt(st.nextToken())-1;
			int C = parseInt(st.nextToken());
			adjList[A].add(new int[] {B, C});
			adjList[B].add(new int[] {A, C});
		}
		
		int[] minEdge = new int[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(minEdge, INF);
		
		int result = 0;
		minEdge[0] = 0;
		int maxDistance = 0;
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) { // 두번째 (거리) 오름차순
				return o1[1] - o2[1];
			}
		});
		pq.offer(new int[] {0, 0});
		int count = 0;
		while(!pq.isEmpty()) {
			
			int current = pq.peek()[0];
			int min = pq.peek()[1];
			pq.poll();
			
			if(visited[current]) continue;

			visited[current] = true;
			result += min;
			maxDistance = Math.max(maxDistance, min);
			count++; // 여기가 기준점
			if(count == N) break; // 이 때 count를 설정하여 n번만 반복하고 빠져나오도록 한다.
			
			for(int[] arr : adjList[current]) {
				if(!visited[arr[0]] && minEdge[arr[0]] > arr[1]) {
					pq.offer(new int[] {arr[0], arr[1]});
					minEdge[arr[0]] = arr[1];
				}
			}
		}

		System.out.println(result-maxDistance);

	}

}