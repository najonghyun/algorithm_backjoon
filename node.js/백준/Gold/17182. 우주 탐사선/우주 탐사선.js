const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
let result;
/**
 * 설명 : 중복이 가능하므로 각각의 최소거리를 플로이드워샬로 담고 그 후 dfs를 돌려서 해결했다.
 */
function dfs(adjMatrix, n, now, time, visited) {
    if (visited === (1 << n) - 1) {
        result = Math.min(result, time);
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

    result = INF;
    for (let k = 0; k < N; k++) {
        for (let i = 0; i < N; i++) {
            if (i === k) continue;
            for (let j = 0; j < N; j++) {
                if (j === k || j === i) continue;
                adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
            }
        }
    }
    dfs(adjMatrix, N, K, 0, (1 << K));
    console.log(result);
};
solution();
