import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 제목 : BJ_1781_컵라면_나종현
 * 설명 : 처음에는 단순 하게 deadline 오름차순 및 count 오름차순으로 풀려했으나 뒤 시간이라도 더 큰 점수가 있을 수도 있는 예외가 발생했다.
 * 그래서 dfs했더니 시간초과나고 결국 우선순위큐를 사용하여 오로지 큰 점수기준으로 필터를 했다. 물론 하나씩 볼때는   deadline아 오름차순 되어있어야 한다.
 * 1. 동호의 숙제를  Homework 클래스로 정의된 배열에 할당
 * 2. 정렬 (deadLine 기준으로 오름차순)
 * 3. 높은 컵라면 개수로만 채우기 위해 pq 정의 (기본 오름차순으로 정의되어 있음!)
 * 4. 앞에서 부터 배열 돌면서 pq에 컵라면 개수 할당
 * 5. 넣은 후에 pq사이즈가 deadLine보다 크면(즉 기한이 넘어가면) pq 하나 뺌(자동으로 가장 작은게 앞에 있음)
 * 6. pq에 있는 개수 합 출력
 * @author 나종현
 *
 */
public class Main {
	static int N;
	static Homework[] dongho;
	
	// Homework 클래스 (compareTo 재정의 하기 위해 Comparable<Homework>)
	public static class Homework implements Comparable<Homework> { 
		int deadLine;
		long count;
		
		public Homework(int deadLine, long count) {
			this.deadLine = deadLine;
			this.count = count;
		}

		@Override
		public int compareTo(Homework o) { // deadLine 기준으로 오름차순
			return this.deadLine - o.deadLine;
		}	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(in.readLine());
		dongho = new Homework[N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int deadLine = parseInt(st.nextToken());
			long count = parseLong(st.nextToken());
			dongho[i] = new Homework(deadLine, count);
		}
		Arrays.sort(dongho); // 정렬
		
		// 높은 컵라면 개수를 넣기 위해 pq 정의 (기본 오름차순으로 정의되어 있음!)
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			pq.offer(dongho[i].count); // 일단 넣어주고 			
			if(pq.size() > dongho[i].deadLine) { // 이때 사이즈가 초과하면(기한이 넘어가면)
				pq.poll(); // 그 중에 작은거 뺌 (알아서 작은게 앞에 있음)
			}
		}
		
		// 개수 더하기
		long sum = 0;
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}
		
		System.out.println(sum);

	}

}