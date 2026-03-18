const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;

const solution = () => {
    const n = Number(input[0]);
    const sqrtN = Math.floor(Math.sqrt(n));
    const dp = new Array(n + 1).fill(INF);
    dp[0] = 0;
    dp[1] = 1;
    for (let i = 2; i <= n; i++) {
        const sqrtI = Math.floor(Math.sqrt(i));
        for (let j = 1; j <= sqrtI; j++) {
            const n2 = j * j;
            dp[i] = Math.min(dp[i], dp[i - n2] + 1);
        }
    }
    console.log(dp[n]);

};
solution();
