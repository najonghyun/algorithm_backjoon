import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 문제 : BJ_2138_전구와스위치
 * 설명 : 이것은 그리디 형식으로 (100% 이해는 안됐지만...) 앞에서 부터 차례로 비교하며 봐야한다! 왜냐면 앞에서는 이제 영향을 하나 덜 끼지므로 앞에서부터 보면서 
 * 이제 마지막 영향이 어디에 끼치는지 보고 그 때 비교해서 다르면 스위치 바꾸고 이런식으로 차례로 진행했다. 
 * 이때 모든 경우의 수를 보려면 이제 맨앞의 스위치는 두개로 나누어 눌렀을때와 안눌렀을때를 나눠서 비교했다.
 * 끝까지 본후에는 마지막 것을 보고 이제 다르면 false(될 수 없는 경우), 같으면 true(되는 경우)로 판단한다!
 * 
 * @author 나종현
 * 
 */
public class Main {
	static int INF = Integer.MAX_VALUE;
	
	public static void switchPush(int N, int[] curr, int i) {
		if(i-1 >= 0) {
			curr[i-1] = curr[i-1] == 0 ? 1 : 0;			
		}
		curr[i] = curr[i] == 0 ? 1 : 0;
		if(i+1 < N) {
			curr[i+1] = curr[i+1] == 0 ? 1 : 0;			
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		int[] curr = new int[N];
		int[] target = new int[N];
		String temp = in.readLine();
		for(int i=0; i<N; i++) {
			curr[i] = temp.charAt(i) - '0';
		}
		temp = in.readLine();
		for(int i=0; i<N; i++) {
			target[i] = temp.charAt(i) - '0';
		}
		
		int result = INF;
		// 1. 첫 번째를 누르지 않고 시작했을 때
		int[] currCopy = Arrays.copyOf(curr, N);
		int count = 0;
		for(int i=1; i<N; i++) {
			if(currCopy[i-1] != target[i-1]) {
				switchPush(N, currCopy, i);
				count++;
			}
		}
		if(currCopy[N-1] == target[N-1]) {
			result = count;
		}
		
		// 2. 첫 번째를 누르고 시작했을 때
		currCopy = Arrays.copyOf(curr, N);
		switchPush(N, currCopy, 0);		
		count = 1;
		for(int i=1; i<N; i++) {
			if(currCopy[i-1] != target[i-1]) {
				switchPush(N, currCopy, i);
				count++;
			}
		}
		if(currCopy[N-1] == target[N-1]) {
			result = Math.min(result, count);
		}
		
		System.out.println(result != INF ? result : -1);
		
	}
}