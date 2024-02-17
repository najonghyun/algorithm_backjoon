import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

public class Main {	
	static int N, count;
	static int[][] house;
	static int[] dirY = {0, 1, 1};
	static int[] dirX = {1, 0, 1};
	static boolean[][] visited;
	
	private static void dfs(int nowY, int nowX, int state) {
		if(nowY == N-1 && nowX == N-1) {
			count++;
			return;
		}
		
		for(int d=0; d<3; d++) {
			if(state == 0) {
				if(d == 1) {
					continue;					
				}
			}else if(state == 1) {
				if(d == 0) {
					continue;
				}
			}
			int nextY = nowY + dirY[d];
			int nextX = nowX + dirX[d];
			
			if(nextY >= N || nextY < 0 || nextX >= N || nextX < 0 || house[nextY][nextX] == 1) {
				continue;
			}
			if(d == 2) {
				if(house[nextY-1][nextX] == 1 || house[nextY][nextX-1] == 1) {
					continue;
				}
			}
			
			visited[nextY][nextX] = true;
			dfs(nextY, nextX, d);
			visited[nextY][nextX] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		house = new int[N][N];
		visited = new boolean[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				house[i][j] = parseInt(st.nextToken());
			}
		}
		
		visited[0][1] = true;
		dfs(0, 1, 0);
		
		System.out.println(count);
		
	}

}