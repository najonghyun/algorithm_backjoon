const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const shapeType = [
    [
        [-1, 0],
        [-1, -1],
    ],
    [
        [0, -1],
        [-1, -1],
    ],
    [
        [-1, 0],
        [-1, 1],
    ],
    [
        [0, 1],
        [-1, 1],
    ],
    [
        [0, -1],
        [1, -1],
    ],
    [
        [1, 0],
        [1, -1],
    ],
    [
        [0, 1],
        [1, 1],
    ],
    [
        [1, 0],
        [1, 1],
    ],
];
const dirY = [0, -1, 0, 1];
const dirX = [1, 0, -1, 0];
let result;
let count;

function isRange(visited, n, m, y, x) {
    if (y >= n || y < 0 || x >= m || x < 0 || visited[y][x]) return false;
    return true;
}

function dfs(map, n, m, y, x, visited, score) {
    count++;
    for (let t = 0; t < 8; t++) {
        const [part1, part2] = shapeType[t];
        // console.log(part1, part2);
        const nextY1 = y + part1[0];
        const nextX1 = x + part1[1];
        const nextY2 = y + part2[0];
        const nextX2 = x + part2[1];
        if (!isRange(visited, n, m, nextY1, nextX1) || !isRange(visited, n, m, nextY2, nextX2)) {
            continue;
        }
        const nextScore = score + (map[y][x] + map[nextY1][nextX1] * 2 + map[nextY2][nextX2]);
        result = Math.max(result, nextScore);
        for (let g = 0; g < 2; g++) {
            for (let d = 0; d < 4; d++) {
                const nextY = (g === 0 ? nextY1 : nextY2) + dirY[d];
                const nextX = (g === 0 ? nextX1 : nextX2) + dirX[d];
                if (!isRange(visited, n, m, nextY, nextX)) {
                    continue;
                }
                if (g === 0) {
                    if ((nextY === nextY2 && nextX === nextX2) || (nextY === y && nextX === x)) {
                        continue;
                    }
                } else {
                    if ((nextY === nextY1 && nextX === nextX1) || (nextY === y && nextX === x)) {
                        continue;
                    }
                }
                visited[y][x] = true;
                visited[nextY1][nextX1] = true;
                visited[nextY2][nextX2] = true;
                // if (nextScore === 507) {
                //     console.log(visited);
                // }
                dfs(map, n, m, nextY, nextX, visited, nextScore);
                visited[y][x] = false;
                visited[nextY1][nextX1] = false;
                visited[nextY2][nextX2] = false;
            }
        }
    }
}

const solution = () => {
    const [n, m] = input[0].split(" ").map(Number);
    const map = new Array(n);
    for (let i = 1; i <= n; i++) {
        map[i - 1] = input[i].split(" ").map(Number);
    }
    const visited = Array.from({ length: n }, () => new Array(m).fill(false));
    result = 0;
    count = 0;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            dfs(map, n, m, i, j, visited, 0);
        }
    }
    // console.log(count);
    // console.log(visited);
    console.log(result);
};

solution();
