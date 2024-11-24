import static java.lang.Integer.parseInt;
import java.util.*;
class Solution { 
    int size;
    boolean[] sosu, visited;
    char[] array;
    HashSet<Integer> answer;
    
    public void setPrime(){
        for(int i=2; i<10000000; i++){
            sosu[i] = true;
        }
        int root = (int) Math.sqrt(10000000);
        for(int i=2; i<root; i++){
            if(sosu[i]){
                for(int j=i*i; j<10000000; j+=i){
                    sosu[j] = false;
                }
            }
        }
    }
    public void prem(String numbers, int n, int cnt){
        if(cnt == n){
            String temp = "";
            for(int i=0; i<n; i++){
                temp += array[i];
            }
            int num = parseInt(temp);
            if(sosu[num]){
                answer.add(num);
            }
            return;
        }
        for(int i=0; i<size; i++){
            if(visited[i]){
                continue;
            }
            array[cnt] = numbers.charAt(i);
            visited[i] = true;
            prem(numbers, n, cnt+1);
            visited[i] = false;
        }
    }
    public int solution(String numbers) {
        size = numbers.length();
        visited = new boolean[size];
        sosu = new  boolean[10000000];
        setPrime();
        answer = new HashSet<>();
        for(int i=1; i<=size; i++){
            array = new char[i];
            prem(numbers, i, 0);
        }
        
        
        return answer.size();
    }
}