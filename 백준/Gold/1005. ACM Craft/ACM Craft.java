import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, minTime, count;
	static int[] construcTime;
	static ArrayList<Integer>[] adjList;
	static int[] inDegree;
	static int[] dp;
	
	private static void Topology(int targetB) {
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				q.offer(new int[] {i, construcTime[i]});
			}
		}
		
		while(!q.isEmpty()) {
			int nowB = q.peek()[0];
			int nowTime = q.peek()[1];
			q.poll();
			
			if(nowB == targetB) {
//				System.out.println("nowTime : " + nowTime);
//				count -= 1;
				
//				minTime = Math.max(minTime, nowTime);
//				if(--count < 0) {
//					return;
//				}
				return;
			}
			
			// 진입차수 테이블 업데이트
			int size = adjList[nowB].size();
			for(int i=0; i<size; i++) {
				int temp = (int) adjList[nowB].get(i);
				inDegree[temp] -= 1;
				dp[temp] = Math.max(dp[temp], dp[nowB]+construcTime[temp]);
				if(inDegree[temp] == 0) {
					q.offer(new int[] {temp, dp[nowB]+construcTime[temp]});
					
				}

			}
			
//			System.out.println(Arrays.toString(inDegree));
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = parseInt(in.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			
			StringTokenizer st;
			st = new StringTokenizer(in.readLine());
			N = parseInt(st.nextToken());
			K = parseInt(st.nextToken());
			
			construcTime = new int[N+1];
			inDegree = new int[N+1];
			adjList = new ArrayList[N+1];
			dp = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(in.readLine());
			for(int i=1; i<=N; i++) {
				int time = parseInt(st.nextToken());
				construcTime[i] =time;
				dp[i] = time;
			}
			
			for(int i=1; i<=K; i++) {
				st = new StringTokenizer(in.readLine());
				int start = parseInt(st.nextToken());
				int end = parseInt(st.nextToken());
				inDegree[end] += 1;
				adjList[start].add(end);
			}
			
			int targetB = parseInt(in.readLine());
			
			count = inDegree[targetB];
			minTime = 0;
			Topology(targetB);
			sb.append(dp[targetB]).append("\n");

		}
		System.out.println(sb);
	}

}