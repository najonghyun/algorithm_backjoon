const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    const [N, M, H] = input[0].split(" ").map(Number);
    const list = new Array(N).fill();
    for (let i = 1; i <= N; i++) {
        list[i - 1] = [0, ...input[i].split(" ").map(Number)];
    }
    // console.log(list);

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
    // for (let i = 0; i <= N; i++) {
    //     console.log(dp[i]);
    // }
    console.log(dp[N][H]);
};

solution();
