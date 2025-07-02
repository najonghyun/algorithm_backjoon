import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 문제 : BJ_15989_123더하기4
 * 설명: 
 * 이 문제는 DP(동적 계획법) 문제로, 점화식을 세워 풀어야 한다.
 * 핵심은 '1, 2, 3의 합으로 n을 만드는 방법의 수'를 구하는 건데,
 * 여기서 "순서가 다른 경우는 같은 것으로 간주"하므로 중복을 허용하는 조합 문제이다.

 * 만약 순서를 고려하는 문제(즉, 순열)였다면, 점화식은 다음과 같이 세울 수 있다
 *    dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]
 * 이는 각 숫자를 마지막에 붙였을 때를 기준으로 경우의 수를 나누는 방식이다.

 * 하지만 이 문제는 순서를 무시해야 하므로, 조합 방식으로 접근해야 하며, 이때는 숫자 1, 2, 3을 오름차순으로 하나씩 고려해야 합니다.
 * 점화식: for (num = 1 to 3):
 *          for (i = num to n):
 *            dp[i] += dp[i - num];
 * 이유:
 * num = 1일 때, i(=n)을 만들 수 있는 조합은 'i - 1까지 만들 수 있는 모든 조합'에 숫자 1을 하나 붙인 것과 같다.
 * 따라서 dp[i] += dp[i - 1]이 된다. 마찬가지로 num = 2일 때는 dp[i] += dp[i - 2], num = 3일 때는 dp[i] += dp[i - 3] 이다.
 * **중요 : 1 → 2 → 3 순서대로 처리해야 중복된 조합(예: 1+2, 2+1)을 하나만 세게 된다는 점
 * 
 * @author 나종현
 *
 */
public class Main {
	static int MAX = 10000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder(); 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    int T = parseInt(in.readLine());
	    int[] dp = new int[MAX+1];
	    dp[0] = 1;
	    for(int num=1; num<=3; num++) {
	    	for(int i=num; i<=MAX; i++) {
	    		dp[i] += dp[i-num]; 
	    	}	    	  
	    }
	    for(int test_case=1; test_case<=T; test_case++) {
	    	int n = parseInt(in.readLine());
	    	sb.append(dp[n]).append("\n");
    	}
	    System.out.println(sb);
	}
}
