const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
/**
 * 설명 : 괄호 뒤집기 문제는 3개 기억해야 한다.
 * 1. sum == ±2
 * 2. prefix[i-1] >= 0
 * 3. suffixMin[i] ± 2 >= 0
 */
const solution = () => {
    const list = input[0].split("");
    const size = list.length;

    const prefix = new Array(size);
    let sum = 0;
    for (let i = 0; i < size; i++) {
        if (list[i] === '(') {
            sum++;
        } else {
            sum--;
        }
        prefix[i] = sum;
    }

    const leftMin = new Array(size);
    leftMin[0] = prefix[0];
    for (let i = 1; i < size; i++) {
        leftMin[i] = Math.min(leftMin[i - 1], prefix[i]);
    }
    const rightMin = new Array(size + 1).fill(INF);
    for (let i = size - 1; i >= 0; i--) {
        rightMin[i] = Math.min(rightMin[i + 1], prefix[i]);
    }
    // console.log(prefix);
    // console.log(leftMin);
    // console.log(rightMin);
    let count = 0;
    if (sum === -2) {
        for (let i = 0; i < size; i++) {
            if (list[i] === ')') {
                const prev = i === 0 ? true : leftMin[i - 1] >= 0;
                if (prev && rightMin[i] + 2 >= 0) {
                    count++;
                }
            }
        }
    }

    if (sum === 2) {
        for (let i = 0; i < size; i++) {
            if (list[i] === '(') {
                const prev = i === 0 ? true : leftMin[i - 1] >= 0;
                if (prev && rightMin[i] - 2 >= 0) {
                    count++;
                }
            }
        }
    }

    console.log(count);

};
solution();
