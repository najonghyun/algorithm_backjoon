import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
/**
* 문제 : 광고삽입
* 설명 : 초단위로 쪼개서 시작하면 +1 끝나면 -1로 각 시간별 시청자수를 구하고, 그걸 한번더 해서 누적 합을 구한다.
* 그러면 이제 그 차이의 최대값이 가장 많은 시청시간이 된다! (이러한 해결법을 알아야 풀 수 있음!)
* @author 나종현
*/
class Solution {
    
    public static long timeToSecond(String time){
        int hour = parseInt(time.substring(0, 2));
        int minutes = parseInt(time.substring(3, 5));
        int second = parseInt(time.substring(6));
        return hour*3600 + minutes*60 + second;
    }
    
     public static String secondToTime(long time){
        String temp = "";
        long hour = time/3600;
        if(hour < 10) temp += "0"+hour;
        else temp += hour;
        temp+=":";
        time = time%3600;
        long minutes = time/60;
        if(minutes < 10) temp += "0"+minutes;
        else temp += minutes;
        temp+=":";
        long second = time%60;
        if(second < 10) temp += "0"+second;
        else temp += second;
        return temp;
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        long playTime = timeToSecond(play_time);
        long advTime = timeToSecond(adv_time);
        long[] time = new long[360001];
        
        for(String temp : logs){
            long start = timeToSecond(temp.substring(0, 8));
            long end = timeToSecond(temp.substring(9));
            time[(int)start] += 1;
            time[(int)end] -= 1;
        }
        
        // 각 시간별 시청자 수
        for(int i=1; i<=playTime; i++){
            time[i] += time[i-1];
        }
        // 시간 별 재생 누적 합
        for(int i=1; i<=playTime; i++){
            time[i] += time[i-1];
        }
        
        long max = time[(int)(advTime-1)];
        long maxTime = 0;
       
        for(int i=0; i<=playTime-advTime; i++){
            if(time[(int)(i+advTime)] - time[i] > max){
                max = time[(int)(i+advTime)] - time[i];
                maxTime = i+1;
            }
        }
        
       
        answer = secondToTime(maxTime);
        
        
        return answer;
    }
}