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
	
	// copy(): 새로운 배열로 복사 (이 때 플로이드 워샬 인접행렬로 초기화)
	public static void copy(int[][] newArray, int [][] array) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == j) {
					newArray[i][j] = 0;
				}else if(array[i][j] != 0) {
					newArray[i][j] = array[i][j];					
				}else {
					newArray[i][j] = INF;
				}
			}
		}
	}
	
	// minDistance(adjMat): 플로이드 워샬로 인접행렬 최소 거리 구하기 
	public static void minDistance(int[][] adjMat) {
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(k == i) continue;
				for(int j=0; j<N; j++) {
					if(j == k || j == i) continue;
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
				}
			}
		}
	}
	
	// affectCount(): 영향 끼친 도로 개수 구하는 함수
	public static void affectCount() {
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		int[][] order = new int[M][2]; // order: 순서 넣어주는 배열(0: from, 1: to)
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
		
		// 퍼레이드 도로 없는 일반 배열 복사 후 최소 경로 미리 구하기
		copy(offAdjMat, adjMatrix);
		minDistance(offAdjMat);
		
		for(int i=0; i<M; i++) { // 퍼레이드 도로 하나씩 수행
			int from = order[i][0];
			int to = order[i][1];
			copy(onAdjMat, adjMatrix);
			onAdjMat[from][to] = onAdjMat[to][from] = INF;
			minDistance(onAdjMat);
			affectCount();
			if(i == M-1) break;
			sb.append(" ");
		}
		
		System.out.println(sb);
		
	}

}