const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
/**
 * 설명 : 
 */

let min = Number.MAX_VALUE;

function colorMix(sum, rgb) {
    for (let i = 0; i < 3; i++) {
        sum[i] += rgb[i];
    }
}

function comb(n, rgbs, numbers, gomdori, cnt, start) {
    if (cnt === 8) {
        return;
    }
    if (cnt > 1) {
        // console.log(cnt);
        // console.log(numbers);
        const sum = new Array(3).fill(0);
        for (let i = 0; i < cnt; i++) {
            colorMix(sum, numbers[i]);
        }
        let result = 0;
        for (let i = 0; i < 3; i++) {
            const moondori = Math.floor(sum[i] / cnt);
            const diff = Math.abs(gomdori[i] - moondori);
            result += diff;
        }
        min = Math.min(min, result);
    }

    for (let i = start; i < n; i++) {
        numbers[cnt] = rgbs[i];
        comb(n, rgbs, numbers, gomdori, cnt + 1, i + 1);
    }
}

const solution = () => {
    let answer = 0;
    const n = Number(input[0]);
    const rgbs = new Array(n);
    for (let i = 1; i <= n; i++) {
        rgbs[i - 1] = input[i].split(" ").map(Number);
    }
    const gomdori = input[n + 1].split(" ").map(Number);
    const numbers = new Array(7);
    comb(n, rgbs, numbers, gomdori, 0, 0);
    // let min = Number.MAX_VALUE;



    // for (let i = 1; i < (1 << n); i++) {
    //     const sum = new Array(3).fill(0);
    //     let count = 0;
    //     for (let j = 0; j < n; j++) {
    //         if ((i & (1 << j)) !== 0) {
    //             count++;
    //             colorMix(sum, rgbs[j]);
    //         }
    //     }
    //     if (count < 2 || count > 7) continue;
    //     let result = 0;
    //     for (let j = 0; j < 3; j++) {
    //         const moondori = Math.floor(sum[j] / n);
    //         const diff = Math.abs(gomdori[j] - moondori);
    //         result += diff;
    //     }
    //     // console.log(result);
    //     min = Math.min(min, result);
    // }
    answer = min;
    console.log(answer);
};

solution();