const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () =>{
    const a = input[0];
    const b = input[1];
    const c = input[2];
    console.log(Number(a) + Number(b) - Number(c));
    console.log(Number(a + b) - Number(c));
}
solution();