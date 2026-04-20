/**
* 설명 : 우선순위 큐에 담아서 사용하고 이때 시간은 while문으로 한 작업씩 관리하고 
* 이때 큐에 아무것도 없을때는 시간 뛰기를 해야한다.
*/

class PriorityQueue{
    constructor(){
        this.heap = [];
    }
    size(){
        return this.heap.length;
    }
    push(value){
        this.heap.push(value);
        this._up();
    }
    pop(){
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
            if(this.heap[p][1] < this.heap[i][1]) break;
            [this.heap[p], this.heap[i]] = [this.heap[i], this.heap[p]];
            i = p;
        }
    }
    _down(){
        const n = this.size() - 1;
        let i = 0;
        while(true){
            const left = i * 2 + 1;
            const right = i * 2 + 2;
            let min = i;
            if(left <= n && this.heap[min][1] > this.heap[left][1]) min = left;
            if(right <= n && this.heap[min][1] > this.heap[right][1]) min = right;
            if(i === min) break;
            [this.heap[min], this.heap[i]] = [this.heap[i], this.heap[min]];
            i = min;
        }
    }
    
}

function solution(jobs) {
    const n = jobs.length;
    const result = [];
    var answer = 0;
    const pq = new PriorityQueue();
    jobs.sort((a, b) => a[0] - b[0]);
    
    let t = 0, index = 0, count = 0;
    while(count < n){
        while(index < n && jobs[index][0] <= t){
            pq.push(jobs[index++]);
        }
        if(pq.size() === 0){
            t = jobs[index][0];
            continue;
        }
        const [nowS, nowL] = pq.pop();
        result.push(t + nowL - nowS);
        t += nowL;
        count++;
    }
    
    let sum = 0;
    for(const num of result){
        sum += num;
    }
    answer = Math.floor(sum / n)

    return answer;
}