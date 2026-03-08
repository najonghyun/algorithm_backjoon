const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
let count;
/**
 * 이 문제는 경로를 이용하는 문제라 플로이드 워샬로도 가능하지만 o(N^3)로 시간초과가 난다. 그래서 문제에 쿼리에
 * 시작지점부터 갈 수있는 곳의 개수를 구하는 방식으로 한다면 o(NQ)의 시간복잡도로 해결 가능하다. 
 */
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

    console.log(result.join("\n"));

};
solution();
