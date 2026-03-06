const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [-1, 1, 0, 0];
const dirX = [0, 0, 1, -1];

function bfs(n, m, y, x, map, melts) {
    let count = 0;
    const q = [];
    const visited = Array.from({ length: n }, () => new Array(m).fill(false));
    q.push([y, x]);
    visited[y][x] = true;
    let head = 0;
    while (head < q.length) {
        const [nowY, nowX] = q[head++];
        count++;
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (visited[nextY][nextX] || map[nextY][nextX] === 0 || melts[nextY][nextX] > 0) {
                continue;
            }
            q.push([nextY, nextX]);
            visited[nextY][nextX] = true;
        }
    }
    return count;
}

function flow(n, m, map, ices, melts, count) {
    let head = 0;
    let prevYear = 0;
    while (head < ices.length) {
        const [nowY, nowX, size, year] = ices[head++];
        if (year !== prevYear) {
            const nowCount = bfs(n, m, nowY, nowX, map, melts);
            if (count !== nowCount) {
                return year;
            }
        }
        let zero = 0;
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (map[nextY][nextX] === 0 || (melts[nextY][nextX] !== 0 && melts[nextY][nextX] <= year)) {
                zero++;
            }
        }
        const nextSize = size - zero;
        if (nextSize <= 0) {
            melts[nowY][nowX] = year + 1;
            count--;
        } else {
            ices.push([nowY, nowX, nextSize, year + 1]);
        }
        prevYear = year;
    }
    return 0;
}

const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const map = new Array(N);
    for (let i = 1; i <= N; i++) {
        map[i - 1] = input[i].split(" ").map(Number);
    }
    // console.log(map.join("\n"));

    let count = 0;
    const ices = [];
    const melts = Array.from({ length: N }, () => new Array(M).fill(0));
    for (let i = 1; i < N - 1; i++) {
        for (let j = 1; j < M - 1; j++) {
            if (map[i][j] !== 0) {
                ices.push([i, j, map[i][j], 0]);
                count++;
            }
        }
    }

    const result = flow(N, M, map, ices, melts, count);
    console.log(result);

};
solution();
