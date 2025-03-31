import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] paper;
	static boolean[][] visited;
	static int[] colorPaper;
	static int result;
	static int MAX = Integer.MAX_VALUE;
	
	public static boolean isStick(int y, int x, int size) {
		if(y+size > 10 || x+size > 10) return false;
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				if(visited[i][j] || paper[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void stick(int y, int x, int size) {
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				visited[i][j] = true;
			}
		}
	}
	
	public static void reStick(int y, int x, int size) {
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static void backtracking(int y, int x, int num, int count) {
		
		if(count == 0) { // 기저조건 : 나와있는 것은 모두 돌았을 때 
			result = Math.min(result, num);
			return;
		}
		
		if(num > result) { // 가지치기
			return;
		}
		
		for(int i=y; i<10; i++) {
			for(int j=(y != i ? 0 : x); j<10; j++) {
				if(paper[i][j] == 1 && !visited[i][j]) {
					for(int k=5; k>0; k--) {
						if(isStick(i, j, k)) {
							if(colorPaper[k-1]-1 < 0) continue;
							colorPaper[k-1]--;
							stick(i, j, k);
							backtracking(i, j+1, num+1, count-(k*k));
							reStick(i, j, k);
							colorPaper[k-1]++;	
						}
					}
					return;
				}
			}
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		paper = new int[10][10];
		visited = new boolean[10][10];
		int count = 0;
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<10; j++) {
				paper[i][j] = parseInt(st.nextToken());
				if(paper[i][j] == 1) count++;
			}
		}
		colorPaper = new int[5];
		Arrays.fill(colorPaper, 5);
		result = MAX;
		backtracking(0, 0, 0, count);
		System.out.println(result == MAX ? -1 : result);
	}

}
