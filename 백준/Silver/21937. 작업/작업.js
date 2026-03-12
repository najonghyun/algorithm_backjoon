const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
let count;

function bfs(n, adjList, inCount, x) {
    const q = [];
    for (let i = 1; i <= n; i++) {
        if (inCount[i] === 0) {
            q.push([i, 0]);
        }
    }
    let head = 0;
    while (head < q.length) {
        const [now, count] = q[head++];
        if (now === x) return count;

        for (const next of adjList[now] ?? []) {
            inCount[next]--;
            if (inCount[next] === 0) {
                q.push([next, count + 1]);
            }
        }
    }
}

function dfs(adjList, visited, now) {
    visited[now] = true;
    for (const next of adjList[now] ?? []) {
        if (visited[next]) continue;
        count++;
        dfs(adjList, visited, next);
    }
}
const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const inCount = new Array(N + 1).fill(0);
    const adjList = {};
    for (let i = 1; i <= M; i++) {
        const [A, B] = input[i].split(" ").map(Number);
        if (!adjList[B]) adjList[B] = [];
        adjList[B].push(A);
        inCount[B]++;
    }
    const X = Number(input[M + 1]);
    // console.log(adjList);
    // console.log(inCount.slice(1));

    // const result = bfs(N, adjList, inCount, X);
    count = 0;
    const visited = new Array(N + 1).fill(false);
    dfs(adjList, visited, X);
    console.log(count);
};
solution();
