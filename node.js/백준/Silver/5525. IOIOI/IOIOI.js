const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 'IOI' 패턴이 반복되는 식이어서 'O'를 기준으로 양쪽 조사하고 맞으면 더 이어서 가보고 
 * 아니면 초기화 이런식으로 해결했다.
 */
const solution = () => {
    const N = Number(input[0]);
    const M = Number(input[1]);
    const list = input[2].split("");

    let count = 0;
    let result = 0;
    for (let i = 1; i < M - 1; i++) {
        if (list[i - 1] === "I" && list[i] === "O" && list[i + 1] === "I") {
            count++;
            if (count >= N) result++;
            i++;
        } else {
            count = 0;
        }
    }
    console.log(result);
};

solution();
