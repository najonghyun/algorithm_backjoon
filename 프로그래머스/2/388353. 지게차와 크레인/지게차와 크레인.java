import java.util.*;
/**
*
* 문제: 지게차와 크레인
* 설명: 크레인은 그냥 다 보면 되는데 지게차는 다르다. 겉만을 봐야하는데 그냥 단순 for문으로 밖에서부터 접근하면
* 안싸이클을 보기가 애매해진다. (선 업데이트를 하고 다른곳에서 접근할 때 이미 업데이트 된 곳에 접근함으로서 예외 일어남)
* 그래서 
* 1. n+2 * m+2 배열로 겉부분을 만들어 줌
* 2. 거기서부터 bfs로 접근해 물류에 접근하면 더이상 깊이 못들어가게 막음
* 3. 가장자리 부분 발견했을 때 비교로 정답을 구함
*
* author: 나종현
*
*/
class Solution {
    static boolean[][] used;
    static int[] dirY = {-1, 0, 1, 0};
    static int[] dirX = {0, 1, 0, -1};
    
    // 크레인
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
    
    // 지게차
    public int forklift(int n, int m, char[][] map, char target){
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
        char[][] map = new char[n+2][m+2]; // 겉부분 만들어주기 위해
        used = new boolean[n+2][m+2];
        for(int i=0; i<n+2; i++){
            for(int j=0; j<m+2; j++){
                if(i == 0 || i == n+1 || j == 0 || j == m+1){
                    map[i][j] = '*'; // 겉은 *로 감싼다!
                }else{
                   map[i][j] = storage[i-1].charAt(j-1); 
                }
            }
        }
        
        for(String request : requests){
            if(request.length() > 1){
                useCount += crane(n, m, map, request.charAt(0)); // 크레인은 원래 크기 배열로 넣어줌
            }else{
                useCount += forklift(n+2, m+2, map, request.charAt(0));
            }
        }
        
        int answer = (n*m) - useCount; // 전체에서 접근한 부분 뺌
        return answer;
    }
}
