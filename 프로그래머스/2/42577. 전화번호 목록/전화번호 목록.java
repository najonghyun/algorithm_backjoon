import java.util.*;

class Solution {
    public boolean compare_number(String a, String b, int size){
        for(int i=0; i<size; i++){
            if(a.charAt(i) != b.charAt(i)){
                return false;
            }
        }
        return true;
    }
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        int n = phone_book.length;
        
        Arrays.sort(phone_book);
         
        for(int i=0; i<n-1; i++){
            int size = phone_book[i].length();
            if(compare_number(phone_book[i], phone_book[i+1], size)){
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}