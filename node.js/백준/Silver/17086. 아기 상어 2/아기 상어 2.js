const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
const dirY = [-1, -1, 0, 1, 1, 1, 0, -1];
const dirX = [0, 1, 1, 1, 0, -1, -1, -1];

function bfs(N, M, startY, startX, lenArr) {
    const q = [];
    const visited = Array.from({ length: N }, () => new Array(M).fill(false));
    q.push([startY, startX, 0]);
    visited[startY][startX] = true;
    let head = 0;
    while (head < q.length) {
        const [nowY, nowX, nowLength] = q[head++];
        lenArr[nowY][nowX] = Math.min(lenArr[nowY][nowX], nowLength);
        for (let d = 0; d < 8; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (nextY >= N || nextY < 0 || nextX >= M || nextX < 0 || visited[nextY][nextX]) {
                continue;
            }
            q.push([nextY, nextX, nowLength + 1]);
            visited[nextY][nextX] = true;
        }
    }

}

const solution = () => {
    let index = 0;
    const [N, M] = input[index++].split(" ").map(Number);
    const map = new Array(N);
    for (let i = 0; i < N; i++) {
        map[i] = input[index++].split(" ").map(Number);
    }
    const lenArr = Array.from({ length: N }, () => new Array(M).fill(INF));
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            if (map[i][j] === 1) {
                bfs(N, M, i, j, lenArr);
            }
        }
    }
    let result = 0;
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            result = Math.max(result, lenArr[i][j]);
        }
    }
    console.log(result);
};

solution();
