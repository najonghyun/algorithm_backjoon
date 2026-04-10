const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Math.floor(Number.MAX_VALUE / 2);
/**
 * 설명 : 관계의 수를 구해야 하므로 경로를 1로 잡은 다익스트라로 풀었다.
 * 물론 N <= 100 이므로 플로이드-워샬도 가능하다. 우선순위큐로 시간 더 줄여도 되지만 이미 넉넉하므로 하지 않았다.
 */
function dijkstra(N, adjList, start) {
    const distance = new Array(N + 1).fill(INF);
    const visited = new Array(N + 1).fill(false);
    distance[start] = 0;
    for (let c = 0; c < N; c++) {
        let current = -1;
        let min = INF;
        for (let i = 1; i <= N; i++) {
            if (!visited[i] && min > distance[i]) {
                current = i;
                min = distance[i];
            }
        }
        if (current === -1) break;
        visited[current] = true;
        for (const next of adjList[current] ?? []) {
            if (distance[next] > min + 1) {
                distance[next] = min + 1;
            }
        }
    }
    let sum = 0;
    for (let i = 1; i <= N; i++) {
        sum += distance[i];
    }
    return sum;
}
const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const adjList = {};
    for (let i = 1; i <= M; i++) {
        const [A, B] = input[i].split(" ").map(Number);
        if (!adjList[A]) adjList[A] = [];
        if (!adjList[B]) adjList[B] = [];
        adjList[A].push(B);
        adjList[B].push(A);
    }

    let result = -1,
        min = INF;
    for (let i = 1; i <= N; i++) {
        const kbCount = dijkstra(N, adjList, i);
        if (min > kbCount) {
            result = i;
            min = kbCount;
        }
    }
    console.log(result);
};
solution();
