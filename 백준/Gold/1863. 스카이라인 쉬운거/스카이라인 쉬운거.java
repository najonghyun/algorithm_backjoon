import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1863_스카이라인쉬운거_나종현 
 * 설명 : 처음에는 복잡하게 생각했지만 스택을 이용하면 편하게 값을 구할 수 있다. 이 때 마지막에 스택에 남은 높이도 건물이므로 처리해야 한다.
 *
 * @author 나종현
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		int[] building = new int[N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			st.nextToken();
			building[i] = parseInt(st.nextToken());
		}
		
		Stack<Integer> s = new Stack<>();
		s.push(0);
		int result = 0;
		for(int i=0; i<N; i++) {
			int current = s.peek();
			if(current < building[i]) {
				s.push(building[i]);
			}else if(current > building[i]) {
				while(current > building[i]) {
					s.pop();
					current = s.peek();
					result++;
				}
				// 빠져 나왔는데 서로 같지 않다면 그 값 push 해야 한다! (새로운 건물 후보이므로)
				if(current != building[i]) {
					s.push(building[i]);
				}
			}
		}
		while(!s.isEmpty()) {
			int top = s.pop();
			if(top != 0) {
				result++;				
			}
		}
		
		System.out.println(result);
	}
}