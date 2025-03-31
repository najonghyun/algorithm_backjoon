import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 제목 : BJ_1611_달빛여우_나종현
 * 설명 : 일단 최소로 가야한다고 했으므로  최소 경로 알고리즘을 사용해야 한다. 여기에 늑대가 이제 2배로 한번 절반으로 한번 간다고 했는데 일단 이거 편하게 하려면
 * 각각 2배 이벤트 해주면 좋을 것같다. 그리고 distance를 늑대는 2차원으로 해서 이제 0: 느림, 1: 빠름을 맡고 distance를 넣어줄 때 반대의 distance에
 * 넣어주는 방식으로 하면 간단히 해결할 수 있다. 
 * * N과 M이 크므로 인럽행렬 보다는 인접리스트를 사용하였다. 
 * @author 나종현
 *
 */
public class Main {
	static int N;
	static ArrayList<int[]>[] adjList;
	static int MAX = Integer.MAX_VALUE;
	static int[] foxDist;
	static int[][] wolfDist;
	
	// 여우 다익스트라
	static public void dijkstraFox() {
		boolean[] visited = new boolean[N];
		Arrays.fill(foxDist, MAX);
		foxDist[0] = 0;
		
		for(int c=0; c<N; c++) {
			int min = MAX;
			int current = -1;
			for(int i=0; i<N; i++) {
				if(!visited[i] && min > foxDist[i]) {
					current = i;
					 min = foxDist[i];
				}
			}
			
			if(current == -1) break;
			visited[current] = true;
			
			for(int[] arr : adjList[current]) {
				int next = arr[0];
				int cost = arr[1] * 2;
				if(foxDist[next] > min + cost) {
					foxDist[next] = min + cost;
				}
			}
		}
	}
	
	// 늑대 다익스트라
	static public void dijkstraWolf() {
		boolean[][] visited = new boolean[2][N];
		
		for(int i=0; i<2; i++) {
			Arrays.fill(wolfDist[i], MAX);			
		}
		wolfDist[1][0] = 0; // 처음 출발은 빠르게 시작
		
		for(int c=0; c<2*N; c++) {
			int min = MAX;
			int current = -1;
			int state = -1;
			
			for(int i=0; i<2; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && min > wolfDist[i][j]) {
						min = wolfDist[i][j];
						current = j;
						state = i;
					}					
				}
			}
			
			if(current == -1) break;
			visited[state][current] = true;
			
			// 반대의 상태에 각각의 비용을 넣어준다
			for(int[] arr : adjList[current]) {
				int next = arr[0];
				int cost = arr[1];
				
				int nextState = 1-state;
				int newCost = (state == 1) ? cost : cost * 4;
				if(wolfDist[nextState][next] > min + newCost) {
					wolfDist[nextState][next] = min + newCost;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		adjList = new ArrayList[N];
		for(int i=0; i<N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = parseInt(st.nextToken())-1;
			int end =  parseInt(st.nextToken())-1;
			int distance =  parseInt(st.nextToken());
			adjList[start].add(new int[]{end, distance});
			adjList[end].add(new int[]{start, distance});
		}
		
		foxDist = new int[N];
		wolfDist = new int[2][N]; // 0:느림, 1:빠름
		dijkstraFox();
		dijkstraWolf();
		
		int result = 0;
		for(int i=1; i<N; i++) {
			int minFox = Math.min(wolfDist[0][i], wolfDist[1][i]);
			if(foxDist[i] < minFox) {
				result++;
			}
		}
		
		System.out.println(result);
		
		
	}

}
