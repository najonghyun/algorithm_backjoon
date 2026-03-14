const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : 우선순위 큐 사용하면 된다.
 */
class PriorityQueue {
    constructor() {
        this.heap = [];
    }
    size() {
        return this.heap.length;
    }
    push(value) {
        this.heap.push(value);
        this._up();
    }
    pop() {
        if (this.size() === 1) return this.heap.pop();
        const root = this.heap[0];
        this.heap[0] = this.heap.pop();
        this._down();
        return root;
    }
    _up() {
        let i = this.size() - 1;
        while (i > 0) {
            const p = Math.floor((i - 1) / 2);
            if (this.heap[p] >= this.heap[i]) break;
            [this.heap[p], this.heap[i]] = [this.heap[i], this.heap[p]];
            i = p;
        }
    }
    _down() {
        let i = 0;
        const n = this.size();
        while (true) {
            let min = i;
            let left = i * 2 + 1;
            let right = i * 2 + 2;
            if (left < n && this.heap[min] < this.heap[left]) min = left;
            if (right < n && this.heap[min] < this.heap[right]) min = right;
            if (i == min) break;

            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
}

const solution = () => {
    const N = Number(input[0]);
    const pq = new PriorityQueue();
    const result = [];
    for (let i = 1; i <= N; i++) {
        const num = Number(input[i]);
        if (num === 0) {
            if (pq.size() === 0) result.push(0);
            else result.push(pq.pop());
        } else {
            pq.push(num);
        }
    }
    console.log(result.join("\n"));
};

solution();
