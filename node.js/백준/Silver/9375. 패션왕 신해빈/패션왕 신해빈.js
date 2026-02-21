const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);


const solution = () => {
    let index = 0;
    const result = [];
    const T = Number(input[index++]);
    for (let t = 0; t < T; t++) {
        const n = Number(input[index++]);
        const list = {};
        for (let i = 0; i < n; i++) {
            const [title, type] = input[index++].split(" ");
            if (!list[type]) list[type] = [];
            list[type].push(title);
        }
        // console.log(list);
        const keys = Object.keys(list);
        const size = keys.length;
        // console.log(Object.keys(list));
        if (size === 0) {
            result.push(0);
        } else if (size === 1) {
            result.push(list[keys[0]].length);
        } else {
            let count = 1;
            for (const key of keys) {
                count *= list[key].length + 1;
            }
            result.push(count - 1);
        }
        // console.log(result);
    }
    console.log(result.join("\n"))
};
solution();
