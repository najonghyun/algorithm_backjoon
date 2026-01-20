const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const solution = () => {
    let answer = 0;
    const n = Number(input[0]);
    const list = new Array(n);
    for (let i = 1; i <= n; i++) {
        list[i - 1] = input[i].split(" ").map(Number);
    }
    const memo = new Array(n);
    for (let i = 0; i < n; i++) {
        memo[i] = new Array(i + 1).fill(0);
    }

    memo[0][0] = list[0][0];
    for (let y = 0; y < n - 1; y++) {
        for (let x = 0; x <= y; x++) {
            if (memo[y + 1][x] < memo[y][x] + list[y + 1][x]) memo[y + 1][x] = memo[y][x] + list[y + 1][x];
            if (memo[y + 1][x + 1] < memo[y][x] + list[y + 1][x + 1]) memo[y + 1][x + 1] = memo[y][x] + list[y + 1][x + 1];
        }
    }

    memo[n - 1].forEach((num) => (answer = Math.max(answer, num)));
    console.log(answer);
};

solution();
