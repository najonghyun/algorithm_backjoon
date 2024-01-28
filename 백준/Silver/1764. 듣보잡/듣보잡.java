import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_1764_듣보잡
 * 설명 : 해쉬맵으로 푸는게 더 빠른가 ??
 * @author 나종현
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		Map<String, Integer> cantHear = new HashMap<String, Integer>();
		String[] cantSee = new String[M];
		for(int i=1; i<=N; i++) {
			cantHear.put(in.readLine(), i);
		}
		for(int i=0; i<M; i++) {
			cantSee[i] = in.readLine();
		}
		Arrays.sort(cantSee);
		int count = 0;
		for(int i=0; i<M; i++) {
			if(cantHear.containsKey(cantSee[i])) {
				count++;
				sb.append(cantSee[i]).append("\n");
			}
		}
		System.out.println(count);
		System.out.println(sb);
		
	}

}