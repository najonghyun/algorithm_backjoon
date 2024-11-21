class Solution {
    int[] answer;
    public void match(int row, int col, int brown){
        int num = (row*2)+(col*2)+4; 
        if(num == brown){
            answer[0] = row+2;
            answer[1] = col+2;
        }
    }
    public int[] solution(int brown, int yellow) {
        answer = new int[2];
        
        int i=1;
        int now = yellow;
        while(now >= i){
            if(yellow % i == 0){
                match(now, i, brown);
            }
            i++;
            now = yellow/i;
        }
       
        return answer;
    }
}