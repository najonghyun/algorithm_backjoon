const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);
/**
 * 설명 : x좌표는 그냥 합치면 되지만 이 문제의 핵심은 y좌표에 달려있다. y좌표에서 양쪽을 균등하게 나눠야만 
 * y값 역시 최소로 구할 수 있다. 그래서 y좌표의 기준값을 기준으로 양쪽에 최소힙과 최대힙 값을 넣어주고 그것을 기준으로 합을 계산했다.
 */
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
            let p = Math.floor((i - 1) / 2);
            if (this.heap[i] > this.heap[p]) break;
            [this.heap[i], this.heap[p]] = [this.heap[p], this.heap[i]];
            i = p;
        }
    }
    _down() {
        let i = 0;
        let n = this.size();
        while (true) {
            let left = i * 2 + 1;
            let right = i * 2 + 2;
            let min = i;
            if (left < n && this.heap[left] < this.heap[min]) min = left;
            if (right < n && this.heap[right] < this.heap[min]) min = right;
            if (min === i) break;
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
            let p = Math.floor((i - 1) / 2);
            if (this.heap[i] < this.heap[p]) break;
            [this.heap[i], this.heap[p]] = [this.heap[p], this.heap[i]];
            i = p;
        }
    }
    _down() {
        let i = 0;
        let n = this.size();
        while (true) {
            let left = i * 2 + 1;
            let right = i * 2 + 2;
            let min = i;
            if (left < n && this.heap[left] > this.heap[min]) min = left;
            if (right < n && this.heap[right] > this.heap[min]) min = right;
            if (min === i) break;
            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
}
const solution = () => {
    const N = Number(input[0]);
    const list = new Array(N);
    for (let i = 1; i <= N; i++) {
        list[i - 1] = input[i].split(" ").map(Number);
    }

    const leftY = new MaxPriorityQueue();
    const rightY = new MinPriorityQueue();

    let xSum = 0, yLeftSum = 0, yRightSum = 0;
    for (let i = 0; i < N; i++) {
        const [x, y] = list[i];
        if (leftY.size() === 0 || leftY.heap[0] >= y) {
            leftY.push(y);
            yLeftSum += y;
        } else {
            rightY.push(y);
            yRightSum += y;
        }
        if (leftY.size() < rightY.size()) {
            const root = rightY.pop();
            leftY.push(root);
            yLeftSum += root;
            yRightSum -= root;
        } else if (leftY.size() > rightY.size() + 1) {
            const root = leftY.pop();
            rightY.push(root);
            yLeftSum -= root;
            yRightSum += root;
        }
        const stdY = leftY.heap[0];
        const ySum = (leftY.size() * stdY - yLeftSum) + (yRightSum - rightY.size() * stdY);
        xSum += Math.abs(x);
        console.log(stdY, xSum + ySum);
    }
};
solution();