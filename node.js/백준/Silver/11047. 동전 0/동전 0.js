const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    const [N, K] = input[0].split(" ").map(Number);
    const coins = new Array(N);
    for (let i = 1; i <= N; i++) {
        coins[i - 1] = Number(input[i]);
    }

    let target = K;
    let result = 0;
    for (let i = N - 1; i >= 0; i--) {
        const coin = coins[i];
        if (target >= coin) {
            const count = Math.floor(target / coin);
            result += count;
            target = target % coin;
        }
    }
    console.log(result);
};
solution();
