import java.util.*;
/**
*
* 제목 : 후보키
* 설명 : 일단 모든 경우의 수의 속성을 돌면서 유일성과 최소성을 만족하는지 봐야한다. 그래서 부분집합 활용!
* 1. 유일성 : 뽑은 속성의 값들을 하나의 string으로 합쳐서 같은게 있는지 비교함으로서 유일한지 판별
* 2. 최소성 : 유일성을 만족하는 것들은 미리 result 배열에 넣어놓고, 유일성에 들어가기전에 이미 그것을 포함한 집합이
* 존재하는지 확인한다. (이때, 단순 contains()를 사용하면 "0123이 03을 포함한다"에서 예외가 발생하므로 하나씩 봐야함)
* 3. 그렇게 판별하여 모두 만족하는 select 집합을 result 배열에 넣어주고 size를 출력한다.!
*
* author: 나종현
*
*/
class Solution {
    // 유일성을 만족하는지
    public boolean isUniqueKey(String[][] relation, int row, String select){ 
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<row; i++){
            String key = "";
            int size = select.length();
            for(int j=0; j<size; j++){
                key += relation[i][select.charAt(j)-'0'];
            }
            if(list.contains(key)) return false;
            list.add(key);
        }
        return true;
    }
    
    // 최소성을 만족하는지
    public boolean isMinKey(ArrayList<String> result, String select){
        for(String s : result){
            int size = s.length();
            boolean include = true;
            for(int i=0; i<size; i++){
                String temp = s.charAt(i) + "";
                if(!select.contains(temp)){
                    include = false;
                    break;
                };
            }
            if(include) return false;
        }
        return true;
    }
    
    public int solution(String[][] relation) {
        ArrayList<String> result = new ArrayList<>();
        int row = relation.length;
        int column = relation[0].length;
        
        for(int i=1; i<(1<<column); i++){
            String select = "";
            for(int j=0; j<column; j++){
                if((i & (1<<j)) != 0){
                    select += j;
                }
            }
            if(isMinKey(result, select) && isUniqueKey(relation, row, select)){
                result.add(select);
            }
        }
        return result.size();
    }
}
