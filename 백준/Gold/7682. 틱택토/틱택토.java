import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 문제 : BJ_7682_틱택토
 * 설명 : 조건들을 잘 분리해서 계산했다.
 * 총 9개이므로 일단 서로 번갈아가면서 둬야하므로 X와 O는 최대 1개 차이나야 한다. + X가 하나더 많으면 X승리, 두개 같으면 O가 승리했다는 것을 알 수 있다.
 * 그래서 두개로 조건을 나누고 이제 빙고인지 아닌지 판별한 후 바로 하나 놓기 전 상태를 다시 판별했다. (마지막 수를 놓았을 때 승부가 나야 하므로)
 * + 말을 모두 놓아도 끝나므로 그때는 그냥 X가 하나 더 많고, O의 승리만 아니라면 만족한다.
 *   
 */
public class Main {
	public static boolean isBingo(char[][] tictacto, char target) {
		// 직선 빙고 여부
		for(int i=0; i<3; i++) {
			int row = 0, col = 0;
			for(int j=0; j<3; j++) {
				if(tictacto[i][j] == target) {
					row++;
				}
				if(tictacto[j][i] == target) {
					col++;
				}
			}
			if(row == 3 || col == 3) {
				return true;
			}
		}
		
		// 대각선 빙고 여부
		int cross1 = 0, cross2 = 0;
		for(int i=0; i<3; i++) {
			if(tictacto[i][i] == target) {
				cross1++;
			}
			if(tictacto[2-i][i] == target) {
				cross2++;
			}
		}
		if(cross1 == 3 || cross2 == 3) {
			return true;
		}
		
		return false;
	}
	
	public static boolean reCheck(char[][] tictacto, char target) {
		char[][] temp = new char[3][3];
		for(int i=0; i<3; i++) {
			temp[i] = Arrays.copyOf(tictacto[i], 3);
		}
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(temp[i][j] == target) {
					temp[i][j] = '.';
					if(!isBingo(temp, target)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String temp = in.readLine();
			if(temp.equals("end")) {
				break;
			}
			char[][] tictacto = new char[3][3];
			int xCount = 0, oCount = 0;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					tictacto[i][j] = temp.charAt(3*i + j);
					if(tictacto[i][j] == 'O') {
						oCount++;
					}else if(tictacto[i][j] == 'X') {
						xCount++;					
					}
				}
			}
			
			boolean success = false;
			// O가 이겼을 경우 (O는 빙고여야하고 X는 빙고면 안됨)
			if(xCount == oCount) {
				if(isBingo(tictacto, 'O') && !isBingo(tictacto, 'X')) {
					if(reCheck(tictacto, 'O')) {
						success = true;
					}
				}
				// X가 이겼을 경우 (X는 빙고여야하고 O는 빙고면 안됨)
			}else if(xCount-1 == oCount) {
				// 이 때 다 채워진 경우 -> o가 이기지만 않으면 만족!
				if(xCount + oCount == 9) {
					if(!isBingo(tictacto, 'O')){
						success = true;						
					}
				}else {
					if(isBingo(tictacto, 'X') && !isBingo(tictacto, 'O')) {
						if(reCheck(tictacto, 'X')) {
							success = true;
						}
					}					
				}
			}
			
			sb.append(success ? "valid" : "invalid").append("\n");
		}
		
		System.out.print(sb);
		
	}
}
