import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : BJ_10830_행렬제곱
 * 설명 : 그냥 하면 시간초과 나서 유형보고 분할정복 이길래 아!!! 하고 푼 문제이다.
 * 분할은 재귀를 할때 반씩 나누어서 처리하고 답은 다 정복된 값을 받아서 출력하는 방식이다.
 * @author 나종현
 *
 */
public class Main {
	
	static int N; 
	static long B;
	static int[][] originalMatrix;
	static int[][] matrix;
	
	private static int[][] multiplication(int[][] matrix1, int[][] matrix2){ // 두 매트릭스를 곱하여 출력하는 함수
		int[][] tempMatrix = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int temp = 0;
				for(int k=0; k<N; k++) {
					temp += matrix1[i][k] * matrix2[k][j];
				}
				tempMatrix[i][j] = temp%1000;
			}
		}
		return tempMatrix;
	}
	
	private static int[][] matrixSquare(long b) {
		if(b == 1) { // 기저조건 : b==1 일 때는 원래 행렬
			return originalMatrix;
		}
		int[][] tempMatrix = matrixSquare(b/2); // 미리 여기서 재귀하면 반씩 분할해서 사용 가능
				
		if(b%2 == 0) { // 짝수일 때
			return multiplication(tempMatrix, tempMatrix);
		}else { // 홀수일 때
			return multiplication(multiplication(tempMatrix, tempMatrix), originalMatrix);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		B = parseLong(st.nextToken());
		
		matrix = new int[N][N];
		originalMatrix = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++) {
				int num = parseInt(st.nextToken());
				originalMatrix[i][j] = num%1000; // 처음 값도 1000으로 나눈 나머지를 출력해야 하므로 해줌
			}
		}
		
		matrix = matrixSquare(B); // 결과 값을 matrix[][]에 넣어줌
		
		for(int[] arr : matrix) {
			for(int i : arr) {
				System.out.print(i + " ");												
			}
			System.out.println();
		}
		
	}

}