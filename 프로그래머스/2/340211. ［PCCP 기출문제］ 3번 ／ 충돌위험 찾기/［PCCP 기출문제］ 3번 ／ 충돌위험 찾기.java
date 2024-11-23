import java.util.*;

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
            }
            set.add(temp);     
        }
        answer = cashed.size();
       
        return answer;
    }
}