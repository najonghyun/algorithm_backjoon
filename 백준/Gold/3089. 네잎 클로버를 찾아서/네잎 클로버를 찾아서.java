import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;



public class Main {
	static int N, M;
	static int nowX, nowY;
	static TreeSet<Integer>[] setX;
	static TreeSet<Integer>[] setY;

	private static void move(char c) {
		if(c == 'L') {
			nowX = setY[nowY].lower(nowX);
		}else if(c == 'R') {
			nowX = setY[nowY].higher(nowX);
		}else if(c == 'U') {
			nowY = setX[nowX].higher(nowY);			
		}else if(c == 'D') {
			nowY = setX[nowX].lower(nowY);						
		}
	}
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		setX = new TreeSet[200000];
		setY = new TreeSet[200000];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = parseInt(st.nextToken())+100000;
			int y = parseInt(st.nextToken())+100000;
			if(setX[x] == null) setX[x] = new TreeSet<>();
			if(setY[y] == null) setY[y] = new TreeSet<>();
			setX[x].add(y);
			setY[y].add(x);
			
		}
		
		nowX = 100000;
		nowY = 100000;
		String dir = in.readLine();
		for(int i=0; i<dir.length(); i++) {
			move(dir.charAt(i));
		}
		
		System.out.println((nowX-100000) + " " + (nowY-100000));
		
		
	}

}