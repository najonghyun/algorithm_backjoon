import java.util.*;

class Solution {
    static int N;
    static int[] dirY = {-1, 1, 0, 0, 1, -1, 1, -1, 1, 1, -1, -1};
    static int[] dirX = {0, 0, -1, 1, 1, 1, -1, -1, 1, -1, 1, -1};
    
    public boolean isCheck(int[][] board, int y, int x){
        if(y >= N || y < 0 || x >= N || x < 0 || board[y][x] == 1){
            return false;
        }
        return true;
    }
    
    public int bfs(int[][] board){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][N];
        q.offer(new int[]{0, 0, 0, 0, 1, 0});
        visited[0][0][0] = true;
        
        while(!q.isEmpty()){
            int state = q.peek()[0];
            int nowY1 = q.peek()[1];
            int nowX1 = q.peek()[2];
            int nowY2 = q.peek()[3];
            int nowX2 = q.peek()[4];
            int distance = q.peek()[5];
            q.poll();
            
            if((nowY1 == N-1 && nowX1 == N-1) || (nowY2 == N-1 && nowX2 == N-1)){
                return distance;
            }
            
            // 일직선
            for(int d=0; d<4; d++){
                int nextY1 = nowY1 + dirY[d];
                int nextX1 = nowX1 + dirX[d];
                int nextY2 = nowY2 + dirY[d];
                int nextX2 = nowX2 + dirX[d];
                if(!isCheck(board, nextY1, nextX1) || !isCheck(board, nextY2, nextX2)){
                    continue;
                }
                if(visited[state][nextY1][nextX1]){
                    continue;
                }
                q.offer(new int[]{state, nextY1, nextX1, nextY2, nextX2, distance+1});
                visited[state][nextY1][nextX1] = true;
            }
            
            // 가로일 때 회전
            if(state == 0){
                for(int d=4; d<6; d++){
                    int nextY = nowY1 + dirY[d];
                    if(!isCheck(board, nextY, nowX1)){
                        continue;
                    }
                    int nextX = nowX1 + dirX[d];
                    if(!isCheck(board, nextY, nextX)){
                        continue;
                    }
                    if(d == 4){
                        if(visited[1][nowY2][nowX2]){
                            continue;
                        }
                        q.offer(new int[]{1, nowY2, nowX2, nextY, nextX, distance+1});
                        visited[1][nowY2][nowX2] = true;
                    }else{
                        if(visited[1][nextY][nextX]){
                            continue;
                        }
                        q.offer(new int[]{1, nextY, nextX, nowY2, nowX2, distance+1});
                        visited[1][nextY][nextX] = true;
                    }
                }
                for(int d=6; d<8; d++){
                    int nextY = nowY2 + dirY[d];
                    if(!isCheck(board, nextY, nowX2)){
                        continue;
                    }
                    int nextX = nowX2 + dirX[d];
                    if(!isCheck(board, nextY, nextX)){
                        continue;
                    }
                    if(d == 6){
                        if(visited[1][nowY1][nowX1]){
                            continue;
                        }
                        q.offer(new int[]{1, nowY1, nowX1, nextY, nextX, distance+1});
                        visited[1][nowY1][nowX1] = true;
                    }else{
                        if(visited[1][nextY][nextX]){
                            continue;
                        }
                        q.offer(new int[]{1, nextY, nextX, nowY1, nowX1, distance+1});
                        visited[1][nextY][nextX] = true;
                    }
                }
            // 세로일 때 회전
            }else {
                for(int d=8; d<10; d++){
                    int nextX = nowX1 + dirX[d];
                    if(!isCheck(board, nowY1, nextX)){
                        continue;
                    }
                    int nextY = nowY1 + dirY[d];
                    if(!isCheck(board, nextY, nextX)){
                        continue;
                    }
                    if(d == 8){
                        if(visited[0][nowY2][nowX2]){
                            continue;
                        }
                        q.offer(new int[]{0, nowY2, nowX2, nextY, nextX, distance+1});
                        visited[0][nowY2][nowX2] = true; 
                    }else{
                        if(visited[0][nextY][nextX]){
                            continue;
                        }
                        q.offer(new int[]{0, nextY, nextX, nowY2, nowX2, distance+1});
                        visited[0][nextY][nextX] = true;
                    }
                }
                for(int d=10; d<12; d++){
                    int nextX = nowX2 + dirX[d];
                    if(!isCheck(board, nowY2, nextX)){
                        continue;
                    }
                    int nextY = nowY2 + dirY[d];
                    if(!isCheck(board, nextY, nextX)){
                        continue;
                    }
                    if(d == 10){
                        if(visited[0][nowY1][nowX1]){
                            continue;
                        }
                        q.offer(new int[]{0, nowY1, nowX1, nextY, nextX, distance+1});
                        visited[0][nowY1][nowX1] = true;
                    }else{
                        if(visited[0][nextY][nextX]){
                            continue;
                        }
                        q.offer(new int[]{0, nextY, nextX, nowY1, nowX1, distance+1});
                        visited[0][nextY][nextX] = true;
                    }
            
                }
            }
          
        }
        return -1;
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        answer = bfs(board);
        return answer;
    }
}