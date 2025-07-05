import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 문제 : BJ_12919_A와B2
 * 설명 : 처음에는 S -> T 로 변경했는데 이러면 각 단어마다 두개씩 실행하므로 2^(50) 시간초과가 난다. 그래서
 * 반대로 접근해 T -> S 를 만들어 특정 조건일때만 재귀를 실행하도록 하여 시간을 줄였다.
 * 1. 가장 뒤 단어가 'A'일때만 'A'를 빼고 다음으로 재귀
 * 2. 뒤집었을때 기준 첫 단어가 'B'일때만  'B'제외 및 뒤집어서 재귀
 * 
 * @author 나종현
 *
 */
public class Main {
	static boolean isMake;
	public static void changeToT(int size, int targetSize, String words, String targetWords) {
		if(size == targetSize) {
			if(words.equals(targetWords)) {
				isMake = true;
			}
			return;
		}
		
		String newWords = "";
		if(words.charAt(size-1) == 'A') {
			newWords = words.substring(0, size-1);
			changeToT(size-1, targetSize, newWords, targetWords);
		}
		
		
		if(words.charAt(0) == 'B') {
			newWords = reverseWords(words.substring(1));
			changeToT(size-1, targetSize, newWords, targetWords);
		}
	}
	
	public static String reverseWords(String words) {
		String temp = "";
		for(char c : words.toCharArray()) {
			temp = c + temp ;
		}
		return temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String S = in.readLine();
		String T = in.readLine();
		
		changeToT(T.length(), S.length(), T, S);
		
		System.out.println(isMake ? 1 : 0);
	
	}

}
