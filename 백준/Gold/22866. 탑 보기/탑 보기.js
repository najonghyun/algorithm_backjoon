const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : stack을 이용해 거꾸로 적용했고, 왼쪽 뷰와 오른쪽 뷰를 나누어 계산했다.
 * 각각의 차이와 그때의 값, 총 개수를 객체로 저장해 놓고, 그 후 조건에 따라 출력했다. (N이 큰 출력은 배열이나 문자열로 저장하고 한번에 출력!)
 */
function calculate(stack, now) {
    const size = stack.length;
    if (size === 0) return { count: size };
    const top = stack[size - 1];
    return { count: size, diff: Math.abs(top - now), num: top };
}

const solution = () => {
    const N = Number(input[0]);
    const list = input[1].split(" ").map(Number);

    const rightView = Array.from({ length: N }, () => ({
        count: 0,
    }));
    const stack = [];
    stack.push(N - 1);
    for (let i = N - 2; i >= 0; i--) {
        while (stack.length > 0 && list[i] >= list[stack[stack.length - 1]]) {
            stack.pop();
        }
        rightView[i] = calculate(stack, i);
        stack.push(i);
    }

    const leftView = Array.from({ length: N }, () => ({
        count: 0,
    }));
    const stack2 = [];
    stack2.push(0);
    for (let i = 1; i < N; i++) {
        while (stack2.length > 0 && list[i] >= list[stack2[stack2.length - 1]]) {
            stack2.pop();
        }
        leftView[i] = calculate(stack2, i);
        stack2.push(i);
    }

    const result = [];
    for (let i = 0; i < N; i++) {
        if (rightView[i].count === 0 && leftView[i].count === 0) {
            result.push(0);
        } else if (rightView[i].count === 0) {
            result.push(leftView[i].count + " " + (leftView[i].num + 1));
        } else if (leftView[i].count === 0) {
            result.push(rightView[i].count + " " + (rightView[i].num + 1));
        } else {
            const sum = rightView[i].count + leftView[i].count;
            if (rightView[i].diff >= leftView[i].diff) {
                result.push(sum + " " + (leftView[i].num + 1));
            } else {
                result.push(sum + " " + (rightView[i].num + 1));
            }
        }
    }
    console.log(result.join("\n"));
};
solution();
