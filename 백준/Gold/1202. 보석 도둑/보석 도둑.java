import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int K = parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o2[0] - o1[0];
				}
				return o2[1] - o1[1];
			}
		});
		
		int[][] jewel = new int[N][2];
		int[] C = new int[K];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int M = parseInt(st.nextToken());
			int V = parseInt(st.nextToken());
			jewel[i][0] = M;
			jewel[i][1] = V;
		}
		for(int i=0; i<K; i++) {
			C[i] = parseInt(in.readLine());
		}
		Arrays.sort(jewel, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		Arrays.sort(C);
		
//		for(int[] arr : jewel) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println(Arrays.toString(C));
		
		long maxPrice = 0;
		int idx = 0;
		for(int i=0; i<K; i++) {
			while(idx < N && jewel[idx][0] <= C[i]) {
				pq.offer(new int[] {jewel[idx][0], jewel[idx][1]});
				idx++;
			}
			if(!pq.isEmpty()) {
				int nowV = pq.peek()[1];
				pq.poll();
				maxPrice += nowV;				
			}
		}
		
		
		System.out.println(maxPrice);
		
		
		
		
		

	}

}