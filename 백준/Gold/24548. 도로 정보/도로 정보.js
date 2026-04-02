const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 이 문제는 부분문자열의 부분 개수차이가 서로 같아야 한다는 성질을 알고 있어야 풀 수 있다.
 * 그래서 각 4개의 종류를 3으로 나눈 나머지들의 쌍을 구한 후 그 쌍이 2개부터 각 쌍들의 합이 될 수 있는 경우의 수가 된다.
 * %쌍 구하는 공식% : (n * n-1) / 2  -> because n개가 추가될 때마다 n개만큼 쌍이 불어나므로
 */
const solution = () => {
    const N = Number(input[0]);
    const list = input[1].split("");
    const listSum = Array.from({ length: N + 1 }, () => ({ T: 0, G: 0, F: 0, P: 0 }));

    for (let i = 1; i <= N; i++) {
        listSum[i] = { ...listSum[i - 1] };
        const v = list[i - 1];
        listSum[i][v]++;
    }

    const map = new Map();
    map.set("0 0 0 0", 1);
    let count = 0;
    for (let i = 1; i <= N; i++) {
        const t = listSum[i]["T"] % 3;
        const g = listSum[i]["G"] % 3;
        const f = listSum[i]["F"] % 3;
        const p = listSum[i]["P"] % 3;
        const key = `${t} ${g} ${f} ${p}`;
        const prev = map.get(key) ?? 0;
        count += prev;
        map.set(key, prev + 1);
    }
    console.log(count);
};

solution();
