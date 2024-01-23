import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1865_웜홀
 * 설명 : 일단 문제가 이상함.. 음수를 허용하는 것이라 벨만포드 알고리즘을 사용하였는데 좀 이상함..
 * 나는 일단 인접행렬로 처음에 하니깐 시간초과나서 리스트로 바꾸고 이건 다익스트라와 다르게 start를 0으로 해놓고 이제 N-1번  모든 노드를 본 후
 * 마지막으로 한번 더 보는 방식으로 해결하였다
 * @author 나종현
 *
 */
public class Main {
	static int INF = 10001; // 이것도 만약 MAX_VALUE로 놓으면 틀렸다고 함. 아마 초기 distance가 더하는 과정에서 -쓰레기값이 나와버리기 때문에
	static int N, M, W;
	static ArrayList<int[]>[] adjList;
	static int[] distance;
	
	private static boolean bellmanFord(int start) { // 벨만포드
		distance = new int[N+1];
		Arrays.fill(distance, INF); // 1. distance를 초기 INF(충분히 큰 값)으로 해놓음
		distance[start] = 0; // 2. start를 0으로 놓고

		for(int c=1; c<=N; c++) { // 3. N-1 번은 모든 인접노드들을 하나씩 보면서 새로운 distance보다 원래 distance + 새로운 인접노드의 가중치가 더 적은게 있으면 갱신 
			for(int i=1; i<=N; i++) { 
				for(int[] node : adjList[i]) {
					if(distance[node[0]] > distance[i] + node[1]){
						distance[node[0]] = distance[i] + node[1];
						if(c == N) return false;  // 4. 마지막 N번째 일 때 또 갱신한다면 이건 음수 사이클이 존재하므로 false 출력
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
			

//			boolean cycle = false; // 이런식으로 모든 정점에 대하여 벨만포드를 돌리면 시간초과가 남 ,,,, 어이없음
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
