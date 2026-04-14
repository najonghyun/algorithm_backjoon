const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 이 문제는 조건을 잘 보면서 and 예외 따지면서 그리디하게 접근해야 한다.
 * 일단 건물을 보기위해 필요한 최소 조건은 a+b-1이 되야 답을 찾을 수 있다.
 * 그 후 둘 중 max 건물 기준으로 앞에서부터와 뒤에서부터 보며 각 건물을 세우고 나머지는
 * a === 1일때 (가장 큰건물만 보여야 하고 max건물 바로뒤에 1로 채워야 함) or a !== 1일때(사전순이므로 앞에가 1로 채워져야 함)로 나눠서 나머지를 채웠다.
 */
const solution = () => {
    const [N, a, b] = input[0].split(" ").map(Number);
    const result = [];
    if (N < a + b - 1) {
        console.log(-1);
        return;
    }
    const max = Math.max(a, b);
    for (let i = 1; i < a; i++) {
        result.push(i);
    }
    result.push(max);
    for (let i = b - 1; i > 0; i--) {
        result.push(i);
    }

    const rest = N - (a + b - 1);
    if (rest === 0) {
        console.log(result.join(" "));
        return;
    }
    const answer = [];
    for (let i = 0; i < rest; i++) {
        answer.push(1);
    }
    if (a === 1) {
        console.log(result.slice(0, a).join(" ") + " " + answer.join(" ") + " " + result.slice(a).join(" "));
    } else {
        console.log(answer.join(" ") + " " + result.join(" "));
    }
};
solution();
