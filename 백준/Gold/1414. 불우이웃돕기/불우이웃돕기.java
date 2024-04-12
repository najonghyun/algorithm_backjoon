import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
	static int INF = Integer.MAX_VALUE;
	
	private static int transform(char c) {
		if(c == '0') {
			return 0;
		}else {
			int num = (int)c;
			if(num > 95) {
				return num-96;
			}else {
				return num-38;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		ArrayList<int[]>[] adjList = new ArrayList[N];
		for(int i=0; i<N; i++) {
			adjList[i] = new ArrayList<>();
		}
				
		int sum = 0;
		for(int i=0; i<N; i++) {
			String temp = in.readLine();
			for(int j=0; j<N; j++) {
				int num = transform(temp.charAt(j));
				sum += num;
				if(i == j || num == 0) {
					continue;
				}
				adjList[i].add(new int[] {j, num});
				adjList[j].add(new int[] {i, num});
			}
		}
		
		boolean[] visited = new boolean[N];
		int[] minEdge = new int[N];
		Arrays.fill(minEdge, INF);
		minEdge[0] = 0;
		int result = 0;
		for(int c=0; c<N; c++) {
			int current = -1;
			int min = INF;
			for(int i=0; i<N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					current = i;
					min = minEdge[i];
				}
			}
		
			if(current == -1) break;
			visited[current] = true;
			result += min;
			
			for(int[] arr : adjList[current]) {
				if(!visited[arr[0]] && minEdge[arr[0]] > arr[1]) {
					minEdge[arr[0]] = arr[1];
				}
			}
			
		}
		
		boolean isAll = true;
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				isAll = false;
			}
		}
//		System.out.println(Arrays.toString(minEdge));
		
		System.out.println(isAll ? sum-result : -1);
	}

}