import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<int[]>[] adjList;
	static int MAX = Integer.MAX_VALUE;
	static int[] foxDist;
	static int[][] wolfDist;
	
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
	
	static public void dijkstraWolf() {
		boolean[][] visited = new boolean[2][N];
		
		for(int i=0; i<2; i++) {
			Arrays.fill(wolfDist[i], MAX);			
		}
		wolfDist[1][0] = 0;
		
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
//			System.out.println("Fox : " + foxDist[i]);
//			System.out.println("Wolf : " + minFox);
			if(foxDist[i] < minFox) {
				result++;
			}
		}
		
		System.out.println(result);
		
		
	}

}
