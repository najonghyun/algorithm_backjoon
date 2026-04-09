const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [1, -1, 0, 0];
const dirX = [0, 0, 1, -1];

function bfs(N, M, map, startY, startX) {
    let count = 0;
    const q = [];
    const visited = Array.from({ length: N }, () => new Array(M).fill(false));
    q.push([startY, startX]);
    visited[startY][startX] = true;
    let head = 0;
    while (head < q.length) {
        const [nowY, nowX] = q[head++];
        if (map[nowY][nowX] === 'P') {
            count++;
        }
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (nextY >= N || nextY < 0 || nextX >= M || nextX < 0 || visited[nextY][nextX] || map[nextY][nextX] === 'X') {
                continue;
            }
            q.push([nextY, nextX]);
            visited[nextY][nextX] = true;
        }
    }
    return count;
}

const solution = () => {
    let index = 0;
    const [N, M] = input[index++].split(" ").map(Number);
    const map = Array.from({ length: N }, () => new Array(M));
    let startY = 0, startX = 0;
    for (let i = 0; i < N; i++) {
        const temp = input[index++].split("");
        for (let j = 0; j < M; j++) {
            map[i][j] = temp[j];
            if (map[i][j] === 'I') {
                startY = i;
                startX = j;
            }
        }
    }
    const result = bfs(N, M, map, startY, startX);
    console.log(result === 0 ? 'TT' : result)
};

solution();
