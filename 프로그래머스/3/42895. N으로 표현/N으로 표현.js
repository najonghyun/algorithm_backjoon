/**
 * 설명 : 이 문제는 정답의 범위(최대 8번 사용)에서 힌트를 얻어, dp[사용한 N의 개수]를 기준으로 접근했다.
 * 각 단계마다 Set을 활용해 해당 개수로 만들 수 있는 모든 값을 저장하고, 이전 단계들의 결과를 조합하여 사칙연산으로 새로운 값을 생성하는 방식으로 해결했다.
 */
function solution(N, number) {
    const dp = Array.from({ length: 9 }, () => new Set());
    for (let i = 1; i <= 8; i++) {
        dp[i].add(Number(String(N).repeat(i)));

        for (let j = 1; j < i; j++) {
            for (const a of dp[j]) {
                for (const b of dp[i - j]) {
                    dp[i].add(a + b);
                    dp[i].add(a - b);
                    dp[i].add(a * b);
                    if (b !== 0) {
                        dp[i].add(Math.floor(a / b));
                    }
                }
            }
        }

        if (dp[i].has(number)) {
            return i;
        }
    }
    return -1;
}