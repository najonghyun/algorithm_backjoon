const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = 10000001;

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
            let p = Math.floor((i - 1) / 2);
            if (this.heap[p][0] <= this.heap[i][0]) break;
            [this.heap[p], this.heap[i]] = [this.heap[i], this.heap[p]];
            i = p;
        }
    }
    _down() {
        let i = 0;
        const n = this.size();
        while (true) {
            let left = i * 2 + 1;
            let right = i * 2 + 2;
            let min = i;
            if (left < n && this.heap[min][0] > this.heap[left][0]) min = left;
            if (right < n && this.heap[min][0] > this.heap[right][0]) min = right;
            if (min === i) break;
            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
}

function dikstra(N, adjList, C, start, end) {
    const distance = Array.from({ length: N + 1 }, () => ({ maxCost: INF, minLength: INF }));
    const visited = new Array(N + 1).fill(false);
    const pq = new PriorityQueue();
    distance[start].maxCost = 0;
    distance[start].minLength = 0;
    pq.push([0, 0, start]);

    while (pq.size() > 0) {
        const [maxCost, min, current] = pq.pop();

        visited[current] = true;
        for (const [next, money] of adjList[current] ?? []) {
            // console.log(next, money);
            if (!visited[next] && C >= min + money) {
                distance[next].minLength = min + money;
                if (distance[next].maxCost > money) {
                    distance[next].maxCost = Math.max(maxCost, money);
                    pq.push([distance[next].maxCost, distance[next].minLength, next]);
                }
            }
        }
    }

    // console.log(distance)
    return distance[end].maxCost === INF ? -1 : distance[end].maxCost;
}

const solution = () => {
    const [N, M, A, B, C] = input[0].split(" ").map(Number);
    const adjList = {};
    for (let i = 1; i <= M; i++) {
        const [s, e, money] = input[i].split(" ").map(Number);
        if (!adjList[s]) adjList[s] = [];
        if (!adjList[e]) adjList[e] = [];
        adjList[s].push([e, money]);
        adjList[e].push([s, money]);
    }
    const result = dikstra(N, adjList, C, A, B);

    // console.log(adjList);
    console.log(result)
};
solution();