const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

function move(print, fromY, fromX, toY, toX) {
    while (fromY > toY) {
        fromY--;
        print += "U";
    }
    while (fromY < toY) {
        fromY++;
        print += "D";
    }
    while (fromX > toX) {
        fromX--;
        print += "L";
    }
    while (fromX < toX) {
        fromX++;
        print += "R";
    }
    return print;
}

const solution = () => {
    const [n, m, s] = input[0].split(" ").map(Number);
    const map = new Array(n);
    const mapDic = {};
    for (let i = 1; i <= n; i++) {
        map[i - 1] = input[i].split("");
        map[i - 1].forEach((v, index) => {
            mapDic[v] = mapDic[v] ?? [];
            mapDic[v].push([i - 1, index]);
        });
    }
    // console.log(mapDic);
    const id = input[n + 1].split("");
    const idDic = {};
    id.forEach((v) => (idDic[v] = (idDic[v] ?? 0) + 1));

    let c = Number.MAX_VALUE;
    for (const ch in idDic) {
        c = Math.min(c, Math.floor((mapDic[ch]?.length ?? 0) / idDic[ch]));
    }

    let print = "";
    let nowY = 0,
        nowX = 0;
    for (let i = 0; i < c; i++) {
        for (const v of id) {
            const [toY, toX] = mapDic[v].shift();
            print = move(print, nowY, nowX, toY, toX);
            print += "P";
            nowY = toY;
            nowX = toX;
        }
    }
    print = move(print, nowY, nowX, n - 1, m - 1);

    console.log(c + " " + print.length);
    console.log(print);
};

solution();
