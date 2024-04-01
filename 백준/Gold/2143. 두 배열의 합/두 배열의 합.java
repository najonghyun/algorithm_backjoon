package com.daily_0401;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_2143_두배열의합
 * 설명 : 이거 그냥 하면 정렬이 안되니깐 투포인터 못써서 그럼 방법이 부분합 집합을 리스트에 담아주고 그걸 정렬한 다음
 * 투포인터 진행하면 쉽게 해결 가능!
 * 
 * @author 나종현
 *
 */
public class BJ_2143_두배열의합_나종현 {
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
		System.out.println(count);
		
	}

}
