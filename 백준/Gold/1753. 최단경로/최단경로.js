const fs = require('fs');
const filepath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filepath).toString().trim().split(/\r?\n/);
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
      let p = Math.floor((i - 1) / 2);
      if (this.heap[i][0] >= this.heap[p][0]) break;
      [this.heap[i], this.heap[p]] = [this.heap[p], this.heap[i]];
      i = p;
    }
  }
  _down() {
    let i = 0;
    let n = this.size();
    while (true) {
      let left = i * 2 + 1;
      let right = i * 2 + 2;
      let min = i;
      if (left < n && this.heap[left][0] < this.heap[min][0]) min = left;
      if (right < n && this.heap[right][0] < this.heap[min][0]) min = right;
      if (i === min) break;
      [this.heap[i], this.heap[min]] = [this.heap[min], this.heap[i]];
      i = min;
    }
  }
}

function dijkstra(V, adjList, start) {
  const distance = new Array(V + 1).fill(INF);
  const pq = new PriorityQueue();
  distance[start] = 0;
  pq.push([distance[start], start]);
  while (pq.size() > 0) {
    const [min, current] = pq.pop();
    if (min > distance[current]) continue; // 중복 제거 (방문체크 대신)
    for (const { next, length } of adjList[current] ?? []) {
      if (distance[next] > min + length) {
        distance[next] = min + length;
        pq.push([distance[next], next]);
      }
    }
  }
  return distance;
}
const solution = () => {
  const [V, E] = input[0].split(' ').map(Number);
  const [K] = input[1].split(' ').map(Number);
  const adjList = {};
  for (let i = 0; i < E; i++) {
    const [u, v, w] = input[i + 2].split(' ').map(Number);
    if (!adjList[u]) adjList[u] = [];
    adjList[u].push({ next: v, length: w });
  }
  const distance = dijkstra(V, adjList, K);
  for (let i = 1; i <= V; i++) {
    console.log(distance[i] === INF ? 'INF' : distance[i]);
  }
};
solution();
