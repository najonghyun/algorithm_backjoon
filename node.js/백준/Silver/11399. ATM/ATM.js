const fs = require("fs");
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

const solution = () => {
    const n = Number(input[0]);
    const array = input[1].split(" ");
    array.sort(function (a, b) {
        return a - b;
    });
    let result = 0;
    let sum = 0;
    array.forEach(num => {
        sum += Number(num);
        result += sum;
    })

    console.log(result);
}

solution();