/**
*
* 제목 : 프로그래머스_단체사진찍기
* 설명 : 8명의 줄을 순서가 있도록 나열하고 그 중에 조건에 맞는 것을 찾기 위해 순열(중복 x)을 사용했고, 
* 비교는 일단 8! 밖에 안돼서 각 순열마다 비교를 해주었다. 다만 하나라도 적용 안되면 바로 return을 통해 가지치기 했다. 
*
* author: 나종현
*
*/
class Solution {
    int count;
    
    public boolean compare(String expression, char[] input){
        char a = expression.charAt(0);
        char b = expression.charAt(2);
        int indexA = -1, indexB = -1;
        for(int i=0; i<8; i++){
            if(input[i] == a) indexA = i;
            if(input[i] == b) indexB = i;
        }
        int gap = Math.abs(indexA - indexB) - 1;
        int dataGap = expression.charAt(4) - '0';
        if(expression.charAt(3) == '<'){
           if(gap < dataGap) return true;
        }else if(expression.charAt(3) == '>'){
            if(gap > dataGap) return true;
        }else{
            if(gap == dataGap) return true;
        }
        return false;
    }
    
    public void prem(int cnt, char[] friends, char[] input, boolean[] visited, int n, String[] data) {
        if(cnt == 8){
            for(String c : data){
                if(!compare(c, input)) return;
            }
            count++;
            return;
        }
        for(int i=0; i<8; i++){
            if(!visited[i]){
                input[cnt] = friends[i];
                visited[i] = true;
                prem(cnt+1, friends, input, visited, n, data);
                visited[i] = false;    
            }
        }
    }
    
    public int solution(int n, String[] data) {
        int answer = 0;
        char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        char[] input = new char[8];
        boolean[] visited = new boolean[8];
        prem(0, friends, input, visited, n, data);
        answer = count;
        
        return answer;
    }
}