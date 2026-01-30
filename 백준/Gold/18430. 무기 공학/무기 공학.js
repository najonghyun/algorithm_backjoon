const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [-1, 1, 1, -1];
const dirX = [1, 1, -1, -1];
let result;
function isRange(visited, n, m, y, x) {
    if (y >= n || y < 0 || x >= m || x < 0 || visited[y][x]) return false;
    return true;
}
function dfs(yxpos, map, n, m, visited, score) {
    if (yxpos >= n * m) {
        return;
    }
    const nowY = Math.floor(yxpos / m);
    const nowX = yxpos % m;
    if (!visited[nowY][nowX]) {
        for (let d = 0; d < 4; d++) {
            const nextY = nowY + dirY[d];
            const nextX = nowX + dirX[d];
            if (!isRange(visited, n, m, nextY, nowX) || !isRange(visited, n, m, nowY, nextX)) {
                continue;
            }
            const nextScore = score + (map[nextY][nowX] + map[nowY][nowX] * 2 + map[nowY][nextX]);
            result = Math.max(result, nextScore);
            visited[nextY][nowX] = true;
            visited[nowY][nowX] = true;
            visited[nowY][nextX] = true;
            dfs(yxpos + 1, map, n, m, visited, nextScore);
            visited[nextY][nowX] = false;
            visited[nowY][nowX] = false;
            visited[nowY][nextX] = false;
        }
    }
    dfs(yxpos + 1, map, n, m, visited, score);
}

const solution = () => {
    const [n, m] = input[0].split(" ").map(Number);
    const map = new Array(n);
    for (let i = 1; i <= n; i++) {
        map[i - 1] = input[i].split(" ").map(Number);
    }
    const visited = Array.from({ length: n }, () => new Array(m).fill(false));
    result = 0;
    dfs(0, map, n, m, visited, 0);
    console.log(result);
};

solution();
