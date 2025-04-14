import java.util.*;

class Solution {
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