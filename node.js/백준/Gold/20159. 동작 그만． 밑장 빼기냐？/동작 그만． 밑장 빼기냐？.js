const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

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
    // console.log(sumMe);
    // console.log(sumYou);

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
