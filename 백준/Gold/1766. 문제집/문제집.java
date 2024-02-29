import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1766_문제집_나종현
 * 설명 : 일단 먼저 해야하는 선행이 있는 문제는 무조건 위상정렬(in and out)으로 생각한다. 무조건!!!
 * 순서:
 * 1. 위상 테이블이 필요할 것이므로 만들어서 값 할당
 * 2. 먼저 테이블의 값이 0인걸 넣음
 * 3. 이제 하나씩 하면서 그값에 대한 in을 빼주고, 이제 0이 된다면 큐에 넣어줌
 * 반복 끝 !!! 
 * 여기서 큐는 무조건 쉬운순으로 출력해야 하므로 우선순위 큐를 이용하여 내림처순으로 정렬하면 된다.
 *   
 * @author 나종현
 *
 */
public class Main {
	static int N, M;
	static int[] inDegree;
	static ArrayList<Integer>[] adjList;
	static StringBuilder sb;

	private static void Topology() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				pq.offer(i);
			}
		}
		
		while(!pq.isEmpty()) {
			int nowX = pq.poll();
			sb.append(nowX).append(" ");
			
			for(int i : adjList[nowX]) {
				inDegree[i]--;
				if(inDegree[i] == 0) {
					pq.offer(i);
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		inDegree = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int A = parseInt(st.nextToken());
			int B = parseInt(st.nextToken());
			adjList[A].add(B);
			inDegree[B]++;
		}
		 
		Topology();
		
		System.out.println(sb);

	}

}