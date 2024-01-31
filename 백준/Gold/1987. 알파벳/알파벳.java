import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_1987_알파벳
 * 설명 : dfs문제이고, 재귀를 이용해서 백트레킹 방식으로 해결하였다. 이때 알파벳체크만 해도 방문체크를 할 필요는 없다. 
 * @author 나종현
 *
 */
public class Main {
	static int R, C, maxDistance;
	static char[][] board;
	static boolean[] alphabetCheck;
	static int[] dirY = {-1, 1, 0, 0};
	static int[] dirX = {0, 0, -1, 1};

	private static void dfs(int y, int x, int distance) {
		
		if(distance > maxDistance) { 
			maxDistance = distance;
		}
		
		for(int d=0; d<4; d++) {
			int nextY = y + dirY[d];
			int nextX = x + dirX[d];
			
			if(nextY >= R || nextY < 0 || nextX >= C || nextX < 0) {
				continue;
			}
			int alphabet = board[nextY][nextX] - 'A';
			if(alphabetCheck[alphabet]) {
				continue;
			}
			alphabetCheck[alphabet] = true;
			dfs(nextY, nextX, distance+1);
			alphabetCheck[alphabet] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		board = new char[R][C];
		alphabetCheck = new boolean[26];
		
		for(int i=0; i<R; i++) {
			String temp = in.readLine();
			for(int j=0; j<C; j++) {
				board[i][j] = temp.charAt(j);
			}
		}
		
		int alphabet = board[0][0] - 'A';
		alphabetCheck[alphabet] = true;
		dfs(0, 0, 1);
		
		System.out.println(maxDistance);
		

	}

}