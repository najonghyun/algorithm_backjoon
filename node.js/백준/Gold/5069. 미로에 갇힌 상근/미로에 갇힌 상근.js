const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [1, 0, -1, -1, 0, 1];
const dirX = [0, 1, 1, 0, -1, -1];
/**
 * 일단 완탐은 무조건 시간초과다. 그래서 처음에는 점화식을 생각했지만 규칙도 따로 없다. 그래서 완탐에서 겹치는거는 메모시켜놓는
 * dp를 사용했다. 갈수있는좌표를 6방향으로 나누고 마이너스까지 고려해서 배열의 size를 30+1로 잡았다. 그리고 그 가운데인 15부터
 * 시작했고 각 count에 대해 (y, x) 좌표를 저장을 해야하으로 3차원 dp를 사용했다.
 */
const solution = () => {
    const T = Number(input[0]);
    const size = 30;
    const start = size / 2;
    const dp = Array.from({ length: start }, () => Array.from({ length: size + 1 }, () => new Array(size + 1).fill(0)));
    dp[0][start][start] = 1;
    for (let k = 0; k < start - 1; k++) {
        for (let i = 0; i <= size; i++) {
            for (let j = 0; j <= size; j++) {
                for (let d = 0; d < 6; d++) {
                    const nextY = i + dirY[d];
                    const nextX = j + dirX[d];
                    if (nextY > size || nextY < 0 || nextX > size || nextX < 0) continue;
                    dp[k + 1][nextY][nextX] += dp[k][i][j];
                }
            }
        }
    }

    for (let t = 1; t <= T; t++) {
        const n = Number(input[t]);
        console.log(dp[n][15][15]);
    }
};

solution();
