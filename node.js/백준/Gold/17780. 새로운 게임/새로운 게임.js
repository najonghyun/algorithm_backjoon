const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dirY = [0, 0, -1, 1];
const dirX = [1, -1, 0, 0];
/**
 * 설명 : 이거는 시뮬레이션이다. bfs이런거 쓰면 안된다 순서가 중요해서 그냥 for문으로 돌리고 합쳐진다면 
 * 이동할 떼 그 좌표들을 모두 이동시키는 것이 중요하다.
 */
function move(isReverse, positions, info, nowY, nowX, nextY, nextX) {
    if (isReverse) {
        for (const i of positions[nowY][nowX].reverse()) {
            info[i] = [nextY, nextX, info[i][2]];
            positions[nextY][nextX].push(i);
        }
    } else {
        for (const i of positions[nowY][nowX]) {
            info[i] = [nextY, nextX, info[i][2]];
            positions[nextY][nextX].push(i);
        }
    }
    positions[nowY][nowX] = [];
}

function isNotMove(N, map, y, x) {
    if (y >= N || y < 0 || x >= N || x < 0 || map[y][x] === 2) return true;
    return false;
}

function simulation(N, K, map, info, positions) {
    for (let turn = 1; turn <= 1000; turn++) {
        for (let i = 0; i < K; i++) {
            const [nowY, nowX, nowD] = info[i];
            if (positions[nowY][nowX][0] !== i) {
                continue;
            }
            const nextY = nowY + dirY[nowD];
            const nextX = nowX + dirX[nowD];
            if (isNotMove(N, map, nextY, nextX)) {
                const bNextD = nowD === 1 || nowD === 3 ? nowD - 1 : nowD + 1;
                const bNextY = nowY + dirY[bNextD];
                const bNextX = nowX + dirX[bNextD];
                if (isNotMove(N, map, bNextY, bNextX)) {
                    info[i] = [nowY, nowX, bNextD];
                } else if (map[bNextY][bNextX] === 1) {
                    info[i] = [bNextY, bNextX, bNextD];
                    move(true, positions, info, nowY, nowX, bNextY, bNextX);
                    if (positions[bNextY][bNextX].length >= 4) return turn;
                } else {
                    info[i] = [bNextY, bNextX, bNextD];
                    move(false, positions, info, nowY, nowX, bNextY, bNextX);
                    if (positions[bNextY][bNextX].length >= 4) return turn;
                }

            } else if (map[nextY][nextX] === 1) {
                info[i] = [nextY, nextX, nowD];
                move(true, positions, info, nowY, nowX, nextY, nextX);
                if (positions[nextY][nextX].length >= 4) return turn;
            } else {
                info[i] = [nextY, nextX, nowD];
                move(false, positions, info, nowY, nowX, nextY, nextX);
                if (positions[nextY][nextX].length >= 4) return turn;
            }
        }
    }

    return -1;
}

const solution = () => {
    let index = 0;
    const [N, K] = input[index++].split(" ").map(Number);
    const map = new Array(N);
    for (let i = 0; i < N; i++) {
        map[i] = input[index++].split(" ").map(Number);
    }
    const info = new Array(K);
    const positions = Array.from({ length: N }, () => Array.from({ length: N }, () => []));
    for (let i = 0; i < K; i++) {
        const [y, x, d] = input[index++].split(" ").map(v => Number(v - 1));
        info[i] = [y, x, d];
        positions[y][x].push(i);
    }

    const result = simulation(N, K, map, info, positions);
    console.log(result);
};

solution();
