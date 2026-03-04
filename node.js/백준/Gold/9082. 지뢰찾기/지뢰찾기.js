const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

function getBombCount(n, now, visited) {
    let count = 0;
    const prev = now - 1;
    const next = now + 1;
    if (prev >= 0 && visited[prev]) count++;
    if (visited[now]) count++;
    if (next < n && visited[next]) count++;

    return count;
}

const solution = () => {
    let index = 0;
    const T = Number(input[index++]);
    for (let t = 0; t < T; t++) {
        const N = Number(input[index++]);
        const list = input[index++].split("").map(Number);
        const bombs = input[index++].split("");

        // console.log(list);
        // console.log(visited);

        let result = 0;
        for (let i = 0; i < 2; i++) {
            let count = 0;
            const visited = bombs.map(v => {
                if (v === '*') {
                    count++;
                    return true;
                }
                return false;
            });
            if (i === 0 && !visited[0]) {
                visited[0] = true;
                count++;
            }

            let possible = true;
            for (let j = 0; j < N - 1; j++) {
                const num = getBombCount(N, j, visited);
                if (num > list[j]) {
                    possible = false;
                    break;
                }
                if (num < list[j]) {
                    if (visited[j + 1]) continue;
                    visited[j + 1] = true;
                    count++;
                }
            }
            if (possible && list[N - 1] === getBombCount(N, N - 1, visited)) {
                // console.log(count);
                result = Math.max(result, count);
            }
        }
        console.log(result);
    }
};
solution();
