import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 제목 : BJ_12956_퍼레이드_나종현
 * 설명 : 일단 모든 도시로 가는 경우의 수를 구해야 하기 때문에 플로이드 워셜 알고리즘을 사용했다. 
 * 주의할 점은 플로이드 워샬의 기본 조건인데 인접행렬 형태가 i==j 부분은 0이여야하고 나머지는 INF로 초기화 해주어야 한다.(그래야 최소값을 구하므로...)
 * + k를 거쳐서 갈 때 더하기 연산이 사용되므로 무작정 가장 큰 정수로 초기화 하면 오버플로우가 나타날 수 있다. 그래서 Integer.MAX_VALUE에서 나누기 2 해준 값을 넣었다.
 * + 위에서부터 순서대로가 아니기 때문에 출력 순서 주의 해야한다! 
 * @author 나종현
 *
 */
public class Main {
	static int N, M;
	static int[][] adjMatrix, onAdjMat, offAdjMat;
	static StringBuilder sb;
	static int INF = Integer.MAX_VALUE / 2;
	
	public static void parade() {
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(k == i) continue;
				for(int j=0; j<N; j++) {
					if(j == k || j == i) continue;
					onAdjMat[i][j] = Math.min(onAdjMat[i][j], onAdjMat[i][k] + onAdjMat[k][j]);
					offAdjMat[i][j] = Math.min(offAdjMat[i][j], offAdjMat[i][k] + offAdjMat[k][j]);
				}
			}
		}
		
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				if(i == j) continue;
					if(onAdjMat[i][j] != offAdjMat[i][j]) {
						result++;
					}					
			}
		}
		sb.append(result);
		
	}
	
	public static void copy(int[][] array1, int [][] array2) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == j) {
					array1[i][j] = 0;
				}else if(array2[i][j] != 0) {
					array1[i][j] = array2[i][j];					
				}else {
					array1[i][j] = INF;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		int[][] order = new int[M][2];
		adjMatrix = new int[N][N];
		onAdjMat = new int[N][N];
		offAdjMat = new int[N][N];
		sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = parseInt(st.nextToken());
			int to = parseInt(st.nextToken());
			int time = parseInt(st.nextToken());
			adjMatrix[from][to] = adjMatrix[to][from] = time;
			order[i][0] = from;
			order[i][1] = to;
		}
		
		for(int i=0; i<M; i++) {
			int from = order[i][0];
			int to = order[i][1];
			copy(onAdjMat, adjMatrix);
			onAdjMat[from][to] = onAdjMat[to][from] = INF;
			copy(offAdjMat, adjMatrix);
			parade();
			if(i == M-1) break;
			sb.append(" ");
		}
		
		System.out.println(sb);
		
	}

}
