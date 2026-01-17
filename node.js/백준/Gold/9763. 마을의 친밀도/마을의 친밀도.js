const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function calculation(list1, list2) {
    const x = Math.abs(list1[0] - list2[0]);
    const y = Math.abs(list1[1] - list2[1]);
    const z = Math.abs(list1[2] - list2[2]);
    return x + y + z;
}
const solution = () => {
    const n = Number(input[0]);
    const list = new Array(n);
    for (let i = 1; i <= n; i++) {
        list[i - 1] = input[i].split(" ").map(Number);
    }

    let result = Number.MAX_VALUE;
    for (let i = 0; i < n; i++) {
        let min1 = Number.MAX_VALUE;
        let min2 = Number.MAX_VALUE;
        for (let j = 0; j < n; j++) {
            if (i == j) continue;
            const distance = calculation(list[i], list[j]);
            if (min1 > distance) {
                min2 = min1;
                min1 = distance;
            } else if (min2 > distance) {
                min2 = distance;
            }
        }
        result = Math.min(result, min1 + min2);
    }

    console.log(result);

}

solution();