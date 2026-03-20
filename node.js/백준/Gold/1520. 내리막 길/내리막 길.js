const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [0, 1, 0, -1];
const dirX = [1, 0, -1, 0];
/**
 *  경로 개수를 dp에 저장하면서 가는 문제이다. 이때 메모를 해서 간곳은 더 가지 않고 안간곳만 가면서 마지막에는 1을
 * 반환하여 그값들을 재귀로 더해주는 방식이다. 그래서 계산 후에는 dp[0][0]에 모든 경우의 수가 들어있다.
 */
function dfs(M, N, map, dp, y, x) {
    if (y === M - 1 && x === N - 1) return 1;
    if (dp[y][x] !== -1) return dp[y][x];

    dp[y][x] = 0;
    for (let d = 0; d < 4; d++) {
        const nextY = y + dirY[d];
        const nextX = x + dirX[d];
        if (nextY >= M || nextY < 0 || nextX >= N || nextX < 0) continue;
        if (map[nextY][nextX] >= map[y][x]) continue;
        dp[y][x] += dfs(M, N, map, dp, nextY, nextX);
    }

    return dp[y][x];
}
const solution = () => {
    const [M, N] = input[0].split(" ").map(Number);
    const map = new Array(M);
    for (let i = 1; i <= M; i++) {
        map[i - 1] = input[i].split(" ").map(Number);
    }

    const dp = Array.from({ length: M }, () => new Array(N).fill(-1));
    dfs(M, N, map, dp, 0, 0);

    console.log(dp[0][0]);

};
solution();
