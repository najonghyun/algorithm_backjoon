import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<int[]> serverEHour = new ArrayDeque<>();
        int serverCount = 0;
        for(int hour=1; hour<=24; hour++) {
            if(!serverEHour.isEmpty() && hour == serverEHour.peek()[0]){
                serverCount -= serverEHour.peek()[1];
                serverEHour.poll();
            }
            
            int needs = players[hour-1] / m;
            if(needs > 0) {
                if(needs > serverCount) {
                    int extraNeeds = needs - serverCount;
                    answer += extraNeeds;
                    serverCount += extraNeeds;
                    
                    serverEHour.offer(new int[]{hour+k, extraNeeds});
                }
            }
        }

        return answer;
    }
}