const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 안뽑는 경우의 수도 더하기 위해 각각의 높이에 0을 기본으로 추가하고 이제 반드시 하나는 뽑아야 하는
 * 경우의 수를 dp로 풀었다.
 */
const solution = () => {
    const [N, M, H] = input[0].split(" ").map(Number);
    const list = new Array(N).fill();
    for (let i = 1; i <= N; i++) {
        list[i - 1] = [0, ...input[i].split(" ").map(Number)];
    }

    const dp = Array.from({ length: N + 1 }, () => new Array(H + 1).fill(0));
    dp[0][0] = 1;
    for (let i = 0; i < N; i++) {
        for (const h of list[i]) {
            for (let j = 0; j <= H; j++) {
                if (j >= h) {
                    dp[i + 1][j] = (dp[i + 1][j] + dp[i][j - h]) % 10007;
                }
            }
        }
    }

    console.log(dp[N][H]);
};

solution();
