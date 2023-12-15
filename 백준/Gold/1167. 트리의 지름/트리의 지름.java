import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1167_트리의지름
 * 설명 : 메모리 고려하여 인접행렬 대신 인접리스트를 이용하였고, dfs를 통해 해결했는데, 평소같이 풀면 시간초과가 발생한다.
 * 그래서 먼저 임의의 점에서 dfs를 한번 돌리고, 최대 거리 점을 구한 후, 그 점을 기준으로  dfs를 돌리면 그게 지름이 되고 쉽게 구할 수 있다.
 * @author 나종현
 *
 */
public class Main {
	static int V;
	static ArrayList<int[]>[] adjList;
	static boolean[] isChecked;
	static int maxLength, maxNode;
	
	private static void dfs(int targetNode, int totalLength) {
		isChecked[targetNode] = true;
		
		if(totalLength > maxLength) {
			maxNode = targetNode;
			maxLength = totalLength;			
		}
		
		for(int[] node : adjList[targetNode]) {
			int nextNode = node[0];
			int nextNodeValue = node[1];
			
			if(!isChecked[nextNode]) {				
				dfs(nextNode, totalLength + nextNodeValue);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		V = parseInt(in.readLine());
		
		adjList = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<int[]>();			
		}
		
		StringTokenizer st;
		for(int i=0; i<V; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = parseInt(st.nextToken());
			
			while(true) {
				int to = parseInt(st.nextToken());
				if(to == -1) {
					break;
				}
				int value = parseInt(st.nextToken());
				adjList[from].add(new int[] {to, value});
			}	
		}
		isChecked = new boolean[V+1];
		dfs(1, 0);
		
		isChecked = new boolean[V+1];
		dfs(maxNode, 0);
		
		System.out.println(maxLength);
		
		


	}

}