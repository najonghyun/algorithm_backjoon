const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split("\n");

// 재귀
function cross(n, woods, currX, jump, i) {
    if (i === n) {
        answer = Math.max(answer, currX);
        return;
    }
    const [l, r] = woods[i];
    if (currX >= l) {
        if (currX >= r) {
            cross(n, woods, currX, jump, i + 1);
            return;
        }
        const diff = currX - l;
        const plus = r - l - diff;
        cross(n, woods, r, jump + plus, i + 1);
    } else {
        const diff = l - currX;
        if (jump >= diff) {
            // 점프 o
            cross(n, woods, r, r - l, i + 1);
            let nextI = i + 1;
            while (nextI < n) {
                const [nextL, nextR] = woods[nextI];
                const diff = nextL - currX;
                if (jump < diff) break;
                cross(n, woods, nextR, nextR - nextL, nextI + 1);
                nextI++;
            }
        } else {
            // 점프 x
            answer = Math.max(answer, currX);
            return;
        }
    }
}

function bfs(n, woods) {
    let max = 0;
    const q = [];
    q.push({ x: 0, jump: 0, i: 0 });
    while (q.length > 0) {
        const { x: currX, jump, i } = q.shift();
        if (i === n) {
            max = Math.max(max, currX);
            continue;
        }
        const [l, r] = woods[i];
        if (currX >= l) {
            if (currX >= r) {
                q.push({ x: currX, jump: jump, i: i + 1 });
            } else {
                const diff = currX - l;
                const plus = r - l - diff;
                q.push({ x: r, jump: jump + plus, i: i + 1 });
            }
        } else {
            const diff = l - currX;
            if (jump >= diff) {
                // 점프 o
                q.push({ x: r, jump: r - l, i: i + 1 });
                let nextI = i + 1;
                while (nextI < n) {
                    const [nextL, nextR] = woods[nextI];
                    const diff = nextL - currX;
                    if (jump < diff) break;
                    q.push({ x: nextR, jump: nextR - nextL, i: nextI + 1 });
                    nextI++;
                }
            } else {
                // 점프 x
                max = Math.max(max, currX);
            }
        }
    }
    return max;
}

const solution = () => {
    let answer = 0;
    const n = Number(input[0]);
    const woods = new Array(n);
    for (let i = 1; i <= n; i++) {
        woods[i - 1] = input[i].split(" ").map(Number);
    }
    woods.sort((a, b) => {
        if (a[0] === b[0]) return a[1] - b[1];
        return a[0] - b[0];
    })
    // cross(n, woods, 0, 0, 0); 이거는 answer 전역변수로!
    answer = bfs(n, woods);

    console.log(answer);
}

solution();