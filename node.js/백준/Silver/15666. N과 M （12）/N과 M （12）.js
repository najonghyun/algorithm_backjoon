const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 중복은 가능하고 순서는 구분하므로 중복조합을 이용해서 풀었다. 그리고 같은 것은 set으로 분리 했다.
 */

function comb(n, m, list, numbers, set, cnt, start) {
    if (cnt === m) {
        const key = numbers.join(" ");
        if (!set.has(key)) {
            console.log(key)
        }
        set.add(key);
        return;
    }
    for (let i = start; i < n; i++) {
        numbers[cnt] = list[i];
        comb(n, m, list, numbers, set, cnt + 1, i);
    }
}

const solution = () => {
    const [n, m] = input[0].split(" ").map(Number);
    const list = input[1].split(" ").map(Number);
    const numbers = new Array(m);
    list.sort((a, b) => a - b);
    const set = new Set();
    comb(n, m, list, numbers, set, 0, 0);
};

solution();