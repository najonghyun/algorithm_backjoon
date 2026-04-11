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
        if (this.size() === 0) return null;
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
            if (i === min) break;

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
        if (this.size() === 0) return null;
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
            let max = i;
            let left = i * 2 + 1;
            let right = i * 2 + 2;

            if (left < n && this.heap[max] < this.heap[left]) max = left;
            if (right < n && this.heap[max] < this.heap[right]) max = right;
            if (i === max) break;

            [this.heap[max], this.heap[i]] = [this.heap[i], this.heap[max]];
            i = max;
        }
    }
}

const cleanMax = (pqMax, count) => {
    while (pqMax.size() > 0) {
        const top = pqMax.heap[0];
        if ((count.get(top) || 0) > 0) break;
        pqMax.pop();
    }
};

const cleanMin = (pqMin, count) => {
    while (pqMin.size() > 0) {
        const top = pqMin.heap[0];
        if ((count.get(top) || 0) > 0) break;
        pqMin.pop();
    }
};

const solution = () => {
    const T = Number(nextToken());
    const result = new Array(T);

    for (let t = 0; t < T; t++) {
        const pqMax = new MaxPriorityQueue();
        const pqMin = new MinPriorityQueue();
        const count = new Map();
        let size = 0;

        const K = Number(nextToken());

        for (let k = 0; k < K; k++) {
            const command = nextToken();
            const num = Number(nextToken());

            if (command === "I") {
                pqMax.push(num);
                pqMin.push(num);
                count.set(num, (count.get(num) || 0) + 1);
                size++;
            } else {
                if (size === 0) continue;

                if (num === 1) {
                    cleanMax(pqMax, count);
                    const max = pqMax.pop();
                    if (max !== null) {
                        count.set(max, count.get(max) - 1);
                        size--;
                    }
                } else {
                    cleanMin(pqMin, count);
                    const min = pqMin.pop();
                    if (min !== null) {
                        count.set(min, count.get(min) - 1);
                        size--;
                    }
                }

                if (size === 0) {
                    pqMax.heap = [];
                    pqMin.heap = [];
                    count.clear();
                }
            }
        }

        if (size === 0) {
            result[t] = "EMPTY";
        } else {
            cleanMax(pqMax, count);
            cleanMin(pqMin, count);
            result[t] = `${pqMax.heap[0]} ${pqMin.heap[0]}`;
        }
    }

    console.log(result.join("\n"));
};

solution();