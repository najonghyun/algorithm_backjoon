import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int n = name.length();
        int leftRight = n-1;
        for(int i=0; i<n; i++){
            // 상하 조이스틱
            char temp = name.charAt(i);
            answer += Math.min(temp-'A', 'A'-temp+26);
            // 좌우 조이스틱
            int x = i+1;
            while(x < n && name.charAt(x) == 'A') x++;
            leftRight = Math.min(leftRight, Math.min(i+i+(n-x), (n-x)+(n-x)+i));
        }
        answer += leftRight;
    
        return answer;
    }
}