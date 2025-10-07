/**
* 문제 : 비밀지도
* 설명 : 비트연산자인 '|'를 이용하고 그걸 binaryString으로 변환 후 n으로 크기를 맟춰주고 decode한다.
*
* author : 나종현
*/
import static java.lang.Integer.toBinaryString;

class Solution {
    public String rowDecode(int n, String binaryRow){
        String fullRow = binaryRow;
        int diff = n - binaryRow.length();
        while(diff-- > 0){
            fullRow = "0" + fullRow;
        }
        String result = "";
        for(int i=0; i<n; i++){
            if(fullRow.charAt(i) == '0'){
                result += " ";
            }else{
                result += "#";
            }
        }
        return result;
    }
    

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i=0; i<n; i++){
            int row = arr1[i] | arr2[i];
            String binaryRow = toBinaryString(row);
            String resultRow = rowDecode(n, binaryRow);
            answer[i] = resultRow;
        }
       
        return answer;
    }
}