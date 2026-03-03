const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    let index = 0;
    for (t = 0; t < 3; t++) {
        const N = Number(input[index++]);
        const list = new Array(N);
        let total = 0;

        for (let i = 0; i < N; i++) {
            const [coin, count] = input[index++].split(" ").map(Number);
            list[i] = [coin, count];
            total += coin * count;
        }
        if (total % 2 === 1) {
            console.log(0);
            continue;
        }
        
        const target = total / 2;
        const dp = new Array(target + 1).fill(false);
        dp[0] = true;

        for ([coin, count] of list) {
            for (let i = target; i >= 0; i--) {
                if (!dp[i]) continue;
                for (let j = 1; j <= count; j++) {
                    const next = i + coin * j;
                    if (next > target) break;
                    dp[next] = true;
                }
            }
        }
        console.log(dp[target] ? 1 : 0);
    }
};
solution();
