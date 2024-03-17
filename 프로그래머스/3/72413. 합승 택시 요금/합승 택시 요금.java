import java.io.*;
import java.util.*;

class Solution {
    static int[][] adjMatrix;
    static int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        adjMatrix = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            Arrays.fill(adjMatrix[i], INF);
        }
        
        for(int[] arr : fares){
            int c = arr[0];
            int d = arr[1];
            int f = arr[2];
             adjMatrix[c][d] = adjMatrix[d][c] = f;  
        }
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                if(k == i){
                    adjMatrix[k][i] = 0;
                    continue;
                }
                for(int j=1; j<=n; j++){
                    if(j == i || j == k || adjMatrix[k][j] == INF || adjMatrix[i][k] == INF){
                        continue;
                    }
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]); 
                }
            }
        }
        
        for(int x=1; x<=n; x++){
            if(adjMatrix[s][x] == INF || adjMatrix[x][a] == INF || adjMatrix[x][b] == INF){
                continue;
            }
            answer = Math.min(answer, adjMatrix[s][x] + adjMatrix[x][a] + adjMatrix[x][b]);
        }
       
        return answer;
    }
}