import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 제목 : BJ_19237_어른상어_나종현
 * 설명 : bfs 같지만 그냥 한턴씩 보는 구현문제이다.
 * 1. 현재 상어가 있는 곳 냄새 방출
 * 2. 상어 하나씩 이동
 * 3. 빈 곳의 자리가 없다면 이제 내 냄새쪽으로 가야 함! (주의: 이 때도 우선순위대로 해야함!!)   
 * 4. 냄새 업데이트
 * 5. 1000번의 시간동안 반복 
 * 
 * @author 나종현
 *
 */
public class Main {
	static int N, M, k;
	static int[][] map;
	static Shark[] shark;
	static int[][][] priority;
	static int[] dirY = {-1, 1, 0, 0};
	static int[] dirX = {0, 0, -1, 1};
	static int result;
	
	public static class Shark{
		int y;
		int x;
		int direction;
		
		public Shark() {
			this.y = -1;
			this.x = -1;
			this.direction = -1;
		}
	}

	public static void moveShark() {
		boolean[] disabled = new boolean[M]; // 먹힌 상어 표시하기 위한 체크
		int rest = M;
		
		Queue<int[]> smellQ = new ArrayDeque<>(); // 냄새 좌표 및 시간 보관소
		int[][] smellNum = new int[N][N]; // 빈 곳이 모두 막혔을 때 자기 냄새 있는 곳으로 가기 위한 방문체크
		
		int time = 0;
		while(time <= 1000) { // 1000번 동안
			// 기저조건 : rest=1이면 무조건 1번만 남았으므로 답 리턴
			if(rest == 1) {
				result = time;
				return;
			}
			// 1. 현재 상어가 있는 곳 냄새 방출
			for(int num=0; num<M; num++) {
				if(disabled[num]) continue; // 이 때 먹힌 상어는 보지 않음
				smellQ.offer(new int[] {num, shark[num].y, shark[num].x, k});
				smellNum[shark[num].y][shark[num].x] = num+1;
			}
			// 2. 상어 하나씩 이동
			for(int num=0; num<M; num++) {
				if(disabled[num]) continue; // 이 때도 먹힌 상어는 보지 않음
				boolean move = false;
				int nowY = shark[num].y;
				int nowX = shark[num].x;
				int nowDirection = shark[num].direction;
				for(int d : priority[num][nowDirection]) {
					int nextY = nowY + dirY[d];
					int nextX = nowX + dirX[d];
					if(nextY >= N || nextY < 0 || nextX >= N || nextX < 0 || smellNum[nextY][nextX] != 0) continue;
					shark[num].y = nextY;
					shark[num].x = nextX;
					shark[num].direction = d;
					map[nowY][nowX] = 0;
					// 2-1. 상어끼리 만났을 때
					if(map[nextY][nextX] != 0) {
						if(num+1 > map[nextY][nextX]){ // 더 숫자가 작은 상어가 먹음!
							disabled[num] = true;
						}else {
							disabled[map[nextY][nextX]-1] = true;
							map[nextY][nextX] = num+1;
						}
						rest--;
					}else { // 2-2. 상어끼리 만나지 않았을 때
						map[nextY][nextX] = num+1;
					}
					move = true;
					break;
				}
				if(!move) { // 3. 빈 곳의 자리가 없다면 이제 내 냄새쪽으로 가야 함! (주의: 이 때도 우선순위대로 해야함!!)   
					for(int d : priority[num][nowDirection]) {
						int nextY = nowY + dirY[d];
						int nextX = nowX + dirX[d];
						if(nextY >= N || nextY < 0 || nextX >= N || nextX < 0 || smellNum[nextY][nextX] != num+1) continue;
						shark[num].y = nextY;
						shark[num].x = nextX;
						shark[num].direction = d;
						map[nowY][nowX] = 0;
						map[nextY][nextX] = num+1;
						break;
					}
				}
			}
			// 4. 냄새 업데이트
			int size = smellQ.size();
			for(int i=0; i<size; i++) {
				int nowNum = smellQ.peek()[0];
				int nowY = smellQ.peek()[1];
				int nowX = smellQ.peek()[2];
				int nowK = smellQ.peek()[3];
				smellQ.poll();
				smellNum[nowY][nowX] = 0; // 이렇게 0으로 초기화 시키고
				nowK--;
				if(nowK > 0) {
					smellQ.offer(new int[] {nowNum, nowY, nowX, nowK});
					smellNum[nowY][nowX] = nowNum+1; // 다시 새로 넣어줘야만 냄새가 겹칠 경우 업데이트가 됨! 
				}
			}
			// 5. 시간 ++
			time++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		k = parseInt(st.nextToken());
		map = new int[N][N];
		shark = new Shark[M];
		for(int i=0; i<M; i++) {
			shark[i] = new Shark();
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");			
			for(int j=0; j<N; j++) {
				int num = parseInt(st.nextToken());
				map[i][j] = num;
				if(num != 0) {
					shark[num-1].y = i;
					shark[num-1].x = j;
				}
			}
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<M; i++) {
			shark[i].direction = parseInt(st.nextToken())-1;
		}
		
		// 우선순위 각 상어당 위,아래,왼,우 각각 4개씩 넣어야하므로 3차원으로 정의
		priority = new int[M][4][4];
		for(int k=0; k<M; k++) {
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j=0; j<4; j++) {
					priority[k][i][j] = parseInt(st.nextToken())-1;					
				}
			}
		}
		
		result = -1;
		moveShark();
		System.out.println(result);
		
	}

}
