const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

let result;
function gcd(a, b) {
    while (b !== 0) {
        [a, b] = [b, a % b]
    }
    return a;
}
const lcm = (a, b) => (a * b) / gcd(a, b);

function findPair(n, pattern) {
    const visited = new Array(n).fill(false);
    for (let i = 0; i < n; i++) {
        if (visited[i]) continue;
        let next = pattern[i] - 1;
        let count = 1;
        while (true) {
            if (i === next) break;
            count++;
            visited[next] = true;
            next = pattern[next] - 1;
        }
        result = lcm(result, count);
    }
}

const solution = () => {
    const n = Number(input[0]);
    const pattern = input[1].split(" ").map(Number);
    result = 1;
    findPair(n, pattern);
    console.log(result);
};

solution();
