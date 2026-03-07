const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 핵심은 일반 dfs처럼 방문을 다시 초기화 하면 시간초과가 난다. 이미 방문한 곳은 다시 갈필요가 없다.
 * 다만 어디서 출발하는지에는 다시 방문을 초기화 해야 한다. 살짝 모호하거나 애매하다면
 * 플로이드 워샬 알고리즘 사용하면 편하게 풀 수 있다.
 */
function dfs(n, adjList, visited, start, now, result) {
    for (const next of adjList[now] ?? []) {
        result[start][next] = 1;
        if (!visited[next]) {
            visited[next] = true;
            dfs(n, adjList, visited, start, next, result);
        }
    }
}

const solution = () => {
    const N = Number(input[0]);
    const adjList = {};
    for (let i = 1; i <= N; i++) {
        const temp = input[i].split(" ").map(Number);
        for (let j = 0; j < N; j++) {
            if (temp[j] !== 0) {
                if (!adjList[i - 1]) adjList[i - 1] = [];
                adjList[i - 1].push(j);
            }
        }
    }

    const result = Array.from({ length: N }, () => new Array(N).fill(0));
    for (let i = 0; i < N; i++) {
        const visited = new Array(N).fill(false);
        dfs(N, adjList, visited, i, i, result);
    }
    for (const arr of result) {
        console.log(arr.join(" "));
    }

};
solution();
