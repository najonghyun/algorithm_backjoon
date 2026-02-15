const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 일단 dp로 해야하는데 dp[n][j] = dp[n-1][j가 아닌값] + cats[i][j]
 * 이 성립하는데 저 j가 아닌 값을 모두 구하면 시간초과가 난다. 그래서 그중에 최대 2개만 구해서 가장 최대값이 현재 j로 끝나는거면 가장 최대값 
 * 아니면 그다음 최대값을 넣어주면 된다.
 */
const solution = () => {
    const [n, k] = input[0].split(" ").map(Number);
    const cats = Array.from({ length: n }, () => new Array(k));
    for (let i = 1; i <= n; i++) {
        cats[i - 1] = input[i].split(" ").map(Number);
    }

    const dp = Array.from({ length: n }, () => new Array(k));
    for (let i = 0; i < k; i++) {
        dp[0][i] = cats[0][i];
    }

    for (let i = 1; i < n; i++) {
        let max = [0, 0];
        let now = -1;
        for (let j = 0; j < k; j++) {
            if (max[0] <= dp[i - 1][j]) {
                max[1] = max[0];
                max[0] = dp[i - 1][j];
                now = j;
            } else if (max[1] <= dp[i - 1][j]) {
                max[1] = dp[i - 1][j];
            }
        }
        for (let j = 0; j < k; j++) {
            if (now === j) {
                dp[i][j] = max[1] + cats[i][j];
            } else {
                dp[i][j] = max[0] + cats[i][j];
            }
        }
    }

    let result = 0;
    for (let i = 0; i < k; i++) {
        result = Math.max(result, dp[n - 1][i]);
    }
    console.log(result);
};
solution();
