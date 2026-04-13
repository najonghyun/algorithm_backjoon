const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

const solution = () => {
    const N = Number(input[0]);
    const list = new Array(N);
    for (let i = 1; i <= N; i++) {
        list[i - 1] = [input[i], i - 1];
    }
    list.sort((a, b) => {
        return a[0].localeCompare(b[0]);
    });
    // console.log(list);

    let max = 0;
    const maxArr = new Array(N).fill(0);
    for (let i = 1; i < N; i++) {
        const arr1 = list[i - 1][0].split("");
        const arr2 = list[i][0].split("");
        const size = Math.min(arr1.length, arr2.length);
        let count = 0;
        for (let j = 0; j < size; j++) {
            if (arr1[j] !== arr2[j]) break;
            count++;
        }
        maxArr[i] = count;
        max = Math.max(max, count);
    }

    // console.log(maxArr);
    let result1 = null,
        result2 = null;
    let min1 = Number.MAX_VALUE;
    let min2 = Number.MAX_VALUE;
    let start = 0;
    while (start < N) {
        let end = start;
        while (end + 1 < N) {
            if (max > maxArr[end + 1]) break;
            end++;
        }
        if (start < end) {
            let tempResult1 = null,
                tempResult2 = null;
            let tempMin1 = Number.MAX_VALUE;
            let tempMin2 = Number.MAX_VALUE;
            for (let i = start; i <= end; i++) {
                const [word, index] = [list[i][0], Number(list[i][1])];
                if (word === tempResult1 || word === tempResult2) continue;
                if (index < tempMin1) {
                    tempResult2 = tempResult1;
                    tempResult1 = word;
                    tempMin2 = tempMin1;
                    tempMin1 = index;
                } else if (index < tempMin2) {
                    tempResult2 = word;
                    tempMin2 = index;
                }
            }
            if (tempMin1 < min1 || (tempMin1 === min1 && tempMin2 < min2)) {
                min1 = tempMin1;
                min2 = tempMin2;
                result1 = tempResult1;
                result2 = tempResult2;
            }
        }
        start = end + 1;
    }

    if (result1) console.log(result1);
    if (result2) console.log(result2);
};
solution();
