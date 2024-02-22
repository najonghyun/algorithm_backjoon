import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int N, size;
	static boolean[] sosu;
	static ArrayList<Integer> memo;
	
	private static void createPrime() {
		int rootN = (int) Math.sqrt(N);
		for(int i=2; i<=N; i++) {
			sosu[i] = true;
		}
				
		for(int i=2; i<=rootN; i++) {
			if(sosu[i]) {
				for(int j=i*i; j<=N; j+=i) {
					sosu[j] = false;
				}
			}	
		}
	}
	
	private static int twoPointer() {
		int start = 0;
		int end = 0;
		int sum = 2;
		int count = 0;
		while(start < size && end < size) {
			if(sum == N) {
				count++;
			}
			if(sum >= N) {
				sum -= memo.get(start++);
			}else if(sum < N) {
				end++;
				if(end>=size) break;
				sum += memo.get(end);
			}
			
		}
		return count;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		sosu = new boolean[N+1];
		memo = new ArrayList<>();
		createPrime();
		
		for(int i=2; i<=N; i++) {
			if(sosu[i]) {
				memo.add(i);
			}
		}
		
		size = memo.size();
		System.out.println(twoPointer());
	

	}

}