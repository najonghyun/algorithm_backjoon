const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const dir = { S: [0, 1], J: [0, -1], I: [1, 0], Z: [-1, 0] };

function lower_bound(N, list, target, isX) {
    const num = isX ? 0 : 1;
    let start = 0;
    let end = N;
    while (start < end) {
        const mid = Math.floor((start + end) / 2);
        if (list[mid][num] >= target) {
            end = mid;
        } else {
            start = mid + 1;
        }
    }
    return start;
}
function upper_bound(N, list, target, isX) {
    const num = isX ? 0 : 1;
    let start = 0;
    let end = N;
    while (start < end) {
        const mid = Math.floor((start + end) / 2);
        if (list[mid][num] > target) {
            end = mid;
        } else {
            start = mid + 1;
        }
    }
    return start;
}
const solution = () => {
    let index = 0;
    const [N, M] = input[index++].split(" ").map(Number);
    const ySortList = new Array(N);
    const xSortList = new Array(N);
    let sum = 0;
    for (let i = 0; i < N; i++) {
        const [x, y] = input[index++].split(" ").map(Number);
        ySortList[i] = [x, y];
        xSortList[i] = [x, y];
        sum += Math.abs(x) + Math.abs(y);
    }
    ySortList.sort((a, b) => {
        if (a[1] === b[1]) return a[0] - b[0];
        return a[1] - b[1];
    });
    xSortList.sort((a, b) => {
        if (a[0] === b[0]) return a[1] - b[1];
        return a[0] - b[0];
    });
    // console.log(xSortList);
    // console.log(ySortList);
    // console.log(sum);

    let [nowX, nowY] = [0, 0];
    const command = input[index++].split("");
    const result = new Array(M);
    for (let c = 0; c < M; c++) {
        if (command[c] === "I") {
            const positionX = upper_bound(N, xSortList, nowX, true);
            const temp = 1 * positionX - 1 * (N - positionX);
            sum += temp;
            nowX += dir[command[c]][0];
        } else if (command[c] === "Z") {
            const positionX = lower_bound(N, xSortList, nowX, true);
            const temp = 1 * positionX - 1 * (N - positionX);
            sum -= temp;
            nowX += dir[command[c]][0];
        } else if (command[c] === "S") {
            const positionY = upper_bound(N, ySortList, nowY, false);
            const temp = 1 * positionY - 1 * (N - positionY);
            sum += temp;
            nowY += dir[command[c]][1];
        } else if (command[c] === "J") {
            const positionY = lower_bound(N, ySortList, nowY, false);
            const temp = 1 * positionY - 1 * (N - positionY);
            sum -= temp;
            nowY += dir[command[c]][1];
        }
        // console.log(nowX, nowY);
        // console.log(sum);
        result[c] = sum;
    }
    console.log(result.join("\n"));
};
solution();
