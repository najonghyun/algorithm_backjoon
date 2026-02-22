const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = 100000000;
const dirX = [1, -1];

function bfs(n, k, map) {
    const q = [];
    const visited = {};
    for (const v of map) {
        q.push([v, 0]);
        visited[v] = true;
    }
    let sum = 0;
    let count = 0;
    let head = 0;
    while (head < q.length) {
        const [now, unLucky] = q[head++];
        if (unLucky > 0) {
            sum += unLucky;
            count++;
            if (count === k) {
                break;
            }
        }
        for (let d = 0; d < 2; d++) {
            const next = now + dirX[d];
            if (visited[next]) continue;
            q.push([next, unLucky + 1]);
            visited[next] = true;
        }
    }
    return sum;
}

const solution = () => {
    const [n, k] = input[0].split(" ").map(Number);
    const map = input[1].split(" ").map(Number);
    const result = bfs(n, k, map);
    console.log(result);
};
solution();
