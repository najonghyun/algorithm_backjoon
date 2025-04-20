import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 제목: BJ_13459_구슬탈출_나종현
 * 설명 : bfs를 사용해  구슬을 이동시킨다.
 * + 두 구슬의 위치를 동시에 방문체크 하기 위해 4차원으로 정의했다. 또한 도착지점만 방문체크 한다.  
 *  @author 나종현
 */
public class Main {
	static boolean result;
	static int N, M;
	static char[][] board;
	static int holeX, holeY;
	static int redY, redX, blueY, blueX;
	static int[] dirY = {-1, 1, 0, 0};
	static int[] dirX = {0, 0, 1, -1};
	
	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		
		q.offer(new int[] {redY, redX, blueY, blueX, 1}); // 1
		visited[redY][redX][blueY][blueX] = true;
		
		while(!q.isEmpty()) {
			int nowRedY = q.peek()[0];
			int nowRedX = q.peek()[1];
			int nowBlueY = q.peek()[2];
			int nowBlueX = q.peek()[3];
			int nowCount = q.peek()[4];
			q.poll();
			
			if(nowCount > 10) {
				break;
			}
			
			for(int d=0; d<4; d++) {
				boolean red = false;
				boolean blue = false;
				
				// 1. 빨간색 구슬 이동
				int nextRedY = nowRedY;
				int nextRedX = nowRedX;
				while(board[nextRedY + dirY[d]][nextRedX + dirX[d]] != '#') {
					nextRedY += dirY[d];
					nextRedX += dirX[d];
					if(nextRedY == holeY && nextRedX == holeX) {
						red = true;
						break;
					}
				}
				
				// 2. 파란색 구슬 이동 
				int nextBlueY = nowBlueY;
				int nextBlueX = nowBlueX;
				while(board[nextBlueY + dirY[d]][nextBlueX + dirX[d]] != '#') {	
					nextBlueY += dirY[d];
					nextBlueX += dirX[d];
					if(nextBlueY == holeY && nextBlueX == holeX) {
						blue = true;
						break;
					}
				}

				// 3. 구슬 hole에 들어갔는지 판별
				if (blue) continue;
				if (red) {
				    result = true;
				    return;
				}

				// 4. 두 구슬이 겹치는 경우 → 이전 좌표 기준으로 더 많이 이동한 구슬을 한 칸 뒤로
				if (nextRedY == nextBlueY && nextRedX == nextBlueX) {
					int redDist = Math.abs(nowRedY - nextRedY) + Math.abs(nowRedX - nextRedX);
					int blueDist = Math.abs(nowBlueY - nextBlueY) + Math.abs(nowBlueX - nextBlueX);
					if (redDist > blueDist) {
						nextRedY -= dirY[d];
						nextRedX -= dirX[d];
					} else {
						nextBlueY -= dirY[d];
						nextBlueX -= dirX[d];
					}
				}
				
				if(!visited[nextRedY][nextRedX][nextBlueY][nextBlueX]) { // 5. 방문 체크(미끄러지는것 모두 방문 체크할 필요없이 도착지점 한번만 해도 됨!)
					visited[nextRedY][nextRedX][nextBlueY][nextBlueX] = true;
					q.offer(new int[] {nextRedY, nextRedX, nextBlueY, nextBlueX, nowCount+1});
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
		board = new char[N][M];
		for(int i=0; i<N; i++) {
			String temp = in.readLine();
			for(int j=0; j<M; j++) {
				board[i][j] = temp.charAt(j);
				if(board[i][j] == 'O') {
					holeY = i;
					holeX = j;
				}
				if(board[i][j] == 'R') {
					redY = i;
					redX = j;
				}
				if(board[i][j] == 'B') {
					blueY = i;
					blueX = j;
				}
			}
		}
		
		bfs();
		
		System.out.println(result ? 1 : 0);
		

	}

}
