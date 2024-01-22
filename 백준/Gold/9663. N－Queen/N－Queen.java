import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
/**
 * 문제 : NQueen
 * 설명 : NQueen 문제는 대표적인 백트래킹 문제이다. 각 행마다 1개씩 queen을 놓아야함으로
 * 행을 기준으로 하나씩 놓아가며 놓을 수 있는지에 대한 함수를 만들어서 판단하고 그렇게 모든 행에 queen을 놓으면 count를 하나 추가 시키면서 경우의 수를 찾아간다.
 * @author USER
 *
 */
public class Main {
	static int N;
	static int[] col; // 이게 중요함!! col[row] : 각 row마다 몇번째에 위치해 있는지 값을 넣어 줌
	static int count;
	
	private static void setQueen(int rowNo) {
		if(rowNo > N) { // 기저조건 : 다 놓았을 경우
			count += 1;
			return;
		}
		
		for(int i=1; i<=N; i++) {
			col[rowNo] = i;
			if(!isAvailable(rowNo)) {
				continue;
			}
			setQueen(rowNo+1);
		}
	}
	
	private static boolean isAvailable(int rowNo) {
		for(int i=1; i<rowNo; i++) { // 내가 놓을 곳 바로 전 열 까지만 비교!!!
			if(col[i] == col[rowNo] || rowNo - i == Math.abs(col[rowNo] - col[i])) { // 비교 : 같은 열인지 and 대각선에 위치한지
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		col = new int[N+1];
		
		setQueen(1); // 1행부터
		
		System.out.println(count);
	}

}