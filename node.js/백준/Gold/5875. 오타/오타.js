const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
/**
 * 설명 : 괄호 뒤집기 문제이고 o(N)에 풀어야 시간초과가 나지 않는다.
 * 무조건 전이든 후든 음수가 되면 괄호 탈락이다. 이 점을 유의해서 3개의 조건으로 푼다.
 * 1. sum === ±2
 * 2. leftMin(현재 전까지) >= 0
 * 3. rightMin(다음 가는 수들) ± 2 >= 0
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
