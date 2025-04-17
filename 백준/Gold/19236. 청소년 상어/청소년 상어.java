import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[] eatFish;
	static int result;
	static int[] dirY = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dirX = {0, -1, -1, -1, 0, 1, 1, 1};
	
	public static class Fish{
		int y;
		int x;
		int direction;
		
		public Fish(){
			this.y = -1;
			this.x = -1;
			this.direction = -1;
		}
		
		public Fish(int y, int x, int direction){
			this.y = y;
			this.x = x;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "Fish [y=" + y + ", x=" + x + ", direction=" + direction + "]";
		}
		
	}
	
	public static void copy(int[][] aMap, int[][] bMap, Fish[] aFish, Fish[] bFish) {
		for (int i=0; i<4; i++) {
			aMap[i] = bMap[i].clone();
		}
		
		for (int i=0; i<16; i++) {
			aFish[i] = new Fish(bFish[i].y, bFish[i].x, bFish[i].direction);
		}
	}
	
	public static void change(int a, int b, Fish[] fish, int[][] map) {
		int tempN = map[fish[a].y][fish[a].x];
		map[fish[a].y][fish[a].x] = map[fish[b].y][fish[b].x];
		map[fish[b].y][fish[b].x] = tempN;
		
		int tempY = fish[a].y;
		fish[a].y = fish[b].y;
		fish[b].y = tempY;
		
		int tempX = fish[a].x;
		fish[a].x = fish[b].x;
		fish[b].x = tempX;
	}
	
	public static void moveFish(int y, int x, Fish[] fish, int[][] map) {
		for(int i=0; i<16; i++) {
			if(eatFish[i]) continue; // 먹은 것은 패스
			for(int d=0; d<8; d++) {
				int newDir = (fish[i].direction+d)%8;
				int nextY = fish[i].y + dirY[newDir];
				int nextX = fish[i].x + dirX[newDir];
				if(nextY >= 4 || nextY < 0 || nextX >= 4 || nextX < 0 || (nextY == y && nextX == x)) {
					continue;
				}
				int nextNum = map[nextY][nextX];
				fish[i].direction = newDir;
				if(nextNum == -1) { // 죽은 물고기라면
					map[fish[i].y][fish[i].x] = -1;
					map[nextY][nextX] = i;
					fish[i].y = nextY;
					fish[i].x = nextX;
				}else {
					change(i, nextNum, fish, map);					
				}
				break;
			}
		}
	}
	
	public static void dfs(int y, int x, int dir, int score, Fish[] fish, int[][] map) {
		
		Fish[] fishCopy = new Fish[16];
		int[][] mapCopy = new int[4][4];
		copy(mapCopy, map, fishCopy, fish);
	    	
		// 상어가 먹이를 먹음
		eatFish[map[y][x]] = true;
		mapCopy[y][x] = -1;
		
		// 물고기 이동
		moveFish(y, x, fishCopy, mapCopy);
		
		// 갈 수 있는거 다 재귀 호출
		int goY = y + dirY[dir];
		int goX = x + dirX[dir];
		boolean move = false;
		while(goY < 4 && goY >=0 && goX < 4 && goX >= 0) {
			if(mapCopy[goY][goX] != -1 && !eatFish[mapCopy[goY][goX]]) {
				move = true;
				dfs(goY, goX, fishCopy[mapCopy[goY][goX]].direction, score+mapCopy[goY][goX]+1, fishCopy, mapCopy);				
			}
			goY += dirY[dir];
			goX += dirX[dir];
		}
		
		if(!move) {
			result = Math.max(result, score);
		}
		
		eatFish[map[y][x]] = false;
	}
	
	public static void main(String[] args) throws IOException {
		int[][] map = new int[4][4];
		Fish[] fish = new Fish[16];
		eatFish = new boolean[16];
		for(int i=0; i<16; i++) {
			fish[i] = new Fish();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<4; j++) {
				int num = parseInt(st.nextToken())-1;
				map[i][j] = num;
				fish[num].y = i;
				fish[num].x = j;
				fish[num].direction = parseInt(st.nextToken())-1;
			}
		}

		dfs(0, 0, fish[map[0][0]].direction, map[0][0]+1, fish, map);	
		System.out.println(result);
		

	}

}
