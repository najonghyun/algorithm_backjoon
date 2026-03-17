const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
let set;
/**
 * 설명 : 처음에는 모든 경우의 수를 다 넣어서 풀이했는데 예외가 많다.
 * 특히 마지막에 놓을 때 빙고가 2개 이상 되어버리는 경우 때문에 일일히 하는 방식으로는 풀 수 없다. 그래서 어짜피 3x3 이므로
 * 브루트포스를 사용해서 모든 가능한 수를 모두 set에 넣어서 해결했다.
 */
function isBingo(map) {
    let oWin = false,
        xWin = false;
    for (let i = 0; i < 3; i++) {
        let rowOCount = 0,
            colOCount = 0,
            rowXCount = 0,
            colXCount = 0;
        for (let j = 0; j < 3; j++) {
            if (map[i][j] === "O") rowOCount++;
            if (map[j][i] === "O") colOCount++;
            if (map[i][j] === "X") rowXCount++;
            if (map[j][i] === "X") colXCount++;
        }
        if (rowOCount === 3 || colOCount === 3) oWin = true;
        if (rowXCount === 3 || colXCount === 3) xWin = true;
    }
    let crossOCount1 = 0,
        crossXCount1 = 0,
        crossOCount2 = 0,
        crossXCount2 = 0;
    for (let i = 0; i < 3; i++) {
        if (map[i][i] === "O") crossOCount1++;
        if (map[i][i] === "X") crossXCount1++;
        if (map[i][2 - i] === "O") crossOCount2++;
        if (map[i][2 - i] === "X") crossXCount2++;
    }
    if (crossOCount1 === 3 || crossOCount2 === 3) oWin = true;
    if (crossXCount1 === 3 || crossXCount2 === 3) xWin = true;

    if (oWin || xWin) return true;
    return false;
}

function perm(map, cnt, turn) {
    let result = "";
    for (let i = 0; i < 3; i++) result += map[i].join("");
    set.add(result);

    if (cnt === 9 || isBingo(map)) {
        return;
    }
    const mark = turn % 2 === 0 ? "X" : "O";
    for (let y = 0; y < 3; y++) {
        for (let x = 0; x < 3; x++) {
            if (map[y][x] !== ".") continue;
            map[y][x] = mark;
            perm(map, cnt + 1, turn + 1);
            map[y][x] = ".";
        }
    }
}

const solution = () => {
    let index = 0;
    const result = [];
    const N = Number(input[index++]);
    const print = Array.from({ length: 3 }, () => new Array(3).fill("."));
    set = new Set();
    perm(print, 0, 0);
    for (let t = 0; t < N; t++) {
        let target = "";
        for (let i = 0; i < 3; i++) {
            target += input[index++];
        }
        index++;

        if (set.has(target)) {
            result.push("yes");
        } else {
            result.push("no");
        }
    }
    console.log(result.join("\n"));
};
solution();
