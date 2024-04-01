import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int T, n, m;
	static int[] A;
	static int[] B;
	static long count;
	static ArrayList<Long> listA;
	static ArrayList<Long> listB;
	
	private static void twoPointer() {
		int pa = 0;
		int pb = listB.size()-1;
		
		while(pa < listA.size() && pb >= 0) {
			long sum = listA.get(pa)+listB.get(pb);
//			System.out.println("sum : " + sum);
			if(sum > T) {
				pb--;
			}else if(sum < T) {
				pa++;
			}else {
				long aCnt = 0;
				long bCnt = 0;
				long nowA = listA.get(pa); 
				long nowB = listB.get(pb); 
				while(pa < listA.size() && listA.get(pa) == nowA) {
					aCnt++;
					pa++;
				}
				while(pb >= 0 && listB.get(pb) == nowB) {
					bCnt++;
					pb--;
				}
//				System.out.println(aCnt + " " + bCnt);
				count = count + (aCnt*bCnt);
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = parseInt(in.readLine());
		n = parseInt(in.readLine());
		A = new int[n];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<n; i++) {
			A[i] = parseInt(st.nextToken());
		}
		m = parseInt(in.readLine());
		B = new int[m];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<m; i++) {
			B[i] = parseInt(st.nextToken());
		}
		
		listA = new ArrayList<>();
		listB = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			long sum = 0;
			for(int j=i; j<n; j++) {
				sum += A[j];
				listA.add(sum);
			}
		}
		Collections.sort(listA);
		
		for(int i=0; i<m; i++) {
			long sum = 0;
			for(int j=i; j<m; j++) {
				sum += B[j];
				listB.add(sum);
			}
		}
		Collections.sort(listB);
		
		
		twoPointer();
//		for(int i : listA) {
//			System.out.print(i + " ");
//		}
//		System.out.println("------------------");
//		for(int i : listB) {
//			System.out.print(i + " ");
//		}
//		System.out.println("------------------");
		System.out.println(count);
		
	}

}