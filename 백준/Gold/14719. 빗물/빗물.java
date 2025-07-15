import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;
/**
 * 문제 : BJ_14719_빗물
 * 설명 : 그림은 양 사이드가 반드시 막혀있고 그중에 최소 높이 기준으로 채워진다.
 * 그래서 왼쪽과 오른쪽 기준으로 한번씩 본 후 필요한 빗물의 교집합을 통해 구했다.
 * 
 * @author 나종현
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		int H = parseInt(st.nextToken());
		int W = parseInt(st.nextToken());
		
		int[] map = new int[W];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<W; i++) {
			map[i] = parseInt(st.nextToken());
		}
		
		int[] water = new int[W];
		Arrays.fill(water, Integer.MAX_VALUE);
		
		// 1. 왼쪽에서부터 빗물 계산
		int max = 0;
		for(int i=0; i<W; i++) {
			if(max < map[i]) {
				max = map[i];
				water[i] = 0;
			}else {
				water[i] = Math.min(water[i], max - map[i]);
			}
		}
		
		// 2. 오른쪽에서부터 빗물 계산
		max = 0;
		for(int i=W-1; i>=0; i--) {
			if(max < map[i]) {
				max = map[i];
				water[i] = 0;
			}else {
				water[i] = Math.min(water[i], max - map[i]);
			}
		}
		
		// 3. 각 W 위치의 필요 빗물의 합
		int result = 0;
		for(int i : water) {
			result += i;
		}
		System.out.println(result);
		
	}
}
