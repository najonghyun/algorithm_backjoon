const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 그냥 비교하면 시간초과 나기 때문에 정렬을 하고 비교해야 한다. 이때 먼저 최대 접두사 개수를 구하고 
 * 다시 돌면서 최대 개수가 존재하는 각 범위를 구하고 그안에서 첫번째값 두번째값을 계산한다. 이유는 같은 범위 내에서만 쌍 출력이
 * 가능 하기 때문이다. 그리고 원래 순서 값을 같이 넣어서 여러개 있을때는 그 순서의 idx로 우선순위를 계산한다. 
 */
const solution = () => {
    const N = Number(input[0]);
    const list = new Array(N);
    for (let i = 1; i <= N; i++) {
        list[i - 1] = [input[i], i - 1];
    }
    list.sort((a, b) => {
        return a[0].localeCompare(b[0]);
    });

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
