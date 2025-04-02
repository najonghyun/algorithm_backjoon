import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1450_냅색문제_나종현
 * 설명 : 일단 부분집합을 구하는 문제인데 바이너리 카운팅을 하더라도 2^30을 모두 돌면 시간초과가 난다. 그래서 절반으로 나누는 것 까진 성공했다. 하지만 
 * 그 후 투포인터를 이용해서 풀어보려했으나 풀리지 않았고, 더 확실한 upperBound를 사용했다. 
 * 1. 두개로 나누어서 각각 부분집합의 합을각 배열에 넣는다.
 * 2. leftSum 배열을 기준으로 돌면서 그 값을 뺀 나머지를 target으로 잡는다.
 * 3. rightSum 배열은 정렬을 한 후 target까지 만족하는 인덱스를 찾는다면 그 인덱스까지의 값들은 모두 만족하므로 그 값을 count에 더한다.
 * 4. 그럼 원래 O(N^2)을 O(NlogN)으로 줄일 수 있으므로 시간초과가 나지 않고 해결할 수 있다.
 * @author 나종현
 *
 */
public class Main {
	static int N, C;
	static ArrayList<Long> leftSum, rightSum;
	
	// upperBound 구하는 함수
	public static int upperBound(long target) {
		int low = 0;
		int high = rightSum.size();
		while(low < high) {
			int mid = (low + high) / 2;
			if(rightSum.get(mid) <= target) {
				low = mid + 1;
			}else {
				high = mid;
			}
		}
		return low;
	}
	
	// 바이너리 카운팅을 이용한 부분집합 넣기
	public static void subComb(int[] array, ArrayList<Long> subList, int size) {
		for(int i=0; i<(1<<size); i++) {
			long sum = 0;
			for(int j=0; j<size; j++) {
				if((i & (1<<j)) != 0) {
					sum += array[j];
				}
			}
			if(sum <= C) {
				subList.add(sum);				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		leftSum = new ArrayList<>();
		rightSum = new ArrayList<>();
		
		st = new StringTokenizer(in.readLine(), " ");
		// 절반을 기준으로(홀수 포함) 각각 나누어 배열에 넣어줌
		int half = N/2;
		int[] left = new int[half];
		for(int i=0; i<half; i++) {
			left[i] = parseInt(st.nextToken());
		}
		int[] right = new int[N-half];
		for(int i=0; i<N-half; i++) {
			right[i] = parseInt(st.nextToken());
		}
		
		// 각각 배열의 부분집합을 새로운 ArrayList에 넣어줌
		subComb(left, leftSum, half);
		subComb(right, rightSum, N-half);
		
		// 오른쪽만 정렬
		Collections.sort(rightSum);
		
		// leftSum에 있는 값을 하나씩 방문하며 C에서 뺀 나머지 값을 target에 넣어 upperBound 실행
		long count = 0;
		for(long num : leftSum) {
			int vaild = upperBound(C-num);
			count += vaild;
		}

		System.out.println(count);
		
	}

}
