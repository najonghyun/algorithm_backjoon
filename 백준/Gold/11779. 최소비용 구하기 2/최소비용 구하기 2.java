import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_11779_최소비용구하기2
 * 설명 : 일단 최소비용이라 다익스트라로 풀었고(인접행렬은 시초나서 인접리스트로 품), 거기에 최소 거리가 되는 모든 루트(도시)들을 차례대로 출력하는 것이었따.
 * 그래서 각 최소거리를 넣을때마다 들리는 곳을 path[]배열에 넣어놓고, 이제 그 패스를 도착점부터 출발지까지 차례대로 Linkedlist처럼 나아가며 출력하는 방식으로 해결하였다. 이때 출력은 출발점부터
 * 해야함으로 스택을 이용하였다.
 * @author 나종현
 *
 */
public class Main {
	static int n, m, departure, arrival;
	static ArrayList<int[]>[] adjList;
	static int[] distance;
	static int[] path;
	static Stack<Integer> s;
	static int INF = Integer.MAX_VALUE;
	
	private static void dijkstra() {
		
		distance[departure] = 0;
		boolean[] visited = new boolean[n+1];
		for(int c=0; c<n; c++) {
			int current = -1;
			int min = INF;
			for(int i=1; i<=n; i++) {
				if(!visited[i] && min > distance[i]) {
					current = i;
					min = distance[i];
				}
			}
				
			if(current == -1) break;
			visited[current] = true;
			
			for(int[] arr : adjList[current]) {
				if(distance[arr[0]] > min + arr[1]) {
					distance[arr[0]] = min + arr[1];
					path[arr[0]] = current;
				}
			}

		}
	}
	
	private static void printPath(int city) {	// path 출력하는거 (도착점부터)
		s.push(city);
		
		if(city == departure || path[city] == 0) { // 기저조건 : 출발점에 오거나 or 길이 없을 때
			return;
		}
		
		printPath(path[city]);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = parseInt(in.readLine());
		m = parseInt(in.readLine());
		adjList = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			adjList[i] = new ArrayList<>();
		}
		path = new int[n+1];
		s = new Stack<>();
		distance = new int[n+1];
		Arrays.fill(distance, INF);
		
		StringTokenizer st;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = parseInt(st.nextToken());
			int end = parseInt(st.nextToken());
			int price = parseInt(st.nextToken());
			adjList[start].add(new int[] {end, price});
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		departure = parseInt(st.nextToken());
		arrival = parseInt(st.nextToken());
		
		dijkstra();
		printPath(arrival); // 도착점 넣음
		
		System.out.println(distance[arrival]);
		System.out.println(s.size()); // 크기 출력
		while(!s.isEmpty()) {
			System.out.print(s.pop() + " "); // 각 도시 출력		
		}
		
	}

}