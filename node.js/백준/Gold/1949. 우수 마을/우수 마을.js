const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 트리 최대 독립집합
 * 설명 : 일단 완탐을 해야 할것 같고, 시간초과면 거의 dp이다. 그중에서도 이 문제는 (선택or미선택) 인 2가지 경우이므로 
 * dp[v][0] : 선택 안했을 때, dp[v][1] : 선택 했을 때 로 나누어서 거꾸로 재귀(dfs)를 사용한다.
 */

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

    const dp = Array.from({ length: N + 1 }, () => new Array(2).fill(0));
    const visited = new Array(N).fill(false);
    visited[0] = true;
    dfs(adjList, citizen, visited, dp, 0);

    console.log(Math.max(dp[0][0], dp[0][1]));

};
solution();
