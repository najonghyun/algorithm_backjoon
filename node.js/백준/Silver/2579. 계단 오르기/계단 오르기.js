const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : dfs는 시간초과고 dp를 사용해야 한다. 점화식을 잘 찾아야 한다.
 */
const solution = () => {
    const N = Number(input[0]);
    const list = new Array(N);
    for (let i = 1; i <= N; i++) {
        list[i - 1] = Number(input[i]);
    }
    const dp = new Array(N).fill(0);
    if (N > 0) {
        dp[0] = list[0];
    }
    if (N > 1) {
        dp[1] = dp[0] + list[1];
    }
    if (N > 2) {
        dp[2] = Math.max(list[0] + list[2], list[1] + list[2]);
    }
    for (let i = 3; i < N; i++) {
        dp[i] = Math.max(dp[i - 2] + list[i], dp[i - 3] + list[i - 1] + list[i])
    }
    console.log(dp[N - 1]);
};
solution();
