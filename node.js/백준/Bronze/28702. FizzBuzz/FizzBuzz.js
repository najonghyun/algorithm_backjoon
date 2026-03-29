const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    const [x1, x2, x3] = [Number(input[0]), Number(input[1]), Number(input[2])];
    const target = x1 ? x1 + 3 : x2 ? x2 + 2 : x3 + 1;
    const is3x = target % 3 === 0;
    const is5x = target % 5 === 0;

    if (is3x && is5x) {
        console.log('FizzBuzz');
    } else if (is3x) {
        console.log("Fizz");
    } else if (is5x) {
        console.log("Buzz");
    } else {
        console.log(target);
    }
};

solution();
