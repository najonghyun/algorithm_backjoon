const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [-1, 1, 0, 0];
const dirX = [0, 0, -1, 1];
/**
 * 설명 : 길을 건너는 부분을 제외한 목초지 사이는 건널 수 있으므로 그부분을 bfs로 탐색하면서 영역을 나눴다.
 * 그리고 각 소의 쌍마다 비교해서 풀었다.
 * 제한시간 2초이므로 1억까지 가능한데 k <= 10000 이므로 o(k^(2))은 딱 1억이라 불필요한 연산만 없다면 가능하다.
 */

function bfs(N, map, visited, set, y, x, num) {
    const q = [];
    q.push([y, x]);
    visited[y][x] = true;
    let head = 0;
    while (head < q.length) {
        const [nowY, nowX] = q[head++];
        map[nowY][nowX] = num;
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            const key = `${nowY} ${nowX} ${nextY} ${nextX}`;
            if (nextY >= N || nextY < 0 || nextX >= N || nextX < 0 || visited[nextY][nextX] || set.has(key)) {
                continue;
            }
            q.push([nextY, nextX]);
            visited[nextY][nextX] = true;
        }
    }
}
const solution = () => {
    let index = 0;
    const [N, K, R] = input[index++].split(" ").map(Number);
    const set = new Set();
    const cows = new Array(K);
    for (let i = 0; i < R; i++) {
        const [r1, c1, r2, c2] = input[index++].split(" ").map((v) => Number(v - 1));
        const key = `${r1} ${c1} ${r2} ${c2}`;
        const rKey = `${r2} ${c2} ${r1} ${c1}`;
        set.add(key).add(rKey);
    }
    for (let i = 0; i < K; i++) {
        cows[i] = input[index++].split(" ").map((v) => Number(v - 1));
    }
    // console.log(set);

    const map = Array.from({ length: N }, () => new Array(N).fill(0));
    const visited = Array.from({ length: N }, () => new Array(N).fill(false));
    let num = 1;
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (visited[i][j]) continue;
            bfs(N, map, visited, set, i, j, num++);
        }
    }
    // console.log(map);

    let count = 0;
    for (let i = 0; i < K; i++) {
        for (let j = i + 1; j < K; j++) {
            const [y1, x1] = cows[i];
            const [y2, x2] = cows[j];
            if (map[y1][x1] !== map[y2][x2]) {
                count++;
            }
        }
    }
    console.log(count);
};

solution();
