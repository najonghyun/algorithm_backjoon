const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

function dfs(n, list, dp, visited, i, count, path, pathSize) {
    if (visited[i] || dp[i] === -1) {
        for (let c = 0; c < pathSize; c++) {
            dp[path[c]] = -1;
        }
        return;
    }
    if (i === n - 1 || dp[i] > 0) {
        for (let c = 0; c < pathSize; c++) {
            dp[path[c]] = pathSize - c + dp[i];
        }
        return;
    }

    visited[i] = true;
    path.push(i);
    dfs(n, list, dp, visited, list[i], count + 1, path, path.length);
    visited[i] = false;
    path.pop();
}

const solution = () => {
    const n = Number(input[0]);
    const list = input[1].split(" ").map(v => Number(v) - 1);
    // console.log(list);

    const visited = new Array(n).fill(false);
    const dp = new Array(n).fill(0);
    const path = [];
    for (let i = 0; i < n; i++) {
        if (dp[i] !== 0) continue;
        dfs(n, list, dp, visited, i, 0, path, path.length);
    }

    console.log(dp.join("\n"));
};
solution();
