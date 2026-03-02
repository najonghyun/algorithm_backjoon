const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;

const solution = () => {
    const [N, M, B] = input[0].split(" ").map(Number);
    const map = new Array(N);
    for (let i = 1; i <= N; i++) {
        map[i - 1] = input[i].split(" ").map(Number);
    }

    let result = [INF, 0];
    for (let h = 0; h <= 256; h++) {
        let bag = B;
        let time = 0;
        for (let i = 0; i < N; i++) {
            for (let j = 0; j < M; j++) {
                if (h > map[i][j]) {
                    const diff = h - map[i][j];
                    bag -= diff;
                    time += 1 * diff;
                } else if (h < map[i][j]) {
                    const diff = map[i][j] - h;
                    bag += diff;
                    time += 2 * diff;
                }
            }
        }
        if (bag >= 0) {
            if (result[0] >= time) {
                result = [time, h];
            }
        }
    }
    console.log(result.join(" "));
};
solution();
