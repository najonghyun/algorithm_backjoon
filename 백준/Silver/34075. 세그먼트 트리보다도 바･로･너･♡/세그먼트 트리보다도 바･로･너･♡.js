const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;

function findEasy(map, myLevel) {
    const list = [];
    for (const [name, level] of map) {
        const diff = Math.abs(Number(level) - Number(myLevel));
        list.push([diff, name]);
    }
    list.sort((a, b) => {
        if (a[0] === b[0]) return a[1].localeCompare(b[1]);
        return a[0] - b[0];
    });
    return [list[0][1], list[1][1]];
}

const solution = () => {
    let index = 0;
    const n = Number(input[index++]);
    const map = new Map();
    for (let i = 0; i < n; i++) {
        const [name, level] = input[index++].split(" ");
        map.set(name, level);
    }
    const m = Number(input[index++]);
    const user = new Map();
    for (let i = 0; i < m; i++) {
        const [name, level] = input[index++].split(" ");
        user.set(name, level);
    }

    const q = Number(input[index++]);
    let nowUser = "";
    for (let i = 0; i < q; i++) {
        const question = input[index++].split(" ");
        if (question[2] === "chan!") {
            nowUser = question[0];
            console.log("hai!");
        } else {
            const [min1, min2] = findEasy(map, user.get(nowUser));
            console.log(min2 + " yori mo " + min1);
        }
    }
};
solution();
