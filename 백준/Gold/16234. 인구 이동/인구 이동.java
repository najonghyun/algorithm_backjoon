import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_16234_인구이동
 * 설명 : 이 문제는 bfs로 탐색하여 인구수 차이가 범위안에 들어오면 이동해서 구역을 만들고 그 구역별로 평균값을 넣어주었다.
 * 이때 넣을 값을 ArrayList에 저장하여 효율적으로 인구 수를 변경했다.
 * 
 */
public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int[] dirY = {-1, 1, 0, 0};
	static int[] dirX = {0, 0, 1, -1};
	
	public static boolean bfs(int N, int L, int R, int startY, int startX) {
		
		// 1. 이동 가능한 지역 찾기
		Queue<int[]> q = new ArrayDeque<>();
		ArrayList<int[]> union = new ArrayList<>();
		
		q.offer(new int[] {startY, startX});
		union.add(new int[] {startY, startX});
		visited[startY][startX] = true;

		int total = map[startY][startX];
		int count = 0;
		while(!q.isEmpty()) {
			int nowY = q.peek()[0];
			int nowX = q.peek()[1];
			q.poll();
			count++;
			
			for(int d=0; d<4; d++) {
				int nextY = nowY + dirY[d];
				int nextX = nowX + dirX[d];
				if(nextY >= N || nextY < 0 || nextX >= N || nextX < 0 || visited[nextY][nextX]) {
					continue;
				}
				int diff = Math.abs(map[nextY][nextX] - map[nowY][nowX]);
				if(diff >= L && diff <= R) {
					q.offer(new int[] {nextY, nextX});
					union.add(new int[] {nextY, nextX});
					visited[nextY][nextX] = true;
					total += map[nextY][nextX];
				}
			}
		}

		// 2. 범위 변함없으면 false
		if(count == 1) return false;
		
		// 3. 평균값을 구해 각 지역에 적용
		int average = total / count;
		for(int[] arr : union) {
			int nowY = arr[0];
			int nowX = arr[1];
			map[nowY][nowX] = average;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int L = parseInt(st.nextToken());
		int R = parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		
		boolean isMove = true;
		int result = 0;
		while(isMove) {
			isMove = false;
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) {
						continue;		
					}
					if(bfs(N, L, R, i, j)) {
						isMove = true;
					}
				}
			}
			if(isMove) {
				result++;				
			}

		}
		
		System.out.println(result);
		
	}
}
