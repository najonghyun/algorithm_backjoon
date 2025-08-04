import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 설명: 이 문제는 기존 이분 탐색이랑 다르게 target을 찾는데 이제 포인터처럼 하나씩 커지거나 줄어들면서 타겟으로 접근하는 탐색이다.  (이분 탐색의 포인터 버전)
 * 이 때 자기자신이 들어있으면 안되므로 자기자신의 index가 들어오면 자동으로 하나더 줄이든 키우든 설정해줘야 한다.
 * 투포인터로 (target은 고정하고, 나머지 투포인터로 잡아서) O(N^2)까지 줄임
 */
public class Main {
	
	// 약간은 다른 느낌의 이분탐색 포인터 버전
	public static boolean isSearch(int N, int[] array, int target, int me) {
		int start = 0;
		int end = N-1;
		while(start < end) {
			if(start == me) {
				start++;
				continue;
			}else if(end == me) {
				end--;
				continue;
			}
			int sum = array[start] + array[end];
			if(target == sum) {
				return true;
			}else if(target >= sum) {
				start++;
			}else {
				end--;
			}
		}

		return false;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		int[] array = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			array[i] = parseInt(st.nextToken());
		}
		Arrays.sort(array);

		int result = 0;
		for(int i=0; i<N; i++) {
			int target = array[i];
			if(isSearch(N, array, target, i)) {
				result++;
			}
		}
		
		System.out.println(result);

	}
}