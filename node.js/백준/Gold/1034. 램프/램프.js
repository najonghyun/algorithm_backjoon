const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const solution = () => {
    let answer = 0;
    const [n, m] = input[0].split(" ").map(Number);
    const list = new Array(n);
    for (let i = 1; i <= n; i++) {
        list[i - 1] = input[i];
    }
    const k = Number(input[n + 1]);
    const map = new Map();
    for (row of list) {
        let count = 0;
        row.split("").forEach(num => { if (num === '0') count++ });

        if (count > k) continue;
        if ((k - count) % 2 !== 0) continue;

        map.set(row, (map.get(row) ?? 0) + 1);
        answer = Math.max(answer, map.get(row));
    };
    console.log(answer);

}



solution();