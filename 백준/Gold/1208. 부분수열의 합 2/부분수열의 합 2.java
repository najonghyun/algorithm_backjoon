import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 문제 : BJ_1208_부분수열의합2
 * 설명 : N이 40이라서 2^40을 하게되면 시간이 너무 초과된다. 그래서 반으로 나누고 그 각각에 부분수열을 구하고, 그 두개를 하나씩 합하면서 경우의 수를 출력한다.
 * 이때 그냥 반복문 돌리면 O(n^2)이므로 더 줄이기 위해 두개의 배열을 이용한 투 포인터 알고리즘(다른방향)을 사용한다.
 * @author 나종현
 *
 */
public class Main {
	static int N, S; 
	static long count;
	static int[] arrays;
	static ArrayList<Long> left, right;
	
	private static void search(int start, int end, int state) { // 2개로 나눔
		int size = end-start;
		for(int i=1; i<(1<<size); i++) { // 부분 수열 구하기 (비트 마스킹)
			long sum = 0;
			for(int j=0; j<size; j++) {
				if((i & (1<<j)) != 0) {
					sum += arrays[start+j];
				}
			}
			if(sum == S) {
				count++;
			}
			if(state == 0) {
				left.add(sum);
			}else if(state == 1) {
				right.add(sum);
			}
		}
		
	}
	
	private static void twoPointer() { // 투 포인터 알고리즘
		int pa = 0;
		int pb = right.size()-1;
		while(pa < left.size() && pb >= 0) {
			long sum = left.get(pa) + right.get(pb);
			if(sum == S) {
				long n = left.get(pa);
				long m = right.get(pb);
				long aCnt = 0, bCnt = 0;
				while(pa < left.size() && n == left.get(pa)) {
					aCnt++;
					pa++;
				}
				while(pb >= 0 && m == right.get(pb)) {
					bCnt++;
					pb--;
				}
				count = count + (aCnt * bCnt);
			}else if(sum < S){
				pa++;
			}else if(sum > S) {
				pb--;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		S = parseInt(st.nextToken());
		arrays = new int[N];
		left = new ArrayList<>();
		right = new ArrayList<>();
		
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {			
			arrays[i] = parseInt(st.nextToken());
		}
		
		search(0, N/2, 0);
		search(N/2, N, 1);
		
		Collections.sort(left); // 투포인터 알고리즘 에서 다른방향일때는  반드시 정렬상태여야 함!
		Collections.sort(right);
		
		twoPointer();
		
		System.out.println(count);
		
		
		
	}

}