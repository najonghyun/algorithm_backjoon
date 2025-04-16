import java.util.*;

class Solution {
    static boolean[][] used;
    static int[] dirY = {-1, 0, 1, 0};
    static int[] dirX = {0, 1, 0, -1};
    
    public int crane(int n, int m, char[][] map, char target){
        int count = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(!used[i][j] && map[i][j] == target){
                    used[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
    
    public int jigaecar(int n, int m, char[][] map, char target){
        int count = 0;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int nowY = q.peek()[0];
            int nowX = q.peek()[1];
            q.poll();
            
            if(!used[nowY][nowX] && map[nowY][nowX] != '*'){
                if(map[nowY][nowX] == target){
                    used[nowY][nowX] = true;
                    count++;
                }
                continue;
            }
            
            for(int d=0; d<4; d++){
                int nextY = nowY + dirY[d];
                int nextX = nowX + dirX[d];
                if(nextY >= n || nextY < 0 || nextX >= m || nextX < 0 || visited[nextY][nextX]){
                    continue;
                }
                q.offer(new int[]{nextY, nextX});
                visited[nextY][nextX] = true;
            }
        }
        return count;
    }
    public int solution(String[] storage, String[] requests) {
        int useCount = 0;
        int n = storage.length;
        int m = storage[0].length();
        char[][] map = new char[n+2][m+2];
        used = new boolean[n+2][m+2];
        for(int i=0; i<n+2; i++){
            for(int j=0; j<m+2; j++){
                if(i == 0 || i == n+1 || j == 0 || j == m+1){
                    map[i][j] = '*';
                }else{
                   map[i][j] = storage[i-1].charAt(j-1); 
                }
            }
        }
        
        for(String request : requests){
            if(request.length() > 1){
                useCount += crane(n, m, map, request.charAt(0));
            }else{
                useCount += jigaecar(n+2, m+2, map, request.charAt(0));
            }
        }
        
        int answer = (n*m) - useCount; 
        return answer;
    }
}