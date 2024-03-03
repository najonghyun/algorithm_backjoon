import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N;
	static int[][] map;
	static int[] dirY = {1, 1, -1, -1};
	static int[] dirX = {1, -1, -1, 1};
	static boolean[][] isChecked;
	static int maxCountBlack, maxCountWhite;
	
	private static void layDownBlack(int row, int col, int count) {
		
		if(row >= N) { 
			maxCountBlack = Math.max(maxCountBlack, count);
			return;
		}
		
		if(col >= N) {
			if((row+1)%2 == 0) {
				layDownBlack(row+1, 0, count);				
			}else {
				layDownBlack(row+1, 1, count);				
				
			}
			return;
		}
		
		if(map[row][col] == 1) {
			if(isLaydown(row, col)) {
				isChecked[row][col] = true;
				layDownBlack(row, col+2, count+1);
				isChecked[row][col] = false;
			}
		}
		layDownBlack(row, col+2, count);
		
		
	}
	
private static void layDownWhite(int row, int col, int count) {
		
		if(row >= N) { 
			maxCountWhite = Math.max(maxCountWhite, count);
			return;
		}
		
		if(col >= N) {
			if((row+1)%2 == 0) {
				layDownWhite(row+1, 1, count);				
			}else {
				layDownWhite(row+1, 0, count);				
				
			}
			return;
		}
		
		if(map[row][col] == 1) {
			if(isLaydown(row, col)) {
				isChecked[row][col] = true;
				layDownWhite(row, col+2, count+1);
				isChecked[row][col] = false;
			}
		}
		layDownWhite(row, col+2, count);
		
		
	}
	
	
	
	private static boolean isLaydown(int y, int x) {
		for(int d=0; d<4; d++) {
			int nextY = y + dirY[d];
			int nextX = x + dirX[d];
			while(nextY < N && nextY >= 0 && nextX < N && nextX >= 0) {
				if(map[nextY][nextX] == 1 && isChecked[nextY][nextX]) {
					return false;
				}
				nextY += dirY[d];
				nextX += dirX[d];
			}
		}
		return true;
	}
	
//	private static int isChecked(int y, int x, boolean state, int size) {
//		isChecked[y][x] = state;
//		if(state) size++;
//		else size--;
//		for(int d=0; d<4; d++) {
//			int nextY = y + dirY[d];
//			int nextX = x + dirX[d];
//			while(nextY < N && nextY >= 0 && nextX < N && nextX >= 0) {
//				if(map[nextY][nextX] == 1 && isChecked[nextY][nextX] != state) {
//					isChecked[nextY][nextX] = state;
//					if(state) size++;
//					else size--;
//				}
//				nextY += dirY[d];
//				nextX += dirX[d];
//			}
//		}
//		return size;
//	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		map = new int[N][N];
		isChecked = new boolean[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		
		layDownBlack(0, 0, 0);
		layDownWhite(0, 1, 0);
		
		System.out.println(maxCountBlack + maxCountWhite);
		

	}
}