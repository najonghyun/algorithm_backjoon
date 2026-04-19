function solution(participant, completion) {
    const length = completion.length;
    participant.sort();
    completion.sort();
    var answer = participant[length];
    for(let i=0; i<length; i++){
        if(participant[i] !== completion[i]){
            answer = participant[i];
            break;
        }
    }
    
    return answer;
}