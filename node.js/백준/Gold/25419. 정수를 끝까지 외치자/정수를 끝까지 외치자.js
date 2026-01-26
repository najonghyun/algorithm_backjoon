const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const solution = () => {
    const [n, k] = input[0].split(" ").map(Number);

    const blackList = {};
    input[1].split(" ").map(Number).forEach(num => blackList[num] = true);

    // dp[a]: a를 마지막으로 외쳤을 때 현재 플레이어가 이기는가
    const dp = Array(n + 1).fill(false);

    // 뒤에서부터 계산
    for (let a = n; a >= 0; a--) {
        let canWin = false;

        for (let b = a + 1; b <= a + k && b <= n; b++) {
            if (blackList[b]) continue;
            if (!dp[b]) {
                canWin = true;
                break;
            }
        }

        dp[a] = canWin;
    }

    // 시작 상태는 0
    console.log(dp[0] ? 1 : 0);
};

solution();