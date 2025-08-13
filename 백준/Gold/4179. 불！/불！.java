import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dirY = {0, 1, 0, -1};
	static int[] dirX = {1, 0, -1, 0};
	
	public static void spread(int R, int C, char[][] map, int[][] fire, int dist) {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(fire[i][j] == dist) {
					for(int d=0; d<4; d++) {
						int nextI = i + dirY[d];
						int nextJ = j + dirX[d];
						if(nextI >= R || nextI < 0 || nextJ >= C || nextJ < 0 || fire[nextI][nextJ] > 0 || map[nextI][nextJ] == '#') {
							continue;
						}
						fire[nextI][nextJ] = dist+1;
					}
				}
			}
		}
	}
	
	public static int bfs(int R, int C, char[][] map, int[] start, int[][] fire) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		
		q.offer(new int[] {start[0], start[1], 0});
		visited[start[0]][start[1]] = true;
		
		int prevDist = 0;
		while(!q.isEmpty()) {
			int nowY = q.peek()[0];
			int nowX = q.peek()[1];
			int dist = q.peek()[2];
			q.poll();
			if(prevDist != dist) {
				//불 퍼짐!
				spread(R, C, map, fire, dist);
//				for(int[] arr : fire) {
//					System.out.println(Arrays.toString(arr));
//				}
//				System.out.println();
				prevDist = dist;
			}
			// 불에 잡아 먹혀도 탈출 못함
			if(fire[nowY][nowX] > 0) {
				continue;
			}
			
			for(int d=0; d<4; d++) {
				int nextY = nowY + dirY[d];
				int nextX = nowX + dirX[d];
				if(nextY >= R || nextY < 0 || nextX >= C || nextX < 0) {
					return dist+1;
				}
				if(visited[nextY][nextX] || map[nextY][nextX] == '#' || fire[nextY][nextX] > 0) {
					continue;
				}
				q.offer(new int[] {nextY, nextX, dist+1});
				visited[nextY][nextX] = true;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int R = parseInt(st.nextToken());
		int C = parseInt(st.nextToken());
		char[][] map = new char[R][C];
		int[][] fire = new int[R][C];
		int[] start = new int[2];
		for(int i=0; i<R; i++) {
			String temp = in.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = temp.charAt(j);
				if(map[i][j] == 'F') {
					fire[i][j] = 1;
				}
				if(map[i][j] == 'J') {
					start[0] = i;
					start[1] = j;
				}
			}
		}
		
		int result = bfs(R, C, map, start, fire);
		System.out.println(result > 0 ? result : "IMPOSSIBLE");
	}
}