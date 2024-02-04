import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> s = new Stack<>();
		Stack<Character> s2 = new Stack<>();
		
		String stringList = in.readLine();
		String bombList = in.readLine();
		
		int size = stringList.length();
		int bombSize = bombList.length();
		
		for(int i=0; i<size; i++) {
			char c = stringList.charAt(i);
			s.push(c);
			if(c == bombList.charAt(bombSize-1)) {
				
//				for(int j=bombSize-1; j>=0; j--) {
//					char temp = s.peek();
//					s2.push(temp);
//					s.pop();
//					if(temp != bombList.charAt(j)) {
//						while(!s2.isEmpty()) {
//							s.push(s2.pop());							
//						}
//						break;
//					}
//				}
//				
				
				int index = bombSize-1;
				while(index >= 0 && !s.isEmpty()) {
					char temp = s.peek();
					s2.push(temp);
					s.pop();
					if(temp != bombList.charAt(index)) {
						break;
					}
					index--;
				}
				
				while(!s2.isEmpty()) {
					char ww = s2.pop();
					if(index >= 0) {
						s.push(ww);							
					}
				}
			}
		}
		
		if(s.isEmpty()) {
			sb.append("FRULA");
		}else {
			while(!s.isEmpty()) {
				s2.push(s.pop());
			}
			
			while(!s2.isEmpty()) {
				sb.append(s2.pop());
			}
		}
	
		
		System.out.println(sb);
	}
}