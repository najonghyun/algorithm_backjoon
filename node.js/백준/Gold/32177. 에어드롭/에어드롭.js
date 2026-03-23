const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : N의 범위를 봤을 때 그냥 인접리스트 만들어서 되는 것만 넣고 이제 푸앙이부터 시작해서
 * dfs로 다 한번씩 방문하면 해결된다. 정보가 푸앙이 포함 총 N+1개 인것만 주의하자
 */
function calculate(arr1, arr2) {
    const diffX = arr1[0] - arr2[0];
    const diffY = arr1[1] - arr2[1];
    return Math.sqrt(diffX * diffX + diffY * diffY);
}

function dfs(info, adjList, visited, now, result) {
    for (const next of adjList[now] ?? []) {
        if (visited[next]) continue;
        if (info[next][3] === 1) {
            result.push(next);
        }
        visited[next] = true;
        dfs(info, adjList, visited, next, result);
    }
}

const solution = () => {
    const [N, K, T] = input[0].split(" ").map(Number);
    const info = new Array(N + 1);
    for (let i = 1; i <= N + 1; i++) {
        info[i - 1] = input[i].split(" ").map(Number);
    }

    const adjList = {};
    for (let i = 0; i <= N; i++) {
        for (let j = i + 1; j <= N; j++) {
            const diffT = Math.abs(info[i][2] - info[j][2]);
            if (calculate(info[i], info[j]) <= K && diffT <= T) {
                if (!adjList[i]) adjList[i] = [];
                if (!adjList[j]) adjList[j] = [];
                adjList[i].push(j);
                adjList[j].push(i)
            }
        }
    }

    const visited = new Array(N + 1).fill(false);
    visited[0] = true;
    const result = [];
    dfs(info, adjList, visited, 0, result);
    result.sort((a, b) => a - b);
    console.log(result.length > 0 ? result.join(" ") : 0);

};

solution();
