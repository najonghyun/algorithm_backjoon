const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    const N = Number(input[0]);
    const M = Number(input[1]);
    const list = input[2].split("");
    const size = 2 * N + 1;
    let target = "";
    let now = "";
    let count = 0;
    for (let i = 0; i < size; i++) {
        if (i % 2 === 0) target += "I";
        else target += "O";
        now += list[i];
    }
    if (target.includes(now)) count++;
    // console.log(target);
    // console.log(now);
    for (let i = size; i < M; i++) {
        now = now.substring(1);
        now += list[i];
        if (target.includes(now)) count++;
    }
    console.log(count);
};
solution();
