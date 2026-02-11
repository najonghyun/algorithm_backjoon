const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = 2e6;

const solution = () => {
    const [n, k] = input[0].split(" ").map(Number);
    const list = new Array(n);
    for (let i = 1; i <= n; i++) {
        list[i - 1] = input[i].split(" ").map(Number);
    }
    // console.log(list);
    const adjMatrix = Array.from({ length: n }, () => new Array(n).fill(0));
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (i === j) continue;
            adjMatrix[i][j] = Math.abs(list[i][0] - list[j][0]) + Math.abs(list[i][1] - list[j][1]);
        }
    }
    // console.log(adjMatrix.join("\n"));
    const dp = Array.from({ length: n }, () => new Array(k + 1).fill(INF));
    dp[0][0] = 0;
    for (let i = 1; i < n; i++) {
        dp[i][0] = dp[i - 1][0] + adjMatrix[i - 1][i];
    }
    for (let s = 1; s <= k; s++) {
        for (let i = 1; i < n; i++) {
            const jMin = Math.max(0, i - s - 1);
            for (let j = i - 1; j >= jMin; j--) {
                const skip = i - j - 1;
                dp[i][s] = Math.min(dp[i][s], dp[j][s - skip] + adjMatrix[j][i]);
            }
        }
    }
    // console.log(dp);
    let result = INF;
    for (const num of dp[n - 1]) {
        result = Math.min(result, num);
    }
    console.log(result);
};
solution();
