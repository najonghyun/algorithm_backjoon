const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
let result;

function dfs(N, H, adjList, start, nowY, nowX, count) {
    if (count > 3) {
        // console.log("카운트 초과");
        return;
    }
    for (let i = start; i <= N; i++) {
        for (let j = nowY; j <= H; j++) {
            if (j === 0) {
                nowX = i;
                continue;
            }
            if (adjList[0][j][nowX]) {
                nowX++;
            } else if (adjList[1][j][nowX]) {
                nowX--;
            } else {
                if (nowX + 1 <= N && !adjList[0][j][nowX + 1] && !adjList[1][j][nowX + 1]) {
                    adjList[0][j][nowX] = true;
                    adjList[1][j][nowX + 1] = true;
                    dfs(N, H, adjList, i, j + 1, nowX + 1, count + 1);
                    adjList[0][j][nowX] = false;
                    adjList[1][j][nowX + 1] = false;
                }
                if (nowX - 1 > 0 && !adjList[0][j][nowX - 1] && !adjList[1][j][nowX - 1]) {
                    adjList[0][j][nowX - 1] = true;
                    adjList[1][j][nowX] = true;
                    dfs(N, H, adjList, i, j + 1, nowX - 1, count + 1);
                    adjList[0][j][nowX - 1] = false;
                    adjList[1][j][nowX] = false;
                }
            }
        }
        nowY = 0;
        if (nowX !== i) return;
        // console.log(i, "일치");
    }
    result = Math.min(result, count);
}

const solution = () => {
    const [N, M, H] = input[0].split(" ").map(Number);
    const adjList = Array.from({ length: 2 }, () => Array.from({ length: H + 1 }, () => new Array(N + 1).fill(false)));
    for (let i = 1; i <= M; i++) {
        const [a, b] = input[i].split(" ").map(Number);
        adjList[0][a][b] = true;
        adjList[1][a][b + 1] = true;
    }

    // console.log(adjList[0].join("\n"));
    // console.log();
    // console.log(adjList[1].join("\n"));
    // console.log();

    result = INF;
    dfs(N, H, adjList, 1, 0, 0, 0);
    console.log(result === INF ? -1 : result);


};
solution();
