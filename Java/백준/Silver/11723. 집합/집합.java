import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(in.readLine());

        boolean[] S = new boolean[21];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String command = st.nextToken();
            int num = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;

            switch (command) {
                case "add":
                    S[num] = true;
                    break;

                case "remove":
                    S[num] = false;
                    break;

                case "check":
                    sb.append(S[num] ? 1 : 0).append('\n');
                    break;

                case "toggle":
                    S[num] = !S[num];
                    break;

                case "all":
                    for (int j = 1; j <= 20; j++) {
                        S[j] = true;
                    }
                    break;

                case "empty":
                    for (int j = 1; j <= 20; j++) {
                        S[j] = false;
                    }
                    break;
            }
        }

        System.out.print(sb);
    }
}