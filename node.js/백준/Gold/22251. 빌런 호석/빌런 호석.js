const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const changeCountList =
    [[0, 4, 3, 3, 4, 3, 2, 3, 1, 2],
    [4, 0, 5, 3, 2, 5, 6, 1, 5, 4],
    [3, 5, 0, 2, 5, 4, 3, 4, 2, 3],
    [3, 3, 2, 0, 3, 2, 3, 2, 2, 1],
    [4, 2, 5, 3, 0, 3, 4, 3, 3, 2],
    [3, 5, 4, 2, 3, 0, 1, 4, 2, 1],
    [2, 6, 3, 3, 4, 1, 0, 5, 1, 2],
    [3, 1, 4, 2, 3, 4, 5, 0, 4, 3],
    [1, 5, 2, 2, 3, 2, 1, 4, 0, 1],
    [2, 4, 3, 1, 2, 1, 2, 3, 1, 0]
    ];
    /**
     * 설명 하드코딩으로 각 숫자 바꾸는데 필요한 반전개수 넣어주고 그거에 맞게 풀었다.
     */
const solution = () => {
    const [n, k, p, x] = input[0].split(" ").map(Number);
    let result = 0;
    for (let i = 1; i <= n; i++) {
        if (i === x) continue;
        let num = String(i);
        let target = String(x);
        if (target.length > num.length) {
            const plus = target.length - num.length;
            num = "0".repeat(plus) + num;
        } else if (target.length < num.length) {
            const plus = num.length - target.length;
            target = "0".repeat(plus) + target;
        }
        // num = num.padStart(k, "0");
        // target = target.padStart(k, "0");

        let count = 0;
        for (let j = 0; j < num.length; j++) {
            const from = Number(num.charAt(j));
            const to = Number(target.charAt(j));
            count += changeCountList[from][to];
        }
        if (count <= p) {
            result++;
        }
    }
    console.log(result);

};
solution();
