class Solution {
    // static int[][] selected;
    static int[][] adjMatrix;
    static boolean[] visited;
//     public void comb(int n, int[][] wires, int cnt, int start){
//         if(cnt == n-2){
//             for(int i=0; i<n-2; i++){
                
//             }
//             return;
//         }
//         for(int i=start; i<n-1; i++){
//             selected[cnt][0] = wires[i][0];
//             selected[cnt][1] = wires[i][1];
//             comb(n, wires, cnt+1, i+1);
//         }
//     }
    public void scan(int n, int now){
        visited[now] = true;
        for(int i=0; i<n; i++){
            if(adjMatrix[now][i] != 1 || visited[i]){
                continue;
            }
            scan(n, i);
        }
    }
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<n-1; i++){
            adjMatrix = new int[n][n];
            visited = new boolean[n];
            for(int j=0; j<n-1; j++){
                if(i == j){
                    continue;
                }
                adjMatrix[wires[j][0]-1][wires[j][1]-1] = adjMatrix[wires[j][1]-1][wires[j][0]-1] = 1;
            }
            scan(n, 0);
            int temp = 0;
            for(int j=0; j<n; j++){
                if(visited[j]){
                    temp++;
                }
            }
            answer = Math.min(answer, Math.abs(temp - (n-temp)));
        }
        
        return answer;
    }
}