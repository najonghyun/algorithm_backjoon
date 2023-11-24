import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_16928_뱀과사다리게임
 * 설명 : bfs로 1부터 6까지 이동하고 예외 처리 해서 품
 * @author 나종현
 *
 */
public class Main {
	static int N, M;
	static int[] obstacle;
	static StringBuilder sb;
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] isChecked = new boolean[101];
		
		q.offer(new int[]{1, 0});
		isChecked[1] = true;
		
		while(!q.isEmpty()) {
			int nowLocation = q.peek()[0];
			int nowNumber = q.peek()[1];
			q.poll();
			
			if(nowLocation == 100) {
				sb.append(nowNumber);
				break;
			}
			
			for(int d=1; d<=6; d++) {
				int nextLocation = nowLocation + d;
				if(nextLocation > 100 || nextLocation <= 0 || isChecked[nextLocation]) {
					continue;
				}
				if(obstacle[nextLocation] != 0) {
					q.offer(new int[] {obstacle[nextLocation], nowNumber+1});
					isChecked[obstacle[nextLocation]] = true;
				}else {
					q.offer(new int[] {nextLocation,nowNumber+1});
					isChecked[nextLocation] = true;
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		obstacle = new int[101];
		
		for(int i=0; i<N+M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int num = parseInt(st.nextToken());
			obstacle[num] = parseInt(st.nextToken());
		}
		
		bfs();
		
		System.out.println(sb);

	}

}