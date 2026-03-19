const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
/**
 *  설명 : 이거는 언뜻보면 dp이면서 처음에는 한번에 가는 경우를 다 넣어놓고 그다음은 플로이드 워샬로 o(N^(3)으로 어디를 들렸다 가는 경우를 모두 계산
 * 하면 모든 경우의 수를 구할 수 있다.
 */
function isFriends(list, i, j) {
    const [s1, e1] = list[i];
    const [s2, e2] = list[j];
    if ((e1 >= s2 && e1 <= e2) || (e2 >= s1 && e2 <= e1)) return true;
    return false;
}

const solution = () => {
    let index = 0;
    const N = Number(input[index++]);
    const list = new Array(N);
    for (let i = 0; i < N; i++) {
        list[i] = input[index++].split(" ").map(Number);
    }

    const adjMatrix = Array.from({ length: N }, () => new Array(N).fill([INF, INF, INF]));
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (i === j) {
                adjMatrix[i][j] = [list[i][0], list[i][1], 0];
            } else {
                if (isFriends(list, i, j)) {
                    const minS = Math.min(list[i][0], list[j][0]);
                    const maxE = Math.max(list[i][1], list[j][1]);
                    adjMatrix[i][j] = [minS, maxE, 1];
                }
            }
        }
    }

    for (let k = 0; k < N; k++) {
        for (let i = 0; i < N; i++) {
            if (k === i) continue;
            for (let j = 0; j < N; j++) {
                if (j === k || j === i) continue;
                if (adjMatrix[i][k][2] === INF || adjMatrix[k][j][2] === INF) continue;
                if (adjMatrix[i][j][2] > adjMatrix[i][k][2] + adjMatrix[k][j][2]) {
                    const minS = Math.min(adjMatrix[i][0], adjMatrix[j][0]);
                    const maxE = Math.max(adjMatrix[i][1], adjMatrix[j][1]);
                    const length = Math.min(adjMatrix[i][j][2], adjMatrix[i][k][2] + adjMatrix[k][j][2]);
                    adjMatrix[i][j] = [minS, maxE, length];
                };
            }
        }
    }

    const Q = Number(input[index++]);
    for (let i = 0; i < Q; i++) {
        const [A, B] = input[index++].split(" ").map(Number);
        console.log(adjMatrix[A - 1][B - 1][2] === INF ? -1 : adjMatrix[A - 1][B - 1][2]);
    }
};
solution();
