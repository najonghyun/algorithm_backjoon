const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

function calculate(points, minX, maxX, minY, maxY) {
    let count = 0;
    for (const [x, y] of points) {
        if (y >= minY && y <= maxY && x >= minX && x <= maxX) {
            count++;
        }
    }
    return count;
}

const solution = () => {
    const [n, m, l, k] = input[0].split(" ").map(Number);
    const points = new Array(k);
    for (let i = 1; i <= k; i++) {
        points[i - 1] = input[i].split(" ").map(Number);
    }
    // console.log(points);


    let result = 0;
    for (let i = 0; i < k; i++) {
        for (let j = 0; j < k; j++) {
            const minX = points[i][0];
            const minY = points[j][1];
            const maxX = minX + l;
            const maxY = minY + l;
            const count = calculate(points, minX, maxX, minY, maxY);
            // console.log(y, x, count + 1);
            result = Math.max(result, count);
        }
    }

    console.log(points.length - result);
};
solution();
