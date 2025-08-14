import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_13144_ListofUniqueNumbers
 * 설명: 슬라이딩 윈도우를 이용해서 풀었다. 이 때 부분수열을 모두 구하는 방식이 끝에서 끝나는 기준으로 윈도우 길이를 구하는 것임을 알고 있어야 한다..
 * ** right 에서 끝나는 유효한 부분수열 개수 = 윈도우 길이
 * + 총 나올수있는 경우의 수가 N(N+1)/2 라서 크기 주의!
 * 
 * @author 나종현
 */
public class Main {
	public static long sliding(int N, int[] list) {
		int left = 0;
		long count = 0;
		boolean[] check = new boolean[100001];
		for(int right=0; right<N; right++) {
			int number = list[right];
			// number가 현재 윈도우에 이미 있다면, 사라질 때까지 left 이동
			while(check[number]) {
				check[list[left]] = false;
				left++;
			}
			check[number] = true;
			
			count += (right - left + 1);
		}

		return count;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int[] list = new int[N];
		
		for(int i=0; i<N; i++) {
			list[i] = parseInt(st.nextToken());
		}
				
		System.out.println(sliding(N, list));
	}
}