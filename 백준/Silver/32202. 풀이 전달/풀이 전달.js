const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const MOD = 1000000007;

const solution = () => {
    const N = Number(input[0]);
    const dpT = new Array(N + 1);
    const dpN = new Array(N + 1);

    dpT[1] = 2;
    dpN[1] = 1;
    for (let i = 2; i <= N; i++) {
        dpT[i] = (2 * (dpT[i - 1] + dpN[i - 1])) % MOD;
        dpN[i] = dpT[i - 1] % MOD;
    }
    console.log((dpT[N] + dpN[N]) % MOD);
};
solution();
