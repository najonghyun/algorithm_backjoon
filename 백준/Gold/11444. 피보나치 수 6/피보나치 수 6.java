import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
/**
 * 문제 : BJ_11444_피보나치수6
 * 설명 : 이건 일단  n이 미친듯이 크다. 그래서  O(N)으로도 시간초과가 난다. 그래서 O(logN)을 해야하는데 이 중 하나가 피보나치의 특징 중 하나인 
 * "도가뉴 항등식"을 사용하였다. 일단 항등식은 다음과 같다.
 * 	F(2n) = F(n) ( F(n) + 2F(n-1) )
 * 	F(2n+1) = F(n+1)^2 + F(n)^2
 * 홀수, 짝수로 나누어져 있는데 이 항등식을 이용해서 해결하였고, 재귀를 쓰는데 메모지에이션을 사용하였다. 근데 또 문제가 memo[]를 쓰기에는 배열의 index는 반드시 int만 사용이 가능하다. 그래서 1차원 배열 대신
 * Map을 사용하였다. 또한 답에  1000000007을 나누어야해서 계산 시마다 나누어 주어서 해결하였다.
 * @author 나종현
 *
 */

public class Main {
	
	static Map<Long,Long> memo;
	static long mod = 1000000007;
	
	private static long docagne(long num) { // 도가뉴 항등식
		
		if(memo.containsKey(num)) { // 메모지에이션 특징 : 키 값이 존재할때만 항등식 이용 (즉 이미 있으면 이미 있는 memo를 출력)
			return memo.get(num);
		}
		
		if(num%2 == 1) { // 홀수일 때
			long temp = (num-1)/2;
			long exp1 = docagne(temp+1)%mod;
			long exp2 = docagne(temp)%mod;
			memo.put(num, (exp1*exp1 + exp2*exp2)%mod); // F(n+1)^2 + F(n)^2
		}else {
			long temp = num/2;
			long exp1 = docagne(temp)%mod;
			long exp2 = docagne(temp-1)%mod;
			memo.put(num, (exp1 *(exp1 + 2*exp2))%mod); // F(n) ( F(n) + 2F(n-1) )
		}		
		
		return memo.get(num); // 다 구했으면 답을 출력
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long n = parseLong(in.readLine());
		memo = new TreeMap<>();
		
		memo.put((long)0, (long)0); // 처음 0,1,2 일때는 값을 미리 넣어줌
		memo.put((long)1, (long)1);
		memo.put((long)2, (long)1);
		
		System.out.println(docagne(n));

	}

}