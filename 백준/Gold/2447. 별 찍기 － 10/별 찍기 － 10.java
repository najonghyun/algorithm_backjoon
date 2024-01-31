import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void star10(StringBuilder sb, int i, int j, int n) {
		if(n == 1) {
			sb.append("*");
			return;
		}
		if(i%3 == 1 && j%3 == 1) {
			sb.append(" ");
			return;
		}
		if((i/n)%3 == 1 && (j/n)%3 == 1) {
			sb.append(" ");
			return;
		}
		star10(sb, i, j, n/3);

	
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		char[][] arrays = new char[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				star10(sb, i, j, N);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
