import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1629_곱셈_나종현
 * 설명 : 이 문제는 O(N)으로는 풀 수 없고, 분할정복 거듭제곱을 이용하여 O(logN)에 풀어야 한다.
 * 분할정복 거듭제곱은 원래 n을 계속 반으로 분할하여 더이상 쪼개지지 않을 때의 기저조건을 설정하고, 이제 다시 합(정복)해가면서 해결한다.
 * @author 나종현
 *
 */
public class Main {
	static long A, B, C;
	
	private static Long pow(Long num, Long n) {
		if(n <= 1) { // 기저조건 : n이 더이상 쪼개지지 않으면 num 출력
			return num%C;
		}
		
		Long temp = (pow(num, n/2))%C; // 반 쪼갬
		
		if(n % 2 == 0) { // 그랬을 때 짝수이면
			return (temp * temp) %C; // 걍 다시 2배 해주고 
		}else { // 홀수이면
			return (temp * temp)%C * num%C; // 2배한거에 소실된 값 num 더 곱해주기
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		A = parseLong(st.nextToken());
		B = parseLong(st.nextToken());
		C = parseLong(st.nextToken());
		
		System.out.println(pow(A%C, B)%C);
		
		
		
	}

}