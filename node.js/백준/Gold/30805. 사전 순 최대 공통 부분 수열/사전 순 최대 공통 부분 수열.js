const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
/**
 * 설명 : 분석해보면 이 문제는 같은 공통 수열의 가장 큰 첫 수 이후 나머지 부분에서 또 가장 큰 수 ... 
 * 이런식으로 접근해 나가면 답을 구할 수 있다. 값이 <= 100이므로 그렇게 크지 않기 때문에 그리디로
 * 풀 수 있다.
 */
const solution = () => {
    const N = Number(input[0]);
    const A = input[1].split(" ").map(Number);
    const M = Number(input[2]);
    const B = input[3].split(" ").map(Number);

    let aStart = 0, bStart = 0;
    const result = [];
    while (aStart < N && bStart < M) {
        let found = false;
        for (let num = 100; num >= 1; num--) {
            let aIdx = -1, bIdx = -1;
            for (let i = aStart; i < N; i++) {
                if (A[i] === num) {
                    aIdx = i;
                    break;
                }
            }
            if (aIdx === -1) continue;
            for (let j = bStart; j < M; j++) {
                if (B[j] === num) {
                    bIdx = j;
                    break;
                }
            }
            if (bIdx === -1) continue;
            result.push(num);
            aStart = aIdx + 1;
            bStart = bIdx + 1;
            found = true;
            break;
        }
        if (!found) break;
    }
    console.log(result.length);
    console.log(result.join(" "));
};

solution();
