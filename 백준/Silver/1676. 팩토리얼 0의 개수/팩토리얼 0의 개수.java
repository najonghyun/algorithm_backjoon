import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 문제 : BJ_1676_팩토리얼 0의 개수
 * 설명 : 소인수분해 해가지고 2와 5가 있는 개수만큼 0의 개수가 된다.
 * @author 나종현
 *
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		
		int two = 0;
		int five = 0;
		for(int i=1; i<=N; i++) {
			int temp = i;
			while(temp%5 == 0) {
				five++;
				temp = temp/5;
			}
			while(temp%2 == 0) {
				two++;
				temp = temp/2;
			}
			
		}
		System.out.println(Math.min(two, five));
		
		
	}
}