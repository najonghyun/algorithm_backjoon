import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int n = routes.length;
        // 나간시점 기준 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> {
            if(o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        int answer = 1;
        int nowEnd = routes[0][1];
        for(int i=1; i<n; i++){
            int start = routes[i][0];
            int end = routes[i][1];
            
            // 안겹치는 상황 발생 시
            if(nowEnd < start){
                answer++;
                nowEnd = end;
            }
            // 그 전까지는 겹치니깐 카메라 추가 X
            
        }
        
        return answer;
    }
}