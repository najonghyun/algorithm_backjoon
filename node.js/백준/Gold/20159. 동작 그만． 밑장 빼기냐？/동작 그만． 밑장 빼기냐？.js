const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 계산 해보면 내가 원래 뽑은 순서로 더하다가 밑장을 빼면 상대가 원래 뽑은 순서를 받게 된다.
 * 그래서 각 원래 뽑을 때의 합을 미리 계산해 놓고 그 사이에 따라 각각의 합을 계산해 더해준 후 그것의 최대값을 구하면 된다.
 * 이 때 상대 턴에서도 밑장을 뺄 수 있다. 
 */
const solution = () => {
    const N = Number(input[0]);
    const list = input[1].split(" ").map(Number);
    const count = N / 2;
    const sumMe = new Array(count + 1).fill(0);
    const sumYou = new Array(count + 1).fill(0);

    for (let i = 0; i < count; i++) {
        const odd = i * 2;
        const even = i * 2 + 1;
        sumMe[i + 1] = sumMe[i] + list[odd];
        sumYou[i + 1] = sumYou[i] + list[even];
    }
    
    let result = sumMe[count];
    for (let i = 0; i < count; i++) {
        const case1 = (sumMe[i] - sumMe[0]) + (sumYou[count] - sumYou[i]); // 내 차례 밑장
        const case2 = (sumMe[i + 1]) + (sumYou[count - 1] - sumYou[i]); // 상대 차례 밑장
        result = Math.max(result, case1);
        result = Math.max(result, case2);
    }
    console.log(result);

};

solution();
