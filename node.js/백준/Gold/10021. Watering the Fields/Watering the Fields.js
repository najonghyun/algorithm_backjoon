const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
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
        let n = this.size();
        let i = 0;
        while (true) {
            let min = i;
            const left = i * 2 + 1;
            const right = i * 2 + 2;
            if (left < n && this.heap[left][0] < this.heap[min][0]) min = left;
            if (right < n && this.heap[right][0] < this.heap[min][0]) min = right;
            if (min === i) break;
            [this.heap[i], this.heap[min]] = [this.heap[min], this.heap[i]];
            i = min;
        }
    }
}

function prim(N, C, fields, pq, start) {
    const visited = new Array(N).fill(false);
    pq.push([0, start]);
    let sum = 0;
    let count = 0;
    while (pq.size() > 0) {
        const [cost, current] = pq.pop();
        if (visited[current]) continue;
        visited[current] = true;
        sum += cost;
        count++;
        for (let next = 0; next < N; next++) {
            if (visited[next]) continue;
            const nextCost = euclidean(fields[current], fields[next]);
            if (nextCost >= C) {
                pq.push([nextCost, next]);
            }
        }
    }
    return count === N ? sum : -1;
}

function euclidean(arr1, arr2) {
    const diffX = Math.abs(arr1[0] - arr2[0]);
    const diffY = Math.abs(arr1[1] - arr2[1]);
    return diffX * diffX + diffY * diffY;
}

const solution = () => {
    const [N, C] = input[0].split(" ").map(Number);
    const fields = new Array(N);
    for (let i = 1; i <= N; i++) {
        const [x, y] = input[i].split(" ").map(Number);
        fields[i - 1] = [x, y];
    }
    // console.log(fields);

    const pq = new PriorityQueue();
    const result = prim(N, C, fields, pq, 0);
    console.log(result);
};

solution();
