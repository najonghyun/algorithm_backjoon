const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 이 문제는 시간초과 피하기 위해서 나눗셈의 몫과 나머지를 이용해서 풀었다. 이때 각 위치까지의 개수 합을 누적합 방식으로 미리 계산해서 푼다.
 *
 * + N의 size가 이미 10^(18)이므로 값을 계산하기 위해서는 BigInt를 사용해야 한다. 이때 BigInt는 자기들끼리만 연산이 가능하므로 주의해야 한다.
 */
const solution = () => {
    let index = 0;
    const N = Number(input[index++]);
    const word = input[index++].split("");
    const size = word.length;
    const bigSize = BigInt(size);
    const wordSum = new Array(size + 1);
    wordSum[0] = {};
    for (let i = 65; i < 91; i++) {
        wordSum[0][String.fromCharCode(i)] = 0;
    }
    for (let i = 1; i <= size; i++) {
        wordSum[i] = { ...wordSum[i - 1] };
        wordSum[i][word[i - 1]]++;
    }

    const K = Number(input[index++]);
    const result = new Array(K).fill(0);
    for (let i = 0; i < K; i++) {
        const [a, c] = input[index++].split(" ");
        const num = BigInt(a);
        const startPoint = Number(((num * (num - 1n)) / 2n) % bigSize);
        const q = num / bigSize;
        result[i] = BigInt(wordSum[size][c]) * q;
        const r = Number(num % bigSize);
        if (startPoint + r > size) {
            result[i] += BigInt(wordSum[size][c]) - BigInt(wordSum[startPoint][c]);
            const extra = startPoint + r - size;
            result[i] += BigInt(wordSum[extra][c]);
        } else {
            result[i] += BigInt(wordSum[startPoint + r][c]) - BigInt(wordSum[startPoint][c]);
        }
    }
    console.log(result.map((v) => v.toString()).join("\n"));
};
solution();
