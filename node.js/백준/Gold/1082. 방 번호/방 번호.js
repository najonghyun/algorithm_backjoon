const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 중복을 허용하는 그리디 dp이다.
 * 이거는 값으로하면 maxNumber를 넘어가기 때문에 문자열로 해야한다. 그래서 문자열 크기 비교 함수를 만들고 이를 이용해서 dp[값] = 방번호 로 처리했다.
 */
function better(a, b) {
    if (a === null) return b;
    if (b === null) return a;

    if (a.length !== b.length) {
        return a.length > b.length ? a : b;
    }
    return a > b ? a : b;
}

const solution = () => {
    const n = Number(input[0]);
    const shops = input[1].split(" ").map(Number);
    const m = Number(input[2]);
    const dp = new Array(m + 1).fill(null);

    dp[0] = "";
    for (let i = 0; i <= m; i++) {
        if (dp[i] === null) continue;
        for (let j = 0; j < n; j++) {
            if (i + shops[j] > m) continue;
            if (dp[i] === "" && j === 0) continue;
            const newCost = dp[i] + String(j);
            dp[i + shops[j]] = better(dp[i + shops[j]], newCost);
        }
    }

    let result = null;
    for (let i = 0; i <= m; i++) {
        result = better(result, dp[i]);
    }
    console.log(result === "" ? "0" : result);
};
solution();
