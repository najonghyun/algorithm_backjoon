const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : dfs를 사용하면 시간초과가 난다. 그래서 자세히 보면 처음 바로 아래 지뢰의 유무로 2번만 돌리면 
 * 답을 구할 수 있다. 왜냐하면 그 후에는 그 다음인 i+1에 해당하는 것만 보면 되기 때문이다. 
 * 그래서 그리디로 o(N) 만에 해결할 수 있다.
 * + 보는 중간에라도 더 많아진다면 그것은 안되므로 바로 빠져나와야 한다.
 */
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
                result = Math.max(result, count);
            }
        }
        console.log(result);
    }
};
solution();
