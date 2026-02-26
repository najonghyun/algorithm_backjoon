const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);


const solution = () => {
    const N = Number(input[0]);
    const list = [];
    for (let i = 1; i <= N; i++) {
        list.push(Number(input[i]));
    }
    
    const dp = new Array(N);
    for (let i = 0; i < N; i++) {
        dp[i] = 1;
        for (let j = 0; j < i; j++) {
            if (list[j] < list[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }

    let maxLIS = 0;
    for (let i = 0; i < N; i++) {
        maxLIS = Math.max(maxLIS, dp[i]);
    }
    console.log(N - maxLIS);
};
solution();
