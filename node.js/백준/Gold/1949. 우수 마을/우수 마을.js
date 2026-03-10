const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;

function dfs(adjList, citizen, visited, dp, now) {
    dp[now][1] = citizen[now];
    for (let next of adjList[now]) {
        if (visited[next]) continue;

        visited[next] = true;
        dfs(adjList, citizen, visited, dp, next);
        visited[next] = false;
        dp[now][0] += Math.max(dp[next][1], dp[next][0]);
        dp[now][1] += dp[next][0];
    }
}

const solution = () => {
    let index = 0;
    const N = Number(input[index++]);
    const citizen = input[index++].split(" ").map(Number);
    const adjList = {};
    for (let i = 0; i < N - 1; i++) {
        const [s, e] = input[index++].split(" ").map((v) => Number(v - 1));
        if (!adjList[s]) adjList[s] = [];
        if (!adjList[e]) adjList[e] = [];
        adjList[s].push(e);
        adjList[e].push(s);
    }

    // console.log(citizen);
    // console.log(adjList);

    const dp = Array.from({ length: N + 1 }, () => new Array(2).fill(0));
    const visited = new Array(N).fill(false);
    visited[0] = true;
    dfs(adjList, citizen, visited, dp, 0);

    console.log(Math.max(dp[0][0], dp[0][1]));

};
solution();
