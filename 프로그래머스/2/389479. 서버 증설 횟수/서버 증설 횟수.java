import java.util.*;
/**
*
* 제목 : 프로그래머스_서버증설횟수
* 설명 : 이 문제는 서버가 증설이 되었을 때 각 서버의 보관을 잘해야 한다. 시간이 지남에 따라 서버의 
* 증/감 을 해주기 위해서 queue를 사용했다. (가독성을 위해 변수명 중요!)
* 
* hour : 현재 시간(끝시간 기준)
* serverList : 서버 근황 담은 2차원 queue {종료시간, 서버수}
* serverCount : 현재 증설된 서버 수
* needs : 이용자에 따른 추가해야할 서버 수
* extraNeeds : 현재 시간에 증설된 서버 수를 고려하여 더 추가해야 할 서버 수 (needs - serverCount)
* 
*
* author: 나종현
*/
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<int[]> serverList = new ArrayDeque<>();
        int serverCount = 0;
        for(int hour=1; hour<=24; hour++) {
            if(!serverList.isEmpty() && hour == serverList.peek()[0]){
                serverCount -= serverList.peek()[1];
                serverList.poll();
            }
            
            int needs = players[hour-1] / m;
            if(needs > 0) {
                if(needs > serverCount) {
                    int extraNeeds = needs - serverCount;
                    answer += extraNeeds;
                    serverCount += extraNeeds;
                    
                    serverList.offer(new int[]{hour+k, extraNeeds});
                }
            }
        }

        return answer;
    }
}
