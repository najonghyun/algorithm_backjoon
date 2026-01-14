class PriorityQueue {
    constructor() {
        this.heap = [];
    }
    size(){
        return this.heap.length;
    }
    push(value) {
        this.heap.push(value);
        this._up();
    }
    pop() {
        if(this.size() === 1) return this.heap.pop();
        const root = this.heap[0];
        this.heap[0] = this.heap.pop();
        this._down();
        return root;
    }
    _up(){
        let i = this.size() - 1;
        while(i > 0){
            const p = Math.floor((i - 1) / 2);
            if(this.heap[p] <= this.heap[i]) break;
            [this.heap[p], this.heap[i]] = [this.heap[i], this.heap[p]];
            i = p;
        }
    }
    _down(){
        let i = 0;
        const n = this.size();
        while(true){
            let min = i;
            let left = i * 2 + 1;
            let right = i * 2 + 2;
            if(left < n && this.heap[min] > this.heap[left]) min = left;
            if(right < n && this.heap[min] > this.heap[right]) min = right;
            if(i == min) break;
            
            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
}

function solution(scoville, K) {
    let answer = -1;
    const pq = new PriorityQueue();
    scoville.forEach(item => pq.push(item));

    let count = 0;
    if(pq.heap[0] >= K){
        answer = count;
    }else{
        while(pq.size() > 1) {
            count++;
            const first = pq.pop();
            const second = pq.pop();
            const mix = first + (second * 2);
            pq.push(mix);
            if(pq.heap[0] >= K) {
                answer = count;
                break;
            }
        }
    }
    return answer;
}