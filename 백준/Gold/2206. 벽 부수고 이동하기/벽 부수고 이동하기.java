import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 : BJ_2206_벽부수고이동하기
 * 설명 : 처음에는 주옥같은 반례하나 때문에 dfs인줄 알았으나 그럼 시간초과가 떴다.
 * 해결법은 bfs로 하고 이제 반례는 방문체크를 찬스가 있을 때와 없을때로 나눠서 각각 체크를 해주는 방식으로 해결하였다. 
 * 이 문제로 최단거리는  무조건  bfs인 걸 알았고, 다른 부분에서 문제를 해결해 보아야 겠다.
 * 
 * @author 나종현
 */

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][][] isChecked;
	static int[] dirY = {-1, 1, 0, 0};
	static int[] dirX = {0 ,0, -1, 1};
	static int minDistance;
		
	private static void bfs() {
		Queue<int []> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 1, 1});
		isChecked[1][0][0] = true;
		
		while(!q.isEmpty()) {
			int nowY = q.peek()[0];
			int nowX = q.peek()[1];
			int nowDistance = q.peek()[2];
			int nowChance = q.peek()[3];
			q.poll();
			
			if(nowY == N-1 && nowX == M-1) {
				// 도착
				minDistance = nowDistance;
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nextY = nowY + dirY[d];
				int nextX = nowX + dirX[d];
				
				if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || isChecked[nowChance][nextY][nextX]) {
					continue;
				}
				
				// 벽에 막혀있고 찬스가 없다면 마찬가지로 continue
				if(map[nextY][nextX] == 1) {
					if(nowChance == 0) {
						continue;
					}
				}
				
				if(map[nextY][nextX] == 1) { // 찬스 있다면 박탈하고 넣어 줌
					q.offer(new int[] {nextY, nextX, nowDistance+1, nowChance-1});
					isChecked[nowChance-1][nextY][nextX] = true;					
				}else {
					q.offer(new int[] {nextY, nextX, nowDistance+1, nowChance});
					isChecked[nowChance][nextY][nextX] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		map = new int[N][M];
		isChecked = new boolean[2][N][M];
		
		for(int i=0; i<N; i++) {
			String temp = in.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j)-'0';
			}
		}
		minDistance = -1;
		bfs();
		
		System.out.println(minDistance);
		
	}

}