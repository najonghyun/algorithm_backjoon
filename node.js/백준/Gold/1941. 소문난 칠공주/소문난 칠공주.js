const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
const dirY = [0, 1, 0, -1];
const dirX = [1, 0, -1, 0];
let result;

function include(arr, targetY, targetX) {
    for (let i = 0; i < arr.length; i++) {
        const [y, x] = arr[i];
        if (y === targetY && x === targetX) {
            return true;
        }
    }
    return false;
}

function bfs(arr) {
    const q = [];
    const visited = Array.from({ length: 5 }, () => new Array(5).fill(false));
    const [y, x] = arr[0];
    q.push({ y: y, x: x });
    visited[y][x] = true;
    let count = 1;
    while (q.length > 0) {
        const { y: nowY, x: nowX } = q.shift();
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (nextY >= 5 || nextY < 0 || nextX >= 5 || nextX < 0 || visited[nextY][nextX] || !include(arr, nextY, nextX)) {
                continue;
            }
            q.push({ y: nextY, x: nextX });
            visited[nextY][nextX] = true;
            count++;
        }
    }
    if (count === 7) {
        result++;
    }
}

function comb(size, arr, points, cnt, start, yCount) {
    if (yCount > 3) { // 가지치기
        return;
    }
    if (cnt === 7) {
        bfs(arr);
        return;
    }

    for (let i = start; i < size; i++) {
        arr[cnt] = points[i].slice(0, 2);
        const value = points[i][2];
        const nextYCount = value === 'Y' ? yCount + 1 : yCount;
        comb(size, arr, points, cnt + 1, i + 1, nextYCount);
    }
}
const solution = () => {
    const map = new Array(5);
    const points = [];
    for (let i = 0; i < 5; i++) {
        map[i] = input[i].split("");
        map[i].forEach((v, j) => {
            points.push([i, j, v]);
        });
    }
    result = 0;
    const size = points.length;
    const arr = new Array(7);
    comb(size, arr, points, 0, 0, 0);

    console.log(result);
};

solution();