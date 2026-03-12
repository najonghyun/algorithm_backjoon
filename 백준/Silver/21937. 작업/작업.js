const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 위상정렬 알고리즘 인줄 알았는데 x값에 해당하는 선행들만 계산해서 그 개수를 구하는 것이므로
 * 그냥 단순하게 방향 반대로 설정하고 bfs로 x부터 시작해서 갈 수 있는 곳 개수의 합을 출력하면 된다.
 */
function bfs(n, adjList, start) {
    const q = [];
    const visited = new Array(n + 1).fill(false);
    q.push(start);
    visited[start] = true;
    let head = 0,
        count = 0;
    while (head < q.length) {
        const now = q[head++];
        for (const next of adjList[now] ?? []) {
            if (visited[next]) continue;
            count++;
            q.push(next);
            visited[next] = true;
        }
    }
    return count;
}
const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const adjList = {};
    for (let i = 1; i <= M; i++) {
        const [A, B] = input[i].split(" ").map(Number);
        if (!adjList[B]) adjList[B] = [];
        adjList[B].push(A);
    }
    const X = Number(input[M + 1]);
    const result = bfs(N, adjList, X);
    console.log(result);
};
solution();
