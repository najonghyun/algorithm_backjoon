const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const INF = 1e18;

const tri = {
  0: [1, 2],
  1: [0, 2],
  2: [0, 1],
};

function buildPattern(N, root, flip) {
  const p = Array.from({ length: N }, (_, i) => Array(i + 1).fill(0));
  p[0][0] = root;
  if (N >= 2) {
    p[1][0] = tri[root][flip ? 1 : 0];
    p[1][1] = tri[root][flip ? 0 : 1];
  }
  for (let i = 2; i < N; i++) {
    for (let j = 1; j < i; j++) {
      p[i][j] = 3 - (p[i - 1][j - 1] + p[i - 1][j]);
    }
    p[i][0] = 3 - (p[i - 1][0] + p[i][1]);
    p[i][i] = 3 - (p[i - 1][i - 1] + p[i][i - 1]);
  }
  return p;
}

function rowMinSwaps(row, target) {
  // m[a][b] : current=a, target=b (a!=b)
  const m = [
    [0, 0, 0],
    [0, 0, 0],
    [0, 0, 0],
  ];

  for (let k = 0; k < row.length; k++) {
    const a = row[k];
    const b = target[k];
    if (a !== b) m[a][b]++;
  }

  let swaps = 0;

  // pair swaps
  for (let a = 0; a < 3; a++) {
    for (let b = a + 1; b < 3; b++) {
      const x = Math.min(m[a][b], m[b][a]);
      swaps += x;
      m[a][b] -= x;
      m[b][a] -= x;
    }
  }

  // remaining must be 3-cycles
  let rem = 0;
  for (let a = 0; a < 3; a++) {
    for (let b = 0; b < 3; b++) {
      if (a !== b) rem += m[a][b];
    }
  }

  // rem is multiple of 3
  swaps += (rem / 3) * 2;
  return swaps;
}

function calc(N, pyr, pat) {
  let ans = 0;

  for (let i = 0; i < N; i++) {
    // 행 전체 멀티셋 일치 검사 (이게 핵심 안정장치)
    const ca = [0, 0, 0];
    const cb = [0, 0, 0];
    for (let j = 0; j <= i; j++) {
      ca[pyr[i][j]]++;
      cb[pat[i][j]]++;
    }
    if (ca[0] !== cb[0] || ca[1] !== cb[1] || ca[2] !== cb[2]) return INF;

    // i==0도 자동 0으로 나옴(스왑 불가여도 문제 없음)
    ans += rowMinSwaps(pyr[i], pat[i]);
  }

  return ans;
}

function solution() {
  const N = Number(input[0]);
  const pyr = Array.from({ length: N }, (_, i) => input[i + 1].split(" ").map(Number));

  // N==0 같은 건 없음, N>=1
  const root = pyr[0][0];

  const patA = buildPattern(N, root, false);
  const patB = buildPattern(N, root, true);

  const res = Math.min(calc(N, pyr, patA), calc(N, pyr, patB));
  console.log(res >= INF ? -1 : res);
}

solution();
