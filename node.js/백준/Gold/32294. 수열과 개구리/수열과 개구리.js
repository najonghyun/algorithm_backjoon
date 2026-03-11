const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
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
function dikstra(adjList, pq, distance) {
    while (pq.size() > 0) {
        const [min, current] = pq.pop();
        // console.log(min, current);

        for (const [next, length] of adjList[current] ?? []) {
            if (distance[next] > min + length) {
                distance[next] = min + length;
                pq.push([distance[next], next]);
            }
        }
    }
}

const solution = () => {
    const n = Number(input[0]);
    const a = input[1].split(" ").map(Number);
    const b = input[2].split(" ").map(Number);

    const adjList = {};
    const distance = new Array(n + 1).fill(INF);
    const pq = new PriorityQueue();
    for (let i = 1; i <= n; i++) {
        if (i + a[i - 1] > n) {
            pq.push([b[i - 1], i]);
            distance[i] = b[i - 1];
        } else {
            if (!adjList[i + a[i - 1]]) adjList[i + a[i - 1]] = [];
            adjList[i + a[i - 1]].push([i, b[i - 1]]);
        }
        if (i - a[i - 1] < 1) {
            pq.push([b[i - 1], i]);
            distance[i] = b[i - 1];
        } else {
            if (!adjList[i - a[i - 1]]) adjList[i - a[i - 1]] = [];
            adjList[i - a[i - 1]].push([i, b[i - 1]]);
        }
    }
    // console.log(adjList);

    dikstra(adjList, pq, distance);
    console.log(distance.slice(1).join(" "));



};
solution();
