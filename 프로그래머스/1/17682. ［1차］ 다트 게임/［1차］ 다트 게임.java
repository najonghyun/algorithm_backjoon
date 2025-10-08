/**
* 문제 : 다트게임
* 설명 : 3개의 게임을 하나씩 쪼개서 각각 함수로 계산, subString()과 contains() 잘 이해하자.
*
* author: 나종현
*/
import static java.lang.Integer.parseInt;

class Solution {
    static String bonus = "SDT";
    static String option = "*#";
    
    public void bonusCalculate(int[] result, int i, int number, char bo){
        if(bo == 'S'){
            result[i] = number;
        }else if(bo == 'D'){
            result[i] = (int) Math.pow(number, 2);
        }else if(bo == 'T'){
            result[i] = (int) Math.pow(number, 3);    
        }
    }
    
    public void optionCalculate(int[] result, int i, char op){
        if(op == '*'){
            result[i] = result[i] * 2;
            if(i != 0){
                result[i-1] = result[i-1] * 2;
            }
        }else if(op == '#'){
            result[i] = result[i] * -1;
        }
    }
    
    public void scoreCalculate(int[] result, String dartResult){
        int start = 0;
        int end = 0;
        int length = dartResult.length();
        for(int i=0; i<3; i++){
            while(!bonus.contains(dartResult.substring(end, end+1))){
                end++;
            }
            int number = parseInt(dartResult.substring(start, end));
            char bo = dartResult.charAt(end++);
            bonusCalculate(result, i, number, bo);
            if(length > end && option.contains(dartResult.substring(end, end+1))){
                char op = dartResult.charAt(end++);
                optionCalculate(result, i, op);
            }
            start = end;
        }
    }
    
    public int solution(String dartResult) {
        int answer = 0;
        int[] result = new int[3];
        
        scoreCalculate(result, dartResult);
        
        for(int value : result){
            answer += value;
        }
        
        return answer;
    }
}