const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = 1e18;

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

function dikstra(n, adjList, from, to) {
    const distance = new Array(n + 1).fill(INF);
    const pq = new PriorityQueue();
    distance[from] = 0;
    pq.push([0, from]); // 시작 도시는 거리 0 [거리, 노드]
    while (pq.size() > 0) {
        const [cost, current] = pq.pop();
        if (cost > distance[current]) continue;
        if (current === to) return cost;

        for (const arr of adjList[current] ?? []) {
            if (distance[arr.next] > cost + arr.length) {
                distance[arr.next] = cost + arr.length;
                pq.push([distance[arr.next], arr.next]);
            }
        }

    }
    return distance[to];
}

const solution = () => {
    const [n, m, k] = input[0].split(" ").map(Number);
    const adjList = {};
    for (let i = 1; i <= m; i++) {
        const [u, v, l] = input[i].split(" ").map(Number);
        if (!adjList[u]) adjList[u] = [];
        if (!adjList[v]) adjList[v] = [];
        const same = adjList[u].find((arr) => arr.next === v);
        if (same) {
            same.length = Math.min(same.length, l);
        } else {
            adjList[u].push({ next: v, length: l });
            adjList[v].push({ next: u, length: l });
        }
    }
    const destinations = [];
    for (let i = 1; i <= k; i++) {
        destinations.push(input[m + i].split(" ").map(Number));
    }
    const deliverCost = new Array(k).fill(INF);
    for (let i = 0; i < k; i++) {
        const [f, d] = destinations[i];
        deliverCost[i] = dikstra(n, adjList, f, d);
    }
    const moveCost = Array.from({ length: k }, () => new Array(k).fill(INF));
    for (let i = 0; i < k; i++) {
        const d = destinations[i][1];
        for (let j = 0; j < k; j++) {
            const f = destinations[j][0];
            moveCost[i][j] = dikstra(n, adjList, d, f);
        }
    }
    // console.log(deliverCost);
    // console.log(moveCost);

    const dp = Array.from({ length: (1 << k) }, () => new Array(k).fill(INF));
    for (let i = 0; i < k; i++) {
        dp[(1 << i)][i] = deliverCost[i];
    }

    for (let d = 0; d < (1 << k); d++) {
        for (let i = 0; i < k; i++) {
            if ((d & (1 << i)) === 0) continue;
            if (dp[d][i] === INF) continue;
            for (let j = 0; j < k; j++) {
                if ((d & (1 << j)) !== 0) continue;
                dp[(d | (1 << j))][j] = Math.min(dp[(d | (1 << j))][j], dp[d][i] + moveCost[i][j] + deliverCost[j]);
            }
        }
    }

    let result = INF;
    for (const num of dp[(1 << k) - 1]) {
        result = Math.min(result, num);
    }
    // console.log(adjList);
    // console.log(destinations);
    console.log(result === INF ? -1 : result);


};

solution();
