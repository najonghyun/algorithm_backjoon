const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function detetch(n, sticker, dp) {
    dp[0][0] = sticker[0][0];
    dp[1][0] = sticker[1][0];
    dp[0][1] = dp[0][0] + sticker[1][1];
    dp[1][1] = dp[1][0] + sticker[0][1];
    for (let i = 2; i < n; i++) {
        if (i % 2 == 1) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 2]) + sticker[1][i];
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 2]) + sticker[0][i];
        } else {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 2]) + sticker[0][i];
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 2]) + sticker[1][i];
        }
    }
}
const solution = () => {
    let index = 0;
    let T = Number(input[index++]);

    while (T--) {
        const n = Number(input[index++]);
        const sticker = new Array(2);
        for (let i = 0; i < 2; i++) {
            sticker[i] = input[index++].split(" ").map(Number);
        }

        const dp = Array.from({ length: 2 }, () => new Array(n).fill(0));
        detetch(n, sticker, dp);
        console.log(Math.max(dp[0][n - 1], dp[1][n - 1]));
    }
}

solution();