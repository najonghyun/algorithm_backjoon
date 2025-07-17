import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_2467_용액
 * 설명 : 같은 배열에서 두 수의 합을 비교하는 문제라 전형적인 투포인터 문제이다.
 * 오름차순 되어있을 때, 시작위치를 양끝에서부터 비교하여 시간복잡도를 줄이는 방식이다.
 * 
 * @author 나종현
 * 
 */
public class Main {
	static long MAX = Long.MAX_VALUE;
	
	public static int[] twoPointer(int[] liquids, int N) {
		int start = 0;
		int end = N-1;
		long min = MAX;
		int[] result = new int[2]; 
		while(start < end) {
			long mid = liquids[start] + liquids[end];
			long diff = Math.abs(mid);
			if(min > diff) {
				min = diff;
				result[0] = liquids[start];
				result[1] = liquids[end];
			}
			if(mid >= 0) {
				end--;
			}else {
				start++;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		int[] liquids = new int[N];
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			liquids[i] =  parseInt(st.nextToken());
		}
		
		int[] result = twoPointer(liquids, N);
		System.out.println(result[0] + " " + result[1]);
		
	}
}