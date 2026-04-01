const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

function bfs(N, adjList, start, isMatch) {
    const q = [start];
    const visited = new Array(N).fill(false);
    visited[start] = true;
    let head = 0;
    while (head < q.length) {
        const now = q[head++];
        isMatch[start][now] = true;
        isMatch[now][start] = true;
        for (const next of adjList[now] ?? []) {
            if (visited[next]) continue;
            q.push(next);
            visited[next] = true;
        }
    }
}

const solution = () => {
    let index = 0;
    const N = Number(input[index++]);
    const M = Number(input[index++]);
    const adjList = {};
    for (let i = 0; i < M; i++) {
        const [a, b] = input[index++].split(" ").map((v) => Number(v - 1));
        if (!adjList[b]) adjList[b] = [];
        adjList[b].push(a);
    }

    const isMatch = Array.from({ length: N }, () => new Array(N).fill(false));
    for (let i = 0; i < N; i++) {
        bfs(N, adjList, i, isMatch);
    }

    const result = [];
    for (let i = 0; i < N; i++) {
        const count = isMatch[i].filter((v) => !v).length;
        result.push(count);
    }
    console.log(result.join("\n"));
};

solution();
