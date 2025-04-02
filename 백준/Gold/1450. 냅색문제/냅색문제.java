import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1450_냅색문제_나종현
 * @author 나종현
 *
 */
public class Main {
	static int N, C;
	static ArrayList<Long> leftSum, rightSum;
	
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
		int half = N/2;
		int[] left = new int[half];
		for(int i=0; i<half; i++) {
			left[i] = parseInt(st.nextToken());
		}
		int[] right = new int[N-half];
		for(int i=0; i<N-half; i++) {
			right[i] = parseInt(st.nextToken());
		}
		
		subComb(left, leftSum, half);
		subComb(right, rightSum, N-half);
		
		Collections.sort(rightSum);
		
		long count = 0;
		for(long num : leftSum) {
			int vaild = upperBound(C-num);
			count += vaild;
		}

		System.out.println(count);
		
	}

}
