const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;

function isFriends(list, i, j) {
    const [s1, e1] = list[i];
    const [s2, e2] = list[j];
    if ((e1 >= s2 && e1 <= e2) || (e2 >= s1 && e2 <= e1)) return true;
    return false;
}

const solution = () => {
    let index = 0;
    const N = Number(input[index++]);
    const list = new Array(N);
    for (let i = 0; i < N; i++) {
        list[i] = input[index++].split(" ").map(Number);
    }
    // console.log(list);
    const dp = Array.from({ length: N }, () => new Array(N));

    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (i === j) {
                dp[i][j] = [list[i][0], list[i][1], 0];
            } else {
                if (isFriends(list, i, j)) {
                    const minS = Math.min(list[i][0], list[j][0]);
                    const maxE = Math.max(list[i][1], list[j][1]);
                    dp[i][j] = [minS, maxE, 1];
                } else {
                    dp[i][j] = [INF, INF, INF];
                }
            }
        }
    }

    // console.log(dp.join("\n"));
    for (let k = 0; k < N; k++) {
        for (let i = 0; i < N; i++) {
            if (k === i) continue;
            for (let j = 0; j < N; j++) {
                if (j === k || j === i) continue;
                if (dp[i][k][2] === INF || dp[k][j][2] === INF) continue;
                if (dp[i][j][2] > dp[i][k][2] + dp[k][j][2]) {
                    const minS = Math.min(dp[i][0], dp[j][0]);
                    const maxE = Math.max(dp[i][1], dp[j][1]);
                    const length = Math.min(dp[i][j][2], dp[i][k][2] + dp[k][j][2]);
                    dp[i][j] = [minS, maxE, length];
                };
            }
        }
    }
    // console.log(dp.join("\n"));

    const Q = Number(input[index++]);
    for (let i = 0; i < Q; i++) {
        const [A, B] = input[index++].split(" ").map(Number);
        console.log(dp[A - 1][B - 1][2] === INF ? -1 : dp[A - 1][B - 1][2]);
    }
};
solution();
