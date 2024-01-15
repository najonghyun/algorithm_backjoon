import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<int[]>[] adjList;
	static boolean[] visited;
	static int maxDiameter, centerNode;
	private static void dfs(int nextNode, int nowDist) {		
		visited[nextNode] = true;
		if(nowDist > maxDiameter) {
			centerNode = nextNode;
			maxDiameter = nowDist;
		}
		for(int[] node  : adjList[nextNode]) {
			if(!visited[node[0]]) {
				dfs(node[0], nowDist + node[1]);				
			}
		}
		visited[nextNode] = false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		StringTokenizer st;
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int parent = parseInt(st.nextToken());
			int child = parseInt(st.nextToken());
			int dist = parseInt(st.nextToken());
			adjList[parent].add(new int[] {child, dist});
			adjList[child].add(new int[] {parent, dist});
		}
		
		centerNode = 1;
		for(int i=0; i<2; i++) {
			visited = new boolean[N+1];
			dfs(centerNode, 0);			
		}
		
		System.out.println(maxDiameter);

	}

}