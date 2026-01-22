const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
/**
 * 설명 : 꽃의 범위가 100까지 이므로 dp에 각 꽃을 마지막으로 뽑았을 때 최대값을 넣기 위해 max[100]으로 정의했다.
 */
const solution = () => {
    const n = Number(input[0]);
    const list = input[1].split(" ").map(Number);

    const max = new Array(101).map(Number).fill(0); // 마지막으로 뽑은 꽃의 최대 값
    const set = new Set();
    for (let i = 0; i < n; i++) {
        for (let j = 1; j <= 100; j++) {
            if (set.has(j)) {
                const diff = Math.abs(list[i] - j);
                max[list[i]] = Math.max(max[list[i]], max[j] + (diff * diff));
            }
        }
        set.add(list[i]);
    }
    console.log(max[list[n - 1]]);
};

solution();