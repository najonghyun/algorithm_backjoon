const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [-1, 0, 1, 0];
const dirX = [0, 1, 0, -1]; // 0:북 1:동 2:남 3:서

function isRange(n, m, y, x) {
    if (y >= n || y < 0 || x >= m || x < 0) {
        return false;
    }
    return true;
}

function bfs(n, m, room, r, c, d) {
    let count = 1;
    const q = [];
    const visited = Array.from({ length: n }, () => new Array(m).fill(false));
    q.push([r, c, d]);
    visited[r][c] = true;
    while (q.length > 0) {
        const [nowY, nowX, nowD] = q.shift();
        let isMove = false;
        for (let d = 1; d <= 4; d++) {
            const nextD = (nowD - d + 4) % 4;
            // console.log(tempD)
            const nextY = nowY + dirY[nextD];
            const nextX = nowX + dirX[nextD];
            if (!isRange(n, m, nextY, nextX)) continue;
            if (visited[nextY][nextX] || room[nextY][nextX] === 1) continue;
            q.push([nextY, nextX, nextD]);
            visited[nextY][nextX] = true;
            count++;
            isMove = true;
            break;
        }
        if (!isMove) {
            const backD = (nowD + 2) % 4;
            const nextY = nowY + dirY[backD];
            const nextX = nowX + dirX[backD];
            if (isRange(n, m, nextY, nextX) && room[nextY][nextX] !== 1) {
                q.push([nextY, nextX, nowD]);
            }

        }
    }
    // console.log(visited.join("\n"));
    return count;
}

const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const [r, c, d] = input[1].split(" ").map(Number);
    const room = new Array(N);
    for (let i = 0; i < N; i++) {
        room[i] = input[i + 2].split(" ").map(Number);
    }
    // console.log(room.join("\n"));

    const result = bfs(N, M, room, r, c, d);
    console.log(result);
};
solution();