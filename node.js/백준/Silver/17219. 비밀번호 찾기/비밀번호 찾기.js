const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    const [N, M] = input[0].split(" ").map(Number);
    const memo = new Map();
    for (let i = 1; i <= N; i++) {
        const [id, pw] = input[i].split(" ");
        memo.set(id, pw);
    }
    for (let i = N + 1; i <= N + M; i++) {
        const target = input[i];
        if (memo.has(target)) {
            console.log(memo.get(target));
        }
    }
};
solution();