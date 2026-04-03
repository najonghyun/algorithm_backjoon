const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [-1, 1, 0, 0];
const dirX = [0, 0, 1, -1];
const INF = Number.MAX_VALUE;
let result;
/**
 * 설명 : N의 길이 그리고 선생님의 수가 매우 짧기도 하고 예외가 많으므로 재귀로 완전탐색을 해야 한다. 
 * 몫과 나머지를 이용한 dfs 위치 잘 기억하자. 그리고 쭉가봐서 학생이 있을때 이제 x로 된곳이 후보이며 모두 방문해본다.
 */
function dfs(N, size, map, teachers, visited, now, cnt) {
    if (now === size * 4) {
        result = Math.min(result, cnt);
        return;
    }

    for (let i = now; i < size * 4; i++) {
        const t = Math.floor(i / 4);
        const d = i % 4;
        const [nowY, nowX] = teachers[t];
        let nextY = nowY + dirY[d];
        let nextX = nowX + dirX[d];
        const points = [];
        while (nextY < N && nextY >= 0 && nextX < N && nextX >= 0) {
            if (visited[nextY][nextX]) break;
            if (map[nextY][nextX] === "S") {
                for (const [y, x] of points) {
                    visited[y][x] = true;
                    dfs(N, size, map, teachers, visited, i + 1, cnt + 1);
                    visited[y][x] = false;
                }
                return;
            }
            points.push([nextY, nextX]);
            nextY += dirY[d];
            nextX += dirX[d];
        }
    }
    dfs(N, size, map, teachers, visited, size * 4, cnt);
}
const solution = () => {
    const N = Number(input[0]);
    const map = Array.from({ length: N }, () => new Array(N));
    const teachers = [];
    for (let i = 0; i < N; i++) {
        const temp = input[i + 1].split(" ");
        for (let j = 0; j < N; j++) {
            map[i][j] = temp[j];
            if (temp[j] === "T") {
                teachers.push([i, j]);
            }
        }
    }
    const size = teachers.length;
    const visited = Array.from({ length: N }, () => new Array(N).fill(false));
    result = INF;
    dfs(N, size, map, teachers, visited, 0, 0);
    console.log(result > 3 ? "NO" : "YES");
};

solution();
