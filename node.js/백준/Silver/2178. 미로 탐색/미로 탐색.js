const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [1, -1, 0, 0];
const dirX = [0, 0, 1, -1];

function bfs(N, M, map) {
    const q = [];
    const visited = Array.from({ length: N }, () => new Array(M));
    q.push([0, 0, 1]);
    visited[0][0] = true;
    let head = 0;
    let length = 0;
    while (head < q.length) {
        const [nowY, nowX, count] = q[head++];
        if (nowY === N - 1 && nowX === M - 1) {
            length = count;
            break;
        }
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (nextY >= N || nextY < 0 || nextX >= M || nextX < 0 || visited[nextY][nextX] || map[nextY][nextX] === 0) {
                continue;
            }
            q.push([nextY, nextX, count + 1]);
            visited[nextY][nextX] = true;
        }
    }
    return length;
}
const solution = () => {
    let index = 0;
    const [N, M] = input[index++].split(" ").map(Number);
    const map = new Array(N);
    for (let i = 0; i < N; i++) {
        map[i] = input[index++].split("").map(Number);
    }
    const result = bfs(N, M, map);
    console.log(result);
};
solution();
