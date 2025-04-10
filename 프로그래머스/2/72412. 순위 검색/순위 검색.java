import java.util.*;
import static java.lang.Integer.parseInt;
/**
* 제목 : 순위 검색
* 설명 : info: M, query: N 일때 일단 N*M를 하게 되면 시간이 초과된다 반드시!
* 그래서 N*M 대신 M*logN으로 바꾸기 위해 정렬 후 이분 탐색을 해야한다. 하지만 문자는 이분탐색이 안되므로 문자를 key, 숫자를 value로
* 하는 Map 형태로 풀었다. 
* 이 때 모두 가능한 "-"을 생각해야 한다. 경우의 수에 대한 <String, ArrayList<Integer>>을 다 만들어 놓고 이제 query 들어오면
* 그거에 맞게 넣어주고 각 정렬 후 이분탐색으로 풀었다.
* 
* author: 나종현
*
*/

class Solution {
    static Map<String, ArrayList<Integer>> map;
    
    // 각 자리 당 "-"의 경우의 수를 포함해야 함! 모두 key에 대한 배열을 만들어 놓는다.
    public void dfs(String[] key, String curKey, int cnt, int score){
        if(cnt == 4){
            if (!map.containsKey(curKey)) {
                map.put(curKey, new ArrayList<>());
            }
            map.get(curKey).add(score);
            return;
        }
        dfs(key, curKey+"-", cnt+1, score);
        dfs(key, curKey+key[cnt], cnt+1, score);
    }
    
    // 타겟 점수 이상의 개수를 구해야하므로 target의 처음부분부터 카운트 하기 위해 lower_bound 사용
    // lower_bound : 같을 때도 더 적게!, start+1 및 start 반환!
    public int lower_bound(ArrayList<Integer> list, int target){
        int start = 0;
        int end = list.size();
        while(start < end){
            int mid = (start + end) / 2;
            if(list.get(mid) >= target){
                end = mid;
            }else{
                start = mid+1;
            }
        }
        return start;
    }
    
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        StringTokenizer st;
        for(String i : info){
            st = new StringTokenizer(i, " ");
            String[] key = {st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()}; // 한번에 key로!
            int value = parseInt(st.nextToken());
            dfs(key, "", 0, value);
        }
        
        // map.values() 활용하여 values를 다 가져옴!
        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }
        
        int N = query.length;
        int[] answer = new int[N];
        for(int i=0; i<N; i++){
            query[i] = query[i].replace(" and", "");
            st = new StringTokenizer(query[i], " ");
            String key = st.nextToken() + st.nextToken() + st.nextToken() + st.nextToken();
            int value = parseInt(st.nextToken());
            ArrayList<Integer> list = map.get(key);
            if(list != null){ // 주의 : list이 null일 때 size() 사용하면 컴파일 에러
                int point = lower_bound(list, value);
                answer[i] = list.size() - point;
            }else{
                answer[i] = 0;
            }
            
        }
        return answer;
    }
}