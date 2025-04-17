import java.util.*;
/**
* 제목 : 충돌위험 찾기
* 설명 : 주의할 점은 로봇 이동 시 중복이 가능함! 그래서 맵으로 그려하면서 하면 안됌!
* + 충돌하는 것도 똑같은 곳에서 충돌하더라도 다른 시간이면 그 충돌 값도 더해야 됨! 그래서 좌표 방문으로 처리하면 안됌!
* 1. 그래서 각 이동한 물류의 시간과 위치를 Queue에 다 넣어놓는다.
* 2. Queue에서 하나씩 빼면서 Set에 넣어주면서 중복되는 값은 새 set에 넣어준다(중복이 2개 이상 있으므로 한번만 더하기 위해)
* 3. 새로운 set을 이제 확인해보면 중복된 좌표 단 1개씩만 남는다. 그래서 그 size()를 출력해주면 된다.
*
* author: 나종현
*/
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int x = routes.length;
        int m = routes[0].length;
        
        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0; i<x; i++){
            int now = 0;
            for(int j=1; j<m; j++){
                int startY = points[routes[i][j-1]-1][0]-1;
                int startX = points[routes[i][j-1]-1][1]-1;
                int endY = points[routes[i][j]-1][0]-1;
                int endX = points[routes[i][j]-1][1]-1;
                if(j == 1) {
                    q.offer(new int[]{startY, startX, ++now});
                }
                if(startY > endY){
                    for(int r=startY-1; r>=endY; r--){
                        q.offer(new int[]{r, startX, ++now});
                    }
                }else {
                    for(int r=startY+1; r<=endY; r++){
                        q.offer(new int[]{r, startX, ++now});
                    }
                }
                if(startX > endX){
                    for(int c=startX-1; c>=endX; c--){
                       q.offer(new int[]{endY, c, ++now});
                    }
                }else {
                    for(int c=startX+1; c<=endX; c++){
                        q.offer(new int[]{endY, c, ++now});
                    }
                }
                
            }
        }
        
        // 겹치는거 찾기! set을 string으로 해서 넣고 비교!
        HashSet<String> set = new HashSet<>();
        HashSet<String> cashed = new HashSet<>();
        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.peek()[1];
            int value = q.peek()[2];
            q.poll();
            String temp = r + "," + c + "," + value;
            if(set.contains(temp)){
                cashed.add(temp);
            }else{
                set.add(temp);     
            }
        }
        answer = cashed.size();
        
        return answer;
    }
}