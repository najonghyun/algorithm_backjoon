const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
const dirY = [-1, 1, 0, 0];
const dirX = [0, 0, 1, -1];
let result;
/**
 * 설명: 조합이랑 bfs 문제인데 조건 조심해야함.
 * 1. 비활성 바이러스는 지나갈 수 있음
 * 2. 빈것만 모두 방문하면 끝내야 최소 시간 구할 수 있음
 * 3. 빈게 없으면 그냥 0 출력
 */
function bfs(n, map, active, emptyCount) {
    const q = [];
    const visited = Array.from({ length: n }, () => new Array(n).fill(false));
    for (const [y, x] of active) {
        q.push([y, x, 0]);
        visited[y][x] = true;
    }
    let head = 0;
    let count = 0;
    let minTime = 0;
    while (q.length > head) {
        const [nowY, nowX, time] = q[head++];
        if (time > result) {
            return time;
        }
        minTime = time;
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (nextY >= n || nextY < 0 || nextX >= n || nextX < 0) {
                continue;
            }
            if (visited[nextY][nextX] || map[nextY][nextX] === 1) {
                continue;
            }
            q.push([nextY, nextX, time + 1]);
            visited[nextY][nextX] = true;
            if (map[nextY][nextX] !== 2) {
                count++;
                if (count === emptyCount) {
                    return time + 1;
                }
            }
        }
    }
    return emptyCount === count ? minTime : INF;
}

function comb(n, m, size, map, virus, active, emptyCount, cnt, start) {
    if (cnt === m) {
        const time = bfs(n, map, active, emptyCount);
        result = Math.min(result, time);
        return;
    }
    for (let i = start; i < size; i++) {
        active[cnt] = virus[i];
        comb(n, m, size, map, virus, active, emptyCount, cnt + 1, i + 1);
    }
}

const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const map = Array.from({ length: N }, () => new Array(N).fill(0));
    const virus = [];
    let emptyCount = 0;
    for (let i = 1; i <= N; i++) {
        const temp = input[i].split(" ").map(Number);
        for (let j = 0; j < N; j++) {
            map[i - 1][j] = temp[j];
            if (temp[j] === 2) {
                virus.push([i - 1, j]);
            }
            if (temp[j] === 0) {
                emptyCount++;
            }
        }
    }

    const active = new Array(M);
    result = INF;
    if (emptyCount === 0) {
        console.log(0);
    } else {
        comb(N, M, virus.length, map, virus, active, emptyCount, 0, 0);
        console.log(result === INF ? -1 : result);
    }
};
solution();
