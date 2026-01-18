const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split("\n");


function primeCalculate(isPrime, size) {
    isPrime[0] = false;
    isPrime[1] = false;
    const rootSize = Math.sqrt(size);
    for (let i = 2; i <= rootSize; i++) {
        if (!isPrime[i]) continue;
        for (let j = i * i; j <= size; j += i) {
            isPrime[j] = false;
        }
    }
}

function lowerbound(n, list, target) {
    let start = 0;
    let end = n - 1;
    while (start < end) {
        const mid = Math.floor((start + end) / 2);
        if (list[mid] < target) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }
    return start;
}

const solution = () => {
    const size = 10000000;
    const isPrime = new Array(size + 1).fill(true);
    primeCalculate(isPrime, size);
    primeList = [];
    isPrime.forEach((num, index) => num ? primeList.push(index) : null);
    const answer = [];
    const n = Number(input[0]);
    for (let i = 1; i <= n; i++) {
        const [a, b] = input[i].split(" ").map(Number);
        const start = lowerbound(primeList.length, primeList, a);
        const end = isPrime[b] ? lowerbound(primeList.length, primeList, b) : lowerbound(primeList.length, primeList, b) - 1;
        const diff = end - start;
        if (diff % 2 == 0) {
            const mid = (start + end) / 2;
            answer.push(primeList[mid]);
        } else {
            answer.push(-1);
        }
    }
    console.log(answer.join("\n"));
}

solution();