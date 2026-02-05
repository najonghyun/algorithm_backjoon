const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = 5e7;
/**
 * 설명 : 다익스트라를 사용했고, 건우를 들렸다 가는거랑 바로 가는거랑 최소거리 비교하면 된다.
 */
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
            if (this.heap[p][0] < this.heap[i][0]) break;
            [this.heap[i], this.heap[p]] = [this.heap[p], this.heap[i]];
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
            if (left < n && this.heap[left][0] < this.heap[min][0]) min = left;
            if (right < n && this.heap[right][0] < this.heap[min][0]) min = right;
            if (i === min) break;
            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
}

function dijkstra(V, adjList, start, end) {
    const distance = new Array(V + 1).fill(INF);
    const pq = new PriorityQueue();
    distance[start] = 0;
    pq.push([distance[start], start]);
    while (pq.size() > 0) {
        const [cost, current] = pq.pop();
        if (current === end) break;

        for (const { next, length } of adjList[current]) {
            if (distance[next] > cost + length) {
                distance[next] = cost + length;
                pq.push([distance[next], next])
            }
        }
    }
    return distance[end];
}

const solution = () => {
    const [V, E, P] = input[0].split(" ").map(Number);
    const adjList = {};
    for (let i = 1; i <= E; i++) {
        const [a, b, c] = input[i].split(" ").map(Number);
        if (!adjList[a]) adjList[a] = [];
        if (!adjList[b]) adjList[b] = [];
        adjList[a].push({ next: b, length: c });
        adjList[b].push({ next: a, length: c });
    }
    const excludeG = dijkstra(V, adjList, 1, V);
    const includeG = dijkstra(V, adjList, 1, P) + dijkstra(V, adjList, P, V);

    console.log(excludeG === includeG ? "SAVE HIM" : "GOOD BYE");

};
solution();