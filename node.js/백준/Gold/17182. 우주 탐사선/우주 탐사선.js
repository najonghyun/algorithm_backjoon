const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
let result;

function bfs(adjMatrix, n, k) {
    const q = [];
    q.push([k, 0, (1 << k)]);
    while (q.length > 0) {
        const [now, time, visited] = q.shift();
        // console.log(now, time, visited);
        if (result < time) continue;
        if (visited === (1 << n) - 1) {
            result = Math.min(result, time);
            // console.log(result);
            continue;
        }
        for (let i = 0; i < n; i++) {
            if (i === now) continue;
            const newVisited = ((1 << i) & visited) !== 0 ? visited : (1 << i) + visited;
            q.push([i, time + adjMatrix[now][i], newVisited]);
        }
    }
}

function dfs(adjMatrix, n, now, time, visited) {
    if (visited === (1 << n) - 1) {
        result = Math.min(result, time);
        // console.log(result);
        return;
    }
    for (let i = 0; i < n; i++) {
        if (i === now || ((1 << i) & visited) !== 0) continue;
        const newVisited = (1 << i) + visited;
        dfs(adjMatrix, n, i, time + adjMatrix[now][i], newVisited);
    }
}

const solution = () => {
    const [N, K] = input[0].split(" ").map(Number);
    const adjMatrix = new Array(N);
    for (let i = 1; i <= N; i++) {
        adjMatrix[i - 1] = input[i].split(" ").map(Number);
    }
    // console.log(adjMatrix.join("\n"));
    result = INF;
    // bfs(adjMatrix, N, K);
    // dfs(adjMatrix, N, K, 0, (1 << K));

    for (let k = 0; k < N; k++) {
        for (let i = 0; i < N; i++) {
            if (i === k) continue;
            for (let j = 0; j < N; j++) {
                if (j === k || j === i) continue;
                adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
            }
        }
    }
    // console.log(adjMatrix.join("\n"));

    dfs(adjMatrix, N, K, 0, (1 << K));

    console.log(result);
};
solution();
