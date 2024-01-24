import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	
	static Map<Long,Long> memo;
	static long mod = 1000000007;
	
	private static long docagne(long num) {
		if(memo.containsKey(num)) {
			return memo.get(num);
		}
		
		if(num%2 == 1) {
			long temp = (num-1)/2;
			long exp1 = docagne(temp+1)%mod;
			long exp2 = docagne(temp)%mod;
			memo.put(num, (exp1*exp1 + exp2*exp2)%mod);
		}else {
			long temp = num/2;
			long exp1 = docagne(temp)%mod;
			long exp2 = docagne(temp-1)%mod;
			memo.put(num, (exp1 *(exp1 + 2*exp2))%mod);
		}		
		return memo.get(num);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long n = parseLong(in.readLine());
		memo = new TreeMap<>();
		memo.put((long)0, (long)0);
		memo.put((long)1, (long)1);
		memo.put((long)2, (long)1);
		
		System.out.println(docagne(n));

	}

}