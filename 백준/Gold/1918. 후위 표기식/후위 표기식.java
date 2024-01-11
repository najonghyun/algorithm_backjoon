import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
/**
 * 문제 : BJ_1918_후위표기식
 * 설명 : 생각보다 예외가 많아서 놀랐다. 후위와 전위로 바꾸는 법을 잘 이해해야 풀수 있는 문제였다
 * 알파벳과 연산자를 구분하여 넣고, 특정상황일때 예외가 나올 수 있는 것들을 다 해가면서 풀었다
 * @author 나종현
 *
 */
public class Main {
	static String notation;
	static Queue<Character> alphabets;
	static Stack<Character> operators;
	static StringBuilder sb;
	
	
	private static void postfix(char s) { // 크게 연산자와 알파벳으로 나눔
		if(s == ')') { // ')' 일때는 괄호가 닫혔으므로 '('괄호 전까지 pop 시킨다
			printResult(false);
			operators.pop();
		}else if(s == '+' || s == '-') { // + or - 인경우 그 전 스택에 있는것이  ( 괄호만 아니라면 무조건 출력한다
			if(!operators.isEmpty()) {
				if(operators.peek() != '(') {
					printResult(false);
				}
			}
			operators.push(s); // 그후 다시 연산자 담음
		}else if(s == '*' || s == '/') { // * or / 인경우 그 전 연산자가 같은 종류의 것일 때만 출력한다(연산자 우선순위 때문에) 
			if(!operators.isEmpty()) {
				if(operators.peek() == '*' || operators.peek() == '/') {
					printResult(true);
				}
			}
			operators.push(s);
		}else if(s == '(') {
			operators.push(s);
			
		}else {
			alphabets.offer(s);
		}
	}
	
	private static void printResult(boolean priority) { // priority가 true이면 곱셈,나눗셈이고 false이면 덧셈,뺄셈
		while(!alphabets.isEmpty()) { // 후위 표기식이므로 알파벳먼저 출력
			sb.append(alphabets.peek());
			alphabets.poll();
		}
		if(priority) { // *,/ 인 경우
			if(!operators.isEmpty()) { // 가장 위에 담겨있는 연산자 1개만 출력후 pop
				sb.append(operators.peek());
				operators.pop();
			}
		}else { // +,- 인 경우
			while(!operators.isEmpty()) {
				Character temp = operators.peek(); 
				if(temp == '(') { // 중간에 '('가 있다면 여기까지만 (짜피 이 함수 호출 후 '('는 pop한다 
					break;				
				}
				operators.pop();
				sb.append(temp); // 다시 덧,뺄이 나오기전까지는 계속 출력 and pop
				if(temp == '+' || temp == '-') {
					break;
				}
				
			}
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		notation = in.readLine();
		alphabets = new ArrayDeque<Character>(); // 알파벳은 담긴 순서대로 출력해야함으로 큐로 선언
		operators = new Stack<Character>(); // 연산자는 최근에 담은 순서로 출력해야 함으로 스택으로 선언 
		sb = new StringBuilder();
		
		int size = notation.length();
		for(int i=0; i<size; i++) {
			postfix(notation.charAt(i));
			if(i == size-1) {
				while(!alphabets.isEmpty()) {
					sb.append(alphabets.peek());
					alphabets.poll();
				}
				while(!operators.isEmpty()) {
					sb.append(operators.peek());
					operators.pop();
				}
			}
		}
		System.out.println(sb);
	

	}
}