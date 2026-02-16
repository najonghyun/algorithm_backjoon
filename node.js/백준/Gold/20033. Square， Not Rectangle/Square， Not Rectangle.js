const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
const INF = Number.MAX_VALUE;
class Stack {
    constructor() {
        this.list = [];
    }
    size() {
        return this.list.length;
    }
    push(value) {
        this.list.push(value);
    }
    peek() {
        if (this.size() === 0) return -1;
        return this.list[this.size() - 1];
    }
    pop() {
        if (this.size() === 0) return -1;
        const root = this.list[this.size() - 1];
        this.list.pop();
        return root;
    }
}

const solution = () => {
    const n = Number(input[0]);
    const map = input[1].split(" ").map(Number);
    const stack = new Stack();
    let result = 0;
    for (let i = 0; i < n; i++) {
        let width = 0;
        if (map[i] < stack.peek()[0]) {
            let height = INF;
            while (stack.peek()[0] >= map[i]) {
                const [top, w] = stack.pop();
                width += w;
                height = Math.min(height, top);
                const count = Math.min(height, width);
                result = Math.max(result, count);
            }
        }
        stack.push([map[i], width + 1]);
        // console.log(stack.list);
    }

    let width = 0, height = INF;
    while (stack.size() > 0) {
        const [top, w] = stack.pop();
        width += w;
        height = Math.min(height, top);
        const count = Math.min(height, width);
        result = Math.max(result, count);
    }


    console.log(result);

};
solution();
