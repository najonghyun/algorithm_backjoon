import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int n = progresses.length;
        int[] days = new int[n];
        for(int i=0; i<n; i++){
            int nowProgress = progresses[i];
            int nowSpeed = speeds[i];
            int temp = 0;
            while(nowProgress < 100){
                nowProgress += nowSpeed;
                temp++;
            }
            days[i] = temp;
        }
        
        int count = 1;
        int top = days[0];
        for(int i=1; i<n; i++){
            if(days[i] > top){
                answer.add(count);
                top = days[i];
                count = 0;
            }
            count++;
        }
        answer.add(count);
        
        return answer;
    }
}