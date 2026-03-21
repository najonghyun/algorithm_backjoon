const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;

function dijkstra(n, adjList, roomTypes, roomCosts) {
    const cost = new Array(n).fill(-1);
    const visited = new Array(n).fill(false);
    cost[0] = roomCosts[0];
    for (let c = 0; c < n; c++) {
        let current = -1;
        let max = -1;
        for (let i = 0; i < n; i++) {
            if (!visited[i] && cost[i] > max) {
                current = i;
                max = cost[i];
            }
        }

        if (current === -1) break;
        visited[current] = true;

        for (const next of adjList[current] ?? []) {
            const nextCost = roomCosts[next];
            if (roomTypes[next] === 'T') {
                if (max - nextCost >= 0) {
                    cost[next] = max - nextCost;
                }
            } else {
                cost[next] = Math.max(max, nextCost);
            }
        }
    }

    return cost[n - 1];
}

const solution = () => {
    let index = 0;
    const result = [];
    while (true) {
        const n = Number(input[index++]);
        if (n === 0) break;
        const adjList = {};
        const roomTypes = new Array(n);
        const roomCosts = new Array(n);
        for (let i = 0; i < n; i++) {
            const list = input[index++].split(" ");
            roomTypes[i] = list[0];
            roomCosts[i] = Number(list[1]);
            if (!adjList[i]) adjList[i] = [];
            adjList[i] = list.slice(2, list.length - 1).map((v) => Number(v - 1));
        }

        // console.log(roomTypes);
        // console.log(roomCosts);
        // console.log(adjList);

        const cost = dijkstra(n, adjList, roomTypes, roomCosts);
        result.push(cost === -1 ? "No" : "Yes");
    }

    console.log(result.join("\n"))
};

solution();
