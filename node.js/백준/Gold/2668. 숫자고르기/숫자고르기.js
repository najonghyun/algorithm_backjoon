const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;

function dfs(n, list, visited, isChecked, rootI, i) {
    if (rootI === i) {
        visited[rootI] = true;
        visited[i] = true;
        for (let c = 0; c < n; c++) {
            if (isChecked[c]) {
                visited[c] = true;
            }
        }
        return;
    }
    if (!isChecked[i]) {
        isChecked[i] = true;
        dfs(n, list, visited, isChecked, rootI, list[i]);
        isChecked[i] = false;
    }
}

const solution = () => {
    const list = [];
    const n = Number(input[0]);
    for (let i = 1; i <= n; i++) {
        list.push(Number(input[i] - 1));
    }
    // console.log(list);
    const visited = new Array(n).fill(false);
    const isChecked = new Array(n).fill(false);
    for (let i = 0; i < n; i++) {
        if (visited[i]) continue;
        dfs(n, list, visited, isChecked, i, list[i]);
    }
    // console.log(visited);
    const result = [];
    for (let i = 0; i < n; i++) {
        if (visited[i]) {
            result.push(i + 1);
        }
    }
    console.log(result.length);
    console.log(result.join("\n"));
};
solution();
