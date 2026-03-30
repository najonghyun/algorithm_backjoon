const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
const dirY = [-1, 1, 0, 0];
const dirX = [0, 0, -1, 1];

class PriorityQueue {
    constructor() {
        this.heap = [];
    }
    size() {
        return this.heap.length;
    }
    push(value) {
        this.heap.push(value);
        this._up();
    }
    pop() {
        if (this.size() === 1) return this.heap.pop();
        const root = this.heap[0];
        this.heap[0] = this.heap.pop();
        this._down();
        return root;
    }
    _up() {
        let i = this.size() - 1;
        while (i > 0) {
            const p = Math.floor((i - 1) / 2);
            if (this.heap[p][0] <= this.heap[i][0]) break;
            [this.heap[p], this.heap[i]] = [this.heap[i], this.heap[p]];
            i = p;
        }
    }
    _down() {
        let i = 0;
        const n = this.size();
        while (true) {
            let min = i;
            let left = i * 2 + 1;
            let right = i * 2 + 2;
            if (left < n && this.heap[min][0] > this.heap[left][0]) min = left;
            if (right < n && this.heap[min][0] > this.heap[right][0]) min = right;
            if (i == min) break;

            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
}

function dijkstra(N, warps, dp, t) {
    const pq = new PriorityQueue();
    dp[0][0][0] = 0;
    pq.push([dp[0][0][0], 0, 0, 0]);
    while (pq.size() > 0) {
        const [min, mode, y, x] = pq.pop();
        if (mode === 0) {
            for (let d = 0; d < 4; d++) {
                const [nextY, nextX] = [y + dirY[d], x + dirX[d]];
                if (nextY >= N || nextY < 0 || nextX >= N || nextX < 0) continue;
                if (dp[0][nextY][nextX] > min + 1) {
                    dp[0][nextY][nextX] = min + 1;
                    pq.push([dp[0][nextY][nextX], 0, nextY, nextX]);
                }
            }
            if (dp[1][y][x] > min + t) {
                dp[1][y][x] = min + t;
                pq.push([dp[1][y][x], 1, y, x]);
            }
        } else {
            if (dp[0][y][x] > min) {
                dp[0][y][x] = min;
                pq.push([dp[0][y][x], 0, y, x]);
            }
            for (let d = 0; d < 4; d++) {
                if (warps[d][y][x] === -1) continue;
                const nextY = d < 2 ? warps[d][y][x] : y;
                const nextX = d > 1 ? warps[d][y][x] : x;
                if (nextY >= N || nextY < 0 || nextX >= N || nextX < 0) continue;
                if (dp[1][nextY][nextX] > min + 1) {
                    dp[1][nextY][nextX] = min + 1;
                    pq.push([dp[1][nextY][nextX], 1, nextY, nextX]);
                }
            }
        }
    }
}

const solution = () => {
    const [N, t, r, c] = input[0].split(" ").map(Number);
    const map = new Array(N);
    const dp = Array.from({ length: 2 }, () => Array.from({ length: N }, () => new Array(N).fill(INF)));
    for (let i = 1; i <= N; i++) {
        map[i - 1] = input[i].split("");
    }

    const warps = Array.from({ length: 4 }, () => Array.from({ length: N }, () => new Array(N).fill(-1)));
    for (let x = 0; x < N; x++) {
        let lastY = -1;
        for (let y = 0; y < N; y++) {
            warps[0][y][x] = lastY;
            if (map[y][x] === '#') lastY = y;
        }
        lastY = -1;
        for (let y = N - 1; y >= 0; y--) {
            warps[1][y][x] = lastY;
            if (map[y][x] === '#') lastY = y;
        }
    }
    for (let y = 0; y < N; y++) {
        let lastX = -1;
        for (let x = 0; x < N; x++) {
            warps[2][y][x] = lastX;
            if (map[y][x] === '#') lastX = x;
        }
        lastX = -1;
        for (let x = N - 1; x >= 0; x--) {
            warps[3][y][x] = lastX;
            if (map[y][x] === '#') lastX = x;
        }
    }

    dijkstra(N, warps, dp, t);
    console.log(Math.min(dp[0][r - 1][c - 1], dp[1][r - 1][c - 1]));
};

solution();
