class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
		int match = n;
		int nextA = (a+1)/2;
		int nextB = (b+1)/2;
		
		while(match > 1) {
			answer++;	
			if(nextA == nextB) {
				break;
			}
			match /= 2;
			nextA = (nextA+1)/2;
			nextB = (nextB+1)/2;
		}
        
	    return answer;
    }
}