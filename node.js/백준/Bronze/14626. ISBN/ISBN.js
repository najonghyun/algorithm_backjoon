const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    const isbn = input[0].split("");
    const multiply = [1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3];
    let result = 0;
    for (let x = 0; x <= 9; x++) {
        let sum = 0;
        for (let i = 0; i < 12; i++) {
            if (isbn[i] === "*") {
                sum += x * multiply[i];
            } else {
                sum += Number(isbn[i]) * multiply[i];
            }
        }
        const m = sum % 10 === 0 ? 0 : 10 - (sum % 10);
        if (m === Number(isbn[12])) {
            result = x;
            break;
        };
    }
    console.log(result);
};

solution();
