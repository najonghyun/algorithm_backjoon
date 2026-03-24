const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const MOD = 1000000007;
/**
 * 설명 : 이문제는 dpT : 전달이 발생하는 부분, dpN : 전달이 발생하지 않는 부분
 * 조건이 연속된 두 줄 중 적어도 한 줄에서는 전달이 일어나야 한다 이므로
 * 1. i번째 줄이 T이면 앞이 뭐든 가능, 그리고 현재 줄 경우의 수가 2
 * dpT[i] = 2 * (dpT[i-1] + dpN[i-1])
 * 2. i번째 줄이 N이면 앞줄은 반드시 T여야 함
 * dpN[i] = dpT[i-1]
 * 
 * ** 경우의 수로 조건이 있을 때 차근차근 더해갈 수 있으면 dp를 생각해보자!
 */
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
