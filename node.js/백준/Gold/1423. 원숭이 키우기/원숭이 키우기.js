const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 :
 * 각 캐릭터를 "이 캐릭터를 몇 일 훈련할지" 선택하는 문제로 본다.
 * 총 훈련 가능 일수는 D일이므로, 같은 레벨의 캐릭터도 최대 D마리까지만 고려하면 된다.
 *
 * members 배열에 캐릭터의 시작 레벨을 펼쳐 넣고,
 * dp[i][j] = 앞에서 i명의 캐릭터를 고려했을 때, j일 사용해서 얻을 수 있는 힘 증가량의 최댓값
 *
 * i번째 캐릭터의 시작 레벨이 member일 때, t일 훈련시키면 member+t 레벨이 되고,
 * 증가량은 powers[member+t] - powers[member]
 *
 * 점화식: dp[i][j] = max(dp[i][j], dp[i-1][j-t] + (powers[member+t] - powers[member]))
 * (0 <= t <= min(최대 가능 훈련일, j))
 *
 * 마지막 답: 초기 힘의 합 + dp[M][D]
 * 시간복잡도: o(N² D²)
 */
const solution = () => {
    const N = Number(input[0]);
    const characters = input[1].split(" ").map(Number);
    const powers = input[2].split(" ").map(Number);
    const D = Number(input[3]);

    const members = [];
    for (let i = 0; i < N; i++) {
        const useCount = Math.min(characters[i], D);
        for (let j = 0; j < useCount; j++) {
            members.push(i);
        }
    }

    const M = members.length;
    const dp = Array.from({ length: M + 1 }, () => new Array(D + 1).fill(0));
    for (let i = 1; i <= M; i++) {
        for (let j = 0; j <= D; j++) {
            for (let t = 0; t <= Math.min(N - 1 - members[i - 1], j); t++) {
                const plus = powers[members[i - 1] + t] - powers[members[i - 1]];
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t] + plus);
            }
        }
    }

    let result = 0;
    for (let i = 0; i < N; i++) {
        result += characters[i] * powers[i];
    }
    console.log(result + dp[M][D]);
};

solution();
