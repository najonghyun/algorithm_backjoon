const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [-1, 0, 1, 0];
const dirX = [0, 1, 0, -1]; // 0:북 1:동 2:남 3:서
let count;

function isRange(n, m, y, x) {
    if (y >= n || y < 0 || x >= m || x < 0) {
        return false;
    }
    return true;
}

function dfs(n, m, room, isCleaned, r, c, d) {
    if (!isCleaned[r][c]) {
        isCleaned[r][c] = true;
        count++;
    }

    let isMove = false;
    for (let i = 1; i <= 4; i++) {
        const nextD = (d - i + 4) % 4;
        const nextR = r + dirY[nextD];
        const nextC = c + dirX[nextD];
        if (!isRange(n, m, nextR, nextC)) continue;
        if (isCleaned[nextR][nextC] || room[nextR][nextC] === 1) continue;
        dfs(n, m, room, isCleaned, nextR, nextC, nextD);
        isMove = true;
        break;
    }
    if (!isMove) {
        const backD = (d + 2) % 4;
        const nextR = r + dirY[backD];
        const nextC = c + dirX[backD];
        if (isRange(n, m, nextR, nextC) && room[nextR][nextC] !== 1) {
            dfs(n, m, room, isCleaned, nextR, nextC, d);
        }

    }
}

const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const [r, c, d] = input[1].split(" ").map(Number);
    const room = new Array(N);
    for (let i = 0; i < N; i++) {
        room[i] = input[i + 2].split(" ").map(Number);
    }
    // console.log(room.join("\n"));

    const isCleaned = Array.from({ length: N }, () => new Array(M).fill(false));
    count = 0;
    dfs(N, M, room, isCleaned, r, c, d);
    console.log(count);
};
solution();