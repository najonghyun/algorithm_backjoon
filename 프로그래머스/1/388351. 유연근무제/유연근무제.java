/**
* 문제 : 유연근무제
* 설명 : 그냥 10분 더했을때 시분만 잘 업데이트 해주면 된다!
*
* @author 나종현
*/
class Solution {
    public boolean isSave(int time, int targetTime) {
        int endTime = time+10;
        if(endTime % 100 >= 60){
            endTime = endTime - 60 + 100;
        }
        if(endTime >= targetTime) {
            return true;
        }
        if(endTime >= 2400) {
            endTime -= 2400;
            if(endTime >= targetTime) {
                return true;
            }
        }
        return false;
    }
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        int answer = n;
        for(int i=0; i<n; i++){
            int nowDay = startday;
            for(int j=0; j<7; j++){
                if(nowDay > 7){
                    nowDay = 1;
                }
                if(nowDay >= 6){
                    nowDay++;
                    continue;
                }
                if(!isSave(schedules[i], timelogs[i][j])){
                    System.out.println(timelogs[i][j]);
                    answer--;
                    break;
                }
                nowDay++;
            }
        }
        return answer;
    }
}
