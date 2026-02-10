const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
/**
 * 설명 : 규칙을 찾아보면 정답패턴이 root에 있는 값 기준으로 2가지 방식만 답이 될 수 있다. 그래서 정답 배열을 먼저 만들고 그것과 피라미드를 행마다 비교하면서
 * 최소 swap 개수를 구했다.
 * 이 때 각 행당 최소 swap은 서로 다를 때 크게 상호교환과 3개 교차교환 두가지 방식이 있는데
 * 먼저 최소인 상호교환이 몇개 가능한지 봐주고 나머지 다른거는 3개 교차교환으로 개수를 구해서 해결했다.
 */
const triangle = { 0: [1, 2], 1: [0, 2], 2: [0, 1] };
function makePyramid(n, root, pyramid, isFirst) {
    for (let i = 0; i < n; i++) {
        if (i === 0) pyramid[i][0] = root;
        if (i === 1) {
            pyramid[i][0] = isFirst ? triangle[root][0] : triangle[root][1];
            pyramid[i][1] = isFirst ? triangle[root][1] : triangle[root][0];
        }
        if (i >= 2) {
            for (let j = 1; j < i; j++) {
                pyramid[i][j] = 3 - (pyramid[i - 1][j - 1] + pyramid[i - 1][j]);
            }
            pyramid[i][0] = 3 - (pyramid[i - 1][0] + pyramid[i][1]);
            pyramid[i][i] = 3 - (pyramid[i - 1][i - 1] + pyramid[i][i - 1]);
        }
    }
}

function rowMinSwaps(row, target, size) {
    const m = [
        [0, 0, 0],
        [0, 0, 0],
        [0, 0, 0],
    ];
    for (let i = 0; i < size; i++) {
        const a = row[i];
        const b = target[i];
        if (a !== b) m[a][b]++;
    }
    let swap = 0;
    for (let a = 0; a < 3; a++) {
        for (let b = a + 1; b < 3; b++) {
            const x = Math.min(m[a][b], m[b][a]);
            swap += x;
            m[a][b] -= x;
            m[b][a] -= x;
        }
    }
    let rem = 0;
    for (let a = 0; a < 3; a++) {
        for (let b = 0; b < 3; b++) {
            if (a !== b) rem += m[a][b];
        }
    }
    swap += (rem / 3) * 2;
    return swap;
}

function calculate(n, pyramid, pattern) {
    let swapCount = 0;
    for (let i = 0; i < n; i++) {
        const ca = [0, 0, 0];
        const cb = [0, 0, 0];
        for (let j = 0; j <= i; j++) {
            ca[pyramid[i][j]]++;
            cb[pattern[i][j]]++;
        }
        if (ca[0] !== cb[0] || ca[1] !== cb[1] || ca[2] !== cb[2]) return INF;
        swapCount += rowMinSwaps(pyramid[i], pattern[i], i + 1);
    }
    return swapCount;
}

const solution = () => {
    const N = Number(input[0]);
    const pyramid = new Array(N);
    for (let i = 1; i <= N; i++) {
        pyramid[i - 1] = input[i].split(" ").map(Number);
    }
    const pattern1 = Array.from({ length: N }, (_, i) => new Array(i + 1).fill(0));
    makePyramid(N, pyramid[0][0], pattern1, true);
    const pattern2 = Array.from({ length: N }, (_, i) => new Array(i + 1).fill(0));
    makePyramid(N, pyramid[0][0], pattern2, false);
    let result = INF;
    result = Math.min(calculate(N, pyramid, pattern1), calculate(N, pyramid, pattern2));
    console.log(result === INF ? -1 : result);
};
solution();
