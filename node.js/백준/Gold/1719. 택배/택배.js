const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = 2e5;

function find_set(parents, a) {
    if (parents[a] === a) {
        return a;
    }
    return parents[a] = find_set(parents, parents[a]);
}

function union_set(parents, a, b) {
    let rootB = find_set(parents, b);
    if (a !== rootB) {
        parents[a] = rootB;
    }
}

const solution = () => {
    const [n, m] = input[0].split(" ").map(Number);
    const adjMatrix = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(INF));
    const result = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; i++) {
        const [u, v, l] = input[i].split(" ").map(Number);
        adjMatrix[u][v] = l;
        adjMatrix[v][u] = l;
        result[u][v] = v;
        result[v][u] = u;
    }
    // console.log(adjMatrix.join("\n"));

    // for (let i = 1; i <= n; i++) {
    //     for (let j = 1; j <= n; j++) {
    //         if (i === j) continue;
    //         result[i][j] = j;
    //     }
    // }

    // console.log(" ")
    // console.log(result.join("\n"));
    for (let k = 1; k <= n; k++) {
        for (let i = 1; i <= n; i++) {
            if (i === k) continue;
            for (let j = 1; j <= n; j++) {
                if (j === k || j === i) continue;
                if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                    adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    // union_set(result[i], j, k);
                    result[i][j] = result[i][k];
                }
            }
        }
    }

    // console.log(" ")
    // console.log(adjMatrix.join("\n"));
    // console.log(" ")
    for (let i = 1; i <= n; i++) {
        let print = [];
        for (let j = 1; j <= n; j++) {
            if (result[i][j] === 0) print.push('-');
            else print.push(result[i][j]);
        }
        console.log(print.join(" "));
    }
};
solution();