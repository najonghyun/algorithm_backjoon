const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = 5e5;
/**
 * 설명 : 이거는 경로를 다 저장해야 하는데 중복 없는 경로 저장은 경로를 한번에 저장할 수 있지만 여러개 있는 경우는 prev를 배열로 저장해서 이제 큐로
 * 경로를 하나씩 빼는 식으로 풀어야 한다.
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

function dijkstra(N, adjList, start) {
    const distance = Array.from({ length: N }, () => ({ cost: INF, prev: [] }));
    const pq = new PriorityQueue();
    distance[start].cost = 0;
    pq.push([distance[start].cost, start]);
    while (pq.size() > 0) {
        const [currCost, current] = pq.pop();
        if (currCost !== distance[current].cost) continue;
        for (const { next, length } of adjList[current] ?? []) {
            if (distance[next].cost > currCost + length) {
                distance[next].cost = currCost + length;
                distance[next].prev = [current];
                pq.push([distance[next].cost, next]);
            } else if (distance[next].cost === currCost + length) {
                distance[next].prev.push(current);
            }
        }
    }
    return distance;
}

const solution = () => {
    const answer = [];
    let index = 0;
    while (true) {
        const [N, M] = input[index++].split(" ").map(Number);
        if (N === 0 && M === 0) break;
        const [S, D] = input[index++].split(" ").map(Number);
        const adjList = {};
        for (let i = index; i < index + M; i++) {
            const [u, v, p] = input[i].split(" ").map(Number);
            if (!adjList[u]) adjList[u] = [];
            adjList[u].push({ next: v, length: p });
        }
        index = index + M;

        const distance = dijkstra(N, adjList, S);
        const q = [];
        for (const i of distance[D].prev) {
            q.push([i, D]);
        }
        const visited = {};
        while (q.length > 0) {
            const [start, end] = q.shift();
            adjList[start] = adjList[start].filter((v) => v.next !== end);
            for (const i of distance[start].prev) {
                const key = String(i) + " " + String(start);
                if (visited[key]) continue;
                q.push([i, start]);
                visited[key] = true;
            }
        }
        const resultDistance = dijkstra(N, adjList, S);
        answer.push(resultDistance[D].cost === INF ? -1 : resultDistance[D].cost);
    }
    console.log(answer.join("\n"));
};
solution();
