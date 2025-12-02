const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

class Node {
  constructor() {
    this.children = {};
    this.size = 0;
  }
  add(child) {
    this.children[this.size++] = child;
  }
}
const bfs = (node, parent) => {
  const q = [1];
  const visited = {};
  visited[1] = true;
  while (q.length) {
    const now = q.shift();
    for (let i = 0; i < node[now].size; i++) {
      const next = node[now].children[i];
      if (!visited[next]) {
        parent[next] = now;
        q.push(next);
        visited[next] = true;
      }
    }
  }
};
const solution = () => {
  const n = Number(input[0]);
  const node = Array.from({ length: n + 1 }, () => new Node());
  for (let i = 1; i < n; i++) {
    const arr = input[i].split(' ').map(Number);
    node[arr[0]].add(arr[1]);
    node[arr[1]].add(arr[0]);
  }
  const parent = Array(n + 1).fill(0);
  bfs(node, parent);
  console.log(parent.slice(2).join('\n'));
};

solution();
