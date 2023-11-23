package com.ssafy.algo1123;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_9019_DSLR
 * 설명 : bfs로 풀고 이제 command()안에 "D", "S", "L", "R" 총 4가지 상황을 넣어서 계산하면 된다. 
 * @author 나종현
 *
 */

public class BJ_9019_DSLR_나종현 {
	static int A, B;
	static String[] commands = {"D", "S", "L", "R"};
	static StringBuilder sb;
	
	private static int command(int num, String c) {
		int result = 0;
		if(c.equals("D")) {
			result = (num * 2) % 10000;
		}else if(c.equals("S")) {
			if(num == 0) {
				result = 9999;
			}else {
				result = num - 1;			
			}
		}else if(c.equals("L")) {
			int d1 = num / 1000;
			int remainder = num % 1000;
			result = remainder * 10 + d1;
		}else if(c.equals("R")) {
			int d4 = num % 10;
			int remainder = num / 10;
			result = d4 * 1000 + remainder;
		}
		return result;
	}
	
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		Queue<String> stringQ = new ArrayDeque<>();
		boolean[] isChecked = new boolean[10000];
		q.offer(A);
		stringQ.offer("");
		isChecked[A] = true;
		while(!q.isEmpty()) {
			int nowNum = q.peek();
			String nowCommend = stringQ.peek();
			q.poll();
			stringQ.poll();
			
			if(nowNum == B) {
				sb.append(nowCommend).append("\n");
				break;
			}
			
			for(int c=0; c<4; c++) {
				int nextNum = command(nowNum, commands[c]);
				
				if(isChecked[nextNum]) {
					continue;
				}
				q.offer(nextNum);
				stringQ.offer(nowCommend + commands[c]);
				isChecked[nextNum] = true;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = parseInt(in.readLine());
		StringTokenizer st;
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(in.readLine(), " ");
			A = parseInt(st.nextToken()); 
			B = parseInt(st.nextToken()); 
			
			bfs();
		}
		
		System.out.println(sb);

	}

}
