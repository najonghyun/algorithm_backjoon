import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] board;
	static int[][] maxMemo;
	static int[][] minMemo;
	static int[] dirX = {-1, 0, 1};
	static int INF = Integer.MAX_VALUE;
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		maxMemo[0][0] = board[0][0];
		maxMemo[0][1] = board[0][1];
		maxMemo[0][2] = board[0][2];
		minMemo[0][0] = board[0][0];
		minMemo[0][1] = board[0][1];
		minMemo[0][2] = board[0][2];
		q.offer(new int[] {0, 0});
		q.offer(new int[] {0, 1});
		q.offer(new int[] {0, 2});
		
		while(!q.isEmpty()) {
			int nowY = q.peek()[0];
			int nowX = q.peek()[1];
			q.poll();
		
			for(int d=0; d<3; d++) {
				int nextY = nowY + 1;
				int nextX = nowX + dirX[d];
				
				if(nextX >= 3 || nextX < 0 || nextY >= N) {
					continue;
				}
				if(maxMemo[nextY][nextX] != -1) {
					maxMemo[nextY][nextX] = Math.max(maxMemo[nextY][nextX], maxMemo[nowY][nowX]+board[nextY][nextX]);
					minMemo[nextY][nextX] = Math.min(minMemo[nextY][nextX], minMemo[nowY][nowX]+board[nextY][nextX]);
					continue;
				}
				
				maxMemo[nextY][nextX] = maxMemo[nowY][nowX] + board[nextY][nextX];
				minMemo[nextY][nextX] = minMemo[nowY][nowX] + board[nextY][nextX];
				q.offer(new int[] {nextY, nextX});
				
			}
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		board = new int[N][3];
		maxMemo = new int[N][3];
		minMemo = new int[N][3];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<3; j++) {
				board[i][j] = parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			Arrays.fill(maxMemo[i], -1);
			Arrays.fill(minMemo[i], INF);
		}
		
		bfs();
		
		int maxScore = -1;
		int minScore = INF;
		for(int i=0; i<3; i++) {
			maxScore = Math.max(maxScore, maxMemo[N-1][i]);
			minScore = Math.min(minScore, minMemo[N-1][i]);
		}
		
		System.out.print(maxScore + " " + minScore);
	}

}