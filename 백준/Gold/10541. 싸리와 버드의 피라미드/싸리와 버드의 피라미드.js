const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

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
    // console.log(wordSum);

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
