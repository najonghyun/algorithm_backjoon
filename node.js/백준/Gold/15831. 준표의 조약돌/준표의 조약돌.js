const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

function colorChange(plus, item, black, white) {
    if (item === 'B') {
        black++;
    }
}

function findWalk(n, b, w, road) {
    let maxLength = 0;
    const colorCount = { 'W': 0, 'B': 0 };
    let start = 0;
    let end = 0;
    while (end < n) {
        colorCount[road[end]]++;
        if (road[end] === 'B') {
            while (colorCount.B > b) {
                colorCount[road[start]]--;
                start++;
            }
        }
        if (colorCount.W >= w) {
            maxLength = Math.max(maxLength, end - start + 1);
        }
        end++;
        // console.log(colorCount);
        // console.log(start, end);
    }
    return maxLength;
}

const solution = () => {
    let answer = 0;
    const [n, b, w] = input[0].split(" ").map(Number);
    const road = input[1].split("");
    // console.log(n, b, w);
    // console.log(road.join(""));
    answer = findWalk(n, b, w, road);
    console.log(answer);
};

solution();