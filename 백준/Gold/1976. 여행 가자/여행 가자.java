import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, size;
	static ArrayList<Integer>[] adjList;
	static boolean[] citys;
	
	private static int bfs(int start) {
		int count = size;
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int now = q.peek();
			q.poll();
			
			if(citys[now]) {
				count--;
			}
			
			for(int i : adjList[now]) {
				if(visited[i]) {
					continue;
				}
				q.offer(i);
				visited[i] = true;
			}
			
		}
		
		return count;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		int M = parseInt(in.readLine());
		adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=N; j++) {
				int temp = parseInt(st.nextToken());
				if(temp == 1) {
					adjList[i].add(j);
					adjList[j].add(i);					
				}
			}
			
		}
		
		citys = new boolean[N+1];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<M; i++) {
			int temp = parseInt(st.nextToken());
			if(!citys[temp]) {
				citys[temp] = true;
				size++;
			}
		}
		

		boolean can = false;
		
		for(int i=1; i<=N; i++) {
			if(citys[i]) {
//				System.out.println(i);
//				System.out.println(size);
				int count = bfs(i);
//				System.out.println(count);
				if(count == 0) {
					can = true;
					break;
				}
			}
		}
		
		System.out.println(can ? "YES" : "NO");
		
		
		
		
		
	}

}