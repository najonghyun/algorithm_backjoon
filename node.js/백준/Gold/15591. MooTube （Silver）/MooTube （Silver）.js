const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
let count;

function dfs(adjList, visited, now, k) {
    for (const [next, nextR] of adjList[now]) {
        if (visited[next]) continue;
        if (nextR >= k) {
            count++;
            visited[next] = true;
            dfs(adjList, visited, next, k);
        }
    }
}

const solution = () => {
    let index = 0;
    const [N, Q] = input[index++].split(" ").map(Number);
    const adjList = {};
    for (let i = 0; i < N - 1; i++) {
        const [p, q, r] = input[index++].split(" ").map(Number);
        if (!adjList[p]) adjList[p] = [];
        if (!adjList[q]) adjList[q] = [];
        adjList[p].push([q, r]);
        adjList[q].push([p, r]);
    }

    const result = [];
    for (let i = 0; i < Q; i++) {
        const [k, v] = input[index++].split(" ").map(Number);
        const visited = new Array(N + 1).fill(false);
        count = 0;
        visited[v] = true;
        dfs(adjList, visited, v, k);
        result.push(count);
    }

    // console.log(adjList);
    console.log(result.join("\n"));

};
solution();
