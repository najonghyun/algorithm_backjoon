const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const dirY = [0, 1, 0, -1];
const dirX = [1, 0, -1, 0];

function bfs(map, n, m, startY, startX) {
    const q = [{ y: startY, x: startX, count: 0 }];
    const visited = {};
    visited[startY + " " + startX] = true;
    while (q.length > 0) {
        const { y: nowY, x: nowX, count: nowCount } = q.shift();
        map[nowY][nowX] = nowCount;
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (nextY >= n || nextY < 0 || nextX >= m || nextX < 0
                || visited[nextY + " " + nextX] || map[nextY][nextX] === 0) {
                continue;
            }
            q.push({ y: nextY, x: nextX, count: nowCount + 1 });
            visited[nextY + " " + nextX] = true;
        }
    }
}

const solution = () => {
    const n = Number(input[0].split(" ")[0]);
    const m = Number(input[0].split(" ")[1]);
    const map = Array.from({ length: n }, () => new Array(m));
    let startY = 0;
    let startX = 0;
    for (let i = 0; i < n; i++) {
        const row = input[i + 1].split(" ");
        for (let j = 0; j < m; j++) {
            const num = Number(row[j]);
            if (num === 1) {
                map[i][j] = -1;
            } else {
                map[i][j] = num;
            }
            if (num === 2) {
                startY = i;
                startX = j;
            }
        }
    }

    bfs(map, n, m, startY, startX);

    map.forEach(row => {
        console.log(row.join(" "));
    })
}
solution();