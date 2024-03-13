import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_1953_팀배분_나종현 
 * 설명 : 이분그래프에 대해 이해해야 한다. 이분그래프는 정점들을 2개의 집합으로 나누었을 때 같은 집합에 있는 정점들은 간선이 없어야 한다.
 * 그래서 이 문제는 조건때문에 무조건 이분그래프가 된다.
 * 1. 먼저 아무거나 정점을 선택하여 dfs를 돌린다.
 * 2. 이때 인접한 정점은 다른팀으로 보낸다. 
 * 3. 새로운 방문하지 않은 그래프도 dfs를 돌리고 이건 다른 그래프와 아무런 연관이 없기 때문에 다시 아무팀에나 넣으면 된다. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N;
	static ArrayList<Integer>[] adjList;
	static boolean[] isChecked;
	static Set<Integer> blue;
	static Set<Integer> white;
	
	private static void dfs(int now, int depth) {
		if(depth % 2 == 0) {
			blue.add(now);
		}else {
			white.add(now);
		}
		isChecked[now] = true;
		
		for(int i : adjList[now]) {
			if(!isChecked[i]) {
				dfs(i, depth+1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		isChecked = new boolean[N+1];
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int size = parseInt(st.nextToken());
			for(int j=0; j<size; j++) {
				int b = parseInt(st.nextToken());
				adjList[i].add(b);
				adjList[b].add(i);
			}
		}
		
		blue = new TreeSet<>();
		white = new TreeSet<>();
		
		for(int i=1; i<=N; i++) {
			if(!isChecked[i]) {
				dfs(i, 0);							
			}
		}
		
		sb.append(blue.size()).append("\n");
		for(int i : blue) {
			sb.append(i).append(" ");
		}
		
		sb.append("\n").append(white.size()).append("\n");
		for(int i : white) {
			sb.append(i).append(" ");
		}
		
		System.out.println(sb);
		
		
		

	}

}