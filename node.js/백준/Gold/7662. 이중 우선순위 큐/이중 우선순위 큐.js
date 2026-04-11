const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const data = fs.readFileSync(filePath, "utf8");
let ptr = 0;

const nextToken = () => {
    while (ptr < data.length && data.charCodeAt(ptr) <= 32) ptr++;
    let start = ptr;
    while (ptr < data.length && data.charCodeAt(ptr) > 32) ptr++;
    return data.slice(start, ptr);
};

class MinPriorityQueue {
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
            if (this.heap[p] <= this.heap[i]) break;
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
            if (left < n && this.heap[min] > this.heap[left]) min = left;
            if (right < n && this.heap[min] > this.heap[right]) min = right;
            if (i == min) break;

            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
}
class MaxPriorityQueue {
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
    let index = 0;
    const T = Number(nextToken());
    const result = new Array(T);
    for (let t = 0; t < T; t++) {
        const pqMax = new MaxPriorityQueue();
        const pqMin = new MinPriorityQueue();
        const set = {};
        let size = 0;
        const K = Number(nextToken());
        for (let k = 0; k < K; k++) {
            const command = nextToken();
            const num = Number(nextToken());
            if (command === 'I') {
                pqMax.push(num);
                pqMin.push(num);
                if (!set[num]) set[num] = 0;
                set[num]++;
                size++;
            } else if (command === 'D') {
                if (size === 0) continue;
                if (num === 1) {
                    while (pqMax.size() > 0) {
                        const max = pqMax.pop();
                        if (set[max] && set[max] > 0) {
                            set[max]--;
                            size--;
                            break;
                        }
                    }
                } else if (num === -1) {
                    while (pqMin.size() > 0) {
                        const min = pqMin.pop();
                        if (set[min] && set[min] > 0) {
                            set[min]--;
                            size--;
                            break;
                        }
                    }
                }
            }
        }
        if (size === 0) result[t] = "EMPTY";
        else {
            let maxNum = -1;
            while (pqMax.size() > 0) {
                const temp = pqMax.pop();
                if (set[temp] && set[temp] > 0) {
                    maxNum = temp;
                    break;
                }
            }
            let minNum = -1;
            while (pqMin.size() > 0) {
                const temp = pqMin.pop();
                if (set[temp] && set[temp] > 0) {
                    minNum = temp;
                    break;
                }
            }
            result[t] = `${maxNum} ${minNum}`
        }
    }
    console.log(result.join("\n"));
};

solution();
