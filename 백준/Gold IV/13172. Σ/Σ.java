import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_13172_시그마
 * 설명 : 문제 이해 빡세다. 근데 문제만 잘 이해하면 이제 거듭제곱 분할정복으로만 풀면 순조롭게 통과 할 수 있다.
 * @author 나종현
 *
 */
public class Main {
	static int M;
	static int X = 1000000007;
	
	private static long power(long num, long n) {
		if(n == 1) {
			return num;
		}
		
		long temp = power(num, n/2)%X;
		if(n % 2 == 0) {
			return (temp*temp)%X;
		}else {
			return ((temp*temp)%X*num)%X;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		M = parseInt(in.readLine());
		StringTokenizer st;
		
		// N: 분모, S: 분자, E: 기대값, result: 기대값 총합 
		long result = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int N = parseInt(st.nextToken());
			int S = parseInt(st.nextToken());
			
			long E = (S*power(N, X-2))%X;
			result = (result + E)%X;
		}
		
		System.out.println(result);

	}

}