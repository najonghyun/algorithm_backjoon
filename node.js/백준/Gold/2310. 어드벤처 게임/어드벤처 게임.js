const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 이문제는 인접리스트를 만들고 cost라는 배열을 만들어서 각 노드에 최대 비용을 넣어주는 느낌으로 풀었다.
 * 다익스트라와는 다르게 최대 소지비용을 넣었고, 만족할 때만 넣었다.
 * 이게 다익스트라 느낌으로 푼거지 dfs로 푸는게 정석인 것 같다.
 */
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

        const cost = dijkstra(n, adjList, roomTypes, roomCosts);
        result.push(cost === -1 ? "No" : "Yes");
    }

    console.log(result.join("\n"))
};

solution();
