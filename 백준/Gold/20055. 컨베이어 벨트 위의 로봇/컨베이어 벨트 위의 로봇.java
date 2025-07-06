import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제: 
 * 
가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 * 
 * @author 나종현
 *
 */
public class Main {
	static int N, K, upPoint, zeroCount, result;
	static int[] belt;
	static Queue<int[]> q;
	static boolean[] robot;
	
	public static void work() {
		
		while(zeroCount < K) {
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
			upPoint = upPoint - 1;
			if(upPoint < 0) upPoint = 2*N-1;	
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			int size = q.size();
			for(int i=0; i<size; i++) {
				int point = q.peek()[0];
				int count = q.peek()[1];
				q.poll();
				
				// 큐 회전
				count--;
				if(count == 0) {
					robot[point] = false;
					continue;
				}
				
				int nextPoint = point + 1;
				if(nextPoint >= 2*N)  nextPoint = 0;
				if(belt[nextPoint] > 0 && !robot[nextPoint]) {
					belt[nextPoint]--;
					if(belt[nextPoint] == 0) {
						zeroCount++;
					}
					if(count-1 == 0) {
						robot[point] = false;
						continue;
					}
					q.offer(new int[] {nextPoint, count-1});
					robot[point] = false;
					robot[nextPoint] = true;
				}else {
					q.offer(new int[] {point, count});
				}
			}
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if(belt[upPoint] > 0) {
				q.offer(new int[] {upPoint, N-1});
				belt[upPoint]--;
				if(belt[upPoint] == 0) {
					zeroCount++;
				}
				robot[upPoint] = true;
			}
			
//			System.out.println(Arrays.toString(belt));
			result++;
		}	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		K = parseInt(st.nextToken());
		belt = new int[2*N];
		robot = new boolean[2*N];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<2*N; i++) {
			belt[i] = parseInt(st.nextToken());
		}
		q = new ArrayDeque<>();
		
		work();
		System.out.println(result);
		
	}

}
