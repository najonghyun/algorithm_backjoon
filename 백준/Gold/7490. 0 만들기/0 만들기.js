const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

function dfs(n, cnt, print, list) {
    if (cnt === n) {
        print += cnt;
        const size = print.length;
        let word = "";
        let curr = "+";
        let sum = 0;
        for (let i = 0; i < size; i++) {
            const v = print.charAt(i);
            switch (v) {
                case "+":
                    sum = sum + (curr === "+" ? Number(word) : -Number(word));
                    curr = "+";
                    word = "";
                    break;
                case "-":
                    sum = sum + (curr === "+" ? Number(word) : -Number(word));
                    curr = "-";
                    word = "";
                    break;
                case " ":
                    break;
                default:
                    word += v;
                    break;
            }
        }
        sum = sum + (curr === "+" ? Number(word) : -Number(word));
        // console.log(sum);
        if (sum === 0) {
            list.push(print);
        }
        return;
    }
    dfs(n, cnt + 1, print + cnt + " ", list);
    dfs(n, cnt + 1, print + cnt + "+", list);
    dfs(n, cnt + 1, print + cnt + "-", list);
}
const solution = () => {
    const result = {};
    for (let i = 3; i <= 9; i++) {
        result[i] = [];
        dfs(i, 1, "", result[i]);
    }
    const T = Number(input[0]);
    for (let t = 1; t <= T; t++) {
        const n = Number(input[t]);
        console.log(result[n].join("\n"));
        if (t !== T) console.log();
    }
};
solution();
