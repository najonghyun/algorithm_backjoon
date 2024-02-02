import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_4991_로봇청소기
 * 설명 : 전에 했다가 시간초과 났는데 날만했다. 순열 + bfs 였는데 중복되는게 너무 많았다.
 * 그래서 이번에는 중복되는 계산을 줄이고자  minMatrix[][] 행렬에 최소로 청소기에서 각 먼지  and 먼지에서 먼지 최소 이동 길이를 미리 계산해서 넣어 주었다.
 * 그리고 순열을 따로 구해서 그 순열에 따라  minMatrix[][]을 차례로 방문하여 최소값을 구하는 방식으로 해결하였다.
 * @author 나종현
 *
 */
public class Main {
	static char[][] map;
	static int w, h, dirtyCount, result;
	static int[][] minMatrix; // 0은 시작점, 1,2,3,...은 각 먼지 의 최소 거리 매트릭스
	static int[][] input; // input[0] : 로봇청소기 위치, input[1~] : 먼지 위치
	static int[] numbers; // numbers[] 순열 넣어놓기 위한 배열
	static boolean[] visited; // 순열 방문체크
	static boolean canClean; // canClean : bfs 다 구해놓고 청소기가 먼지를 방문할 수 있는지 유무 (막혀있으면 -1 출력해야 하니깐!)
	
	static int[] dirY = {-1, 0, 1, 0};
	static int[] dirX = {0, 1, 0, -1};
	
	private static void perm(int cnt) { // 순열
		if(cnt == dirtyCount) {
			int temp = 0;
			for(int i=0; i<dirtyCount; i++) {
				temp += minMatrix[numbers[i]][numbers[i+1]];					
			}
			result = Math.min(result, temp);
			return;
		}
		
		for(int i=1; i<=dirtyCount; i++) {
			if(visited[i]) {
				continue;
			}
			numbers[cnt+1] = i;
			visited[i] = true;
			perm(cnt+1);
			visited[i] = false;
		}
	}
	
	
	private static void bfs(int nowPosition, int count) { // count : 총 몇개 방문해야 하는지
		Queue<int []> q = new ArrayDeque<>();
		boolean[][] isChecked = new boolean[h][w];
		
		// 시작 위치 큐에 담음
		q.add(new int[] {input[nowPosition][0], input[nowPosition][1], 0});
		isChecked[input[nowPosition][0]][input[nowPosition][1]] = true;
		
		while(!q.isEmpty()) {
			int nowY = q.peek()[0];
			int nowX = q.peek()[1];
			int cnt = q.peek()[2];
			q.poll();
		
			// 더러운 곳을 찾았을 때
			for(int i=nowPosition; i<dirtyCount; i++) {
				if(nowY == input[i+1][0] && nowX == input[i+1][1]) {
					minMatrix[nowPosition][i+1] = minMatrix[i+1][nowPosition] = cnt;
					count--;
					if(count == 0) { // 다 방문했으면 더이상 bfs 진행 안해도 되므로 끝냄
						return;
					}
				}
			}
			
			for(int d=0; d<4; d++) {
				int nextY = nowY + dirY[d];
				int nextX = nowX + dirX[d];
				
				// 범위 벗어나면 
				if(nextY >= h || nextY < 0 || nextX >= w || nextX < 0) {
					continue;
				}
				// 이미 방문했으면 or 가구를 만나면
				if(isChecked[nextY][nextX] || map[nextY][nextX] == 'x') {
					continue;
				}
				// 만족하면 추가
				q.add(new int[] {nextY, nextX, cnt+1});
				isChecked[nextY][nextX] = true;
			}
			
		}
		canClean = false; // 저 위에 return 부분을 방문 안했다는건 하나라도 못가는 곳이 있다는 것이므로 청소 실패
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) {
				break;
			}
			
			map = new char[h][w];
			input = new int[11][2];
			result = Integer.MAX_VALUE;
			dirtyCount = 0;
			for(int i=0; i<h; i++) {
				String temp = in.readLine();
				for(int j=0; j<w; j++) {
					map[i][j] = temp.charAt(j);
					
					// 로봇 청소기 시작 위치 담아놓음
					if(map[i][j] == 'o') {
						input[0][0] = i;
						input[0][1] = j;
					}
					
					// 더러운 칸의 개수 및 위치 담아놓음
					if(map[i][j] == '*') {
						input[++dirtyCount][0] = i;
						input[dirtyCount][1] = j;
					}
				}
			}
			
			numbers = new int[dirtyCount+1];
			visited = new boolean[dirtyCount+1];
			minMatrix = new int[dirtyCount+1][dirtyCount+1];
			canClean = true;
			
			for(int i=0; i<dirtyCount; i++) { // 0(로봇청소기 위치)부터 모든 거리 다 구해놓음
				bfs(i, dirtyCount-i);				
			}

			if(canClean) { // 먼지를 모두 방문할 수 있을 때만
				perm(0);				
			}
			
			sb.append(result != Integer.MAX_VALUE ? result : -1).append("\n");
			
		}
		
		System.out.println(sb);
		

	}

}