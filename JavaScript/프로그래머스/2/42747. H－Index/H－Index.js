function solution(citations) {
    var answer = 0;
    citations.sort((a, b) => b - a);
    const n = citations.length;
    let point = 0;
    while(citations[point] > n){
        point++;
    }
    for(let h = n; h >= 0; h--){
        if(h === citations[point]){
            point++;
        }
        const rest = n - point;
         console.log(h, point, rest);
        if(point >= h && rest <= h){
            answer = h;
            break;
        }
    }
    
    return answer;
}
    
    // 7 5 4 2 1
// 6 5 3 1 0