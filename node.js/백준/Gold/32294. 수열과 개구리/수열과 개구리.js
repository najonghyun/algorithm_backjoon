const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
/**
 * 설명 : 이 문제를 보면 a 배열의 각 인덱스가 하나의 node 역할을 한다.
 * 각 위치 i에서 i ± a[i]로 이동할 수 있으므로 이를 그래프의 간선으로 볼 수 있다.
 * 
 * 이때 어떤 위치에서 한 번 이동했을 때 수열 바깥으로 나가면 그 시간은 b[i]로 바로 구해진다.
 * 그래서 이런 노드들을 outside로 바로 나갈 수 있는 시작점으로 볼 수 있다.
 * 우리는 x → outside 최소 시간을 구해야 하므로 간선 방향을 뒤집어 그래프를 만든다.
 * 그리고 outside로 바로 나갈 수 있는 노드들을 모두 pq에 넣고 다익스트라를 한 번 돌리면,
 * distance 배열에 모든 노드의 최소 탈출 시간이 구해진다.그럼 o(nlogn)이라 시간초과도 해결된다.
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

    dikstra(adjList, pq, distance);
    console.log(distance.slice(1).join(" "));
};
solution();
