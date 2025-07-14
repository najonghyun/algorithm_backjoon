import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * 문제 : BJ_20437_문자열게임2_나종현
 * 설명 : 처음에는 dp인줄 알았는데 도저히 될수가 없었고, 소문자들로만 이루어져 있다는 힌트로 26개 정도는 반복해도 시간초과가 나지 않는다.
 * 그래서 a부터 z까지 이제 개수를 조사하고 그 개수는 queue를 이용해서 K만큼 들어왔을때 시작과 끝의 차이로 길이를 계산해 최소값과 최대값을 구했다.(슬라이딩 윈도우 형식으로)
 * 
 * @author 나종현
 */
public class Main {
	static int MAX = 10001;
	static char[] alphabets = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = parseInt(in.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			String temp = in.readLine();
			char[] arrays = temp.toCharArray();
			int K = parseInt(in.readLine());
			
			int min = MAX, max = 0;
			int n = arrays.length;
			for(char alphabet : alphabets) {
				Queue<Integer> q = new ArrayDeque<>();
				for(int i=0; i<n; i++) {
					if(arrays[i] == alphabet) {
						q.offer(i);
						int size = q.size();
						if(size < K) {
							continue;
						}
						if(size > K) {
							q.poll();
						}
						int startI = q.peek();
						int length = i - startI + 1;
						min = Math.min(min, length);
						max = Math.max(max, length);
					}
				}
			}
			if(min == MAX && max == 0) {
				sb.append(-1).append("\n");
			}else {
				sb.append(min).append(" ").append(max).append("\n");			
			}
		}
		
		System.out.println(sb);
		
	}

}
