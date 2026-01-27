const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function upperBound(list, target) {
    let start = 0;
    let end = list.length - 1;
    while (start < end) {
        let mid = Math.floor((start + end) / 2);
        if (list[mid] > target) {
            end = mid;
        } else {
            start = mid + 1;
        }
    }
    return start;
}

const solution = () => {
    const [n, q] = input[0].split(" ").map(Number);
    const trees = input[1].split(" ").map(Number);
    const spots = input.slice(2).map(Number);
    trees.sort((a, b) => a - b);
    const treeSum = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        treeSum[i] = treeSum[i - 1] + trees[i - 1];
    }
    // console.log(trees);
    // console.log(spots);
    // console.log(treeSum);

    const result = [];
    for (const num of spots) {
        const point = upperBound(trees, num);
        const leftCount = point;
        let left = Math.abs((leftCount * num) - treeSum[point]);
        const rightCount = n - point;
        let right = Math.abs((rightCount * num) - (treeSum[n] - treeSum[point]));
        result.push(left + right);
    }

    console.log(result.join("\n"));
};

solution();