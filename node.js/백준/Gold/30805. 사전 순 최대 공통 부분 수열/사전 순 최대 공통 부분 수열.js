const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function compare(arr1, arr2) {
    const len = Math.min(arr1.length, arr2.length);
    for (let i = 0; i < len; i++) {
        if (arr1[i] !== arr2[i]) {
            return arr1[i] > arr2[i] ? arr1 : arr2;
        }
    }
    return arr1.length > arr2.length ? arr1 : arr2;
}

const solution = () => {
    const N = Number(input[0]);
    const A = input[1].split(" ").map(Number);
    const M = Number(input[2]);
    const B = input[3].split(" ").map(Number);

    const dp = Array.from({ length: N + 1 }, () => Array.from({ length: M + 1 }, () => []));
    for (let i = N - 1; i >= 0; i--) {
        for (let j = M - 1; j >= 0; j--) {
            let best = compare(dp[i + 1][j], dp[i][j + 1]);
            if (A[i] === B[j]) {
                const temp = [A[i], ...dp[i + 1][j + 1]];
                best = compare(best, temp);
            }
            dp[i][j] = best;
        }
    }
    const size = dp[0][0].length;
    console.log(size);
    if (size > 0) {
        console.log(dp[0][0].join(" "));
    }
};

solution();
