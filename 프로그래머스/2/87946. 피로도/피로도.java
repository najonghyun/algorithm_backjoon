class Solution {
    static int n, answer;
    static int[] number;
    static boolean[] visited;
    
    public void perm(int k, int[][] dungeons, int cnt){
        if(cnt == n){
            int now = k;
            for(int i=0; i<n; i++){
                if(dungeons[number[i]][0] > now){
                    break;
                }
                answer = Math.max(answer, i+1);
                now -= dungeons[number[i]][1];
            }
            return;
        }
        for(int i=0; i<n; i++){
            if(visited[i]){
                continue;
            }
            number[cnt] = i;
            visited[i] = true;
            perm(k, dungeons, cnt+1);
            visited[i] = false;
        }
    }
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        n = dungeons.length;
        number = new int[n];
        visited = new boolean[n];
        perm(k, dungeons, 0);
        
        return answer;
    }
}