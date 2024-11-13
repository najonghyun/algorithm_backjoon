import java.util.*;

class Solution {
    static int INF = Integer.MAX_VALUE;
    public int solution(int n, int[][] costs) {
        ArrayList<int[]>[] adjList = new ArrayList[n];
        for(int i=0; i<n; i++){
            adjList[i] = new ArrayList<>();
        }
        for(int[] arr : costs){
            adjList[arr[0]].add(new int[]{arr[1], arr[2]});
            adjList[arr[1]].add(new int[]{arr[0], arr[2]});
        }
        boolean[] visited = new boolean[n];
        int[] minEdge = new int[n];
        Arrays.fill(minEdge, INF);
        minEdge[0] = 0;
        int answer = 0;
        for(int c=0; c<n; c++){
            int current = -1;
            int min = INF;
            for(int i=0; i<n; i++){
                if(!visited[i] && min > minEdge[i]){
                    current = i;
                    min = minEdge[i];
                }
            }
            if(current == -1){
                break;
            }
            visited[current] = true;
            answer += minEdge[current];
            
            for(int[] arr : adjList[current]){
                if(minEdge[arr[0]] > arr[1]){
                    minEdge[arr[0]] = arr[1];
                }
            }
        }
        return answer;
    }
}