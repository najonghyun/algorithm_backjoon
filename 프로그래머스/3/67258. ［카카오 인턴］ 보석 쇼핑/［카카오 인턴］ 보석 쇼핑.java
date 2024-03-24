import java.io.*;
import java.util.*;
import java.util.Map.Entry;
/**
* 문제 : 보석 쇼핑
* 설명 : start를 0에서 부터 시작해서 end와 같아지면 그때 최신화를 해주어야 한다. 이때 길이 고려해서 더 짧아진 경우에만 해준다.
*author : 나종현
*/
class Solution {
    static int N;
    static Map<String, Integer> dict;
    static int start, end;
    static int INF = Integer.MAX_VALUE;
    
    public int getMinIndex(){
        int min = INF;
        for(Entry<String, Integer> entrySet : dict.entrySet()){
            min = Math.min(min, entrySet.getValue());
        }
        return min == INF ? 0 : min;
    }
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        N = gems.length;
        dict = new HashMap<String, Integer>();
        for(int i=0; i<N; i++){
            if(!dict.containsKey(gems[i])){
                end = i;
                dict.put(gems[i], i);
                answer[0] = start+1;
                answer[1] = end+1; 
            }else{
                end = i;
                dict.put(gems[i], i);
                if(gems[start].contains(gems[end])){
                    start = getMinIndex();
                    if(answer[1] - answer[0] > end - start){
                        answer[0] = start+1;
                        answer[1] = end+1;
                    }
                }
            }
          
        }
       
        return answer;
    }
}