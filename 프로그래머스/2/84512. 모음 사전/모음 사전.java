import java.util.*;

class Solution {
    static String[] moem = {"A", "E", "I", "O", "U"};
    static String[] strings;
    static ArrayList<String> list;
    
    public void perm(int n, int cnt){
        if(cnt == n){
            String temp = "";
            for(int i=0; i<n; i++){
                temp += strings[i];
            }
            list.add(temp);
            return;
        }
        for(int i=0; i<5; i++){
            strings[cnt] = moem[i];
            perm(n, cnt+1);
        }        
    }
    
    public int solution(String word) {
        int answer = 0;
        list = new ArrayList<>();
        for(int i=1; i<=5; i++){
            strings = new String[i];
            perm(i, 0);
        }

        Collections.sort(list);
        int num = 1;
        for(String temp : list){
            if(temp.equals(word)){
                answer = num;
                break;
            }
            num++;
        }
        return answer;
    }
}