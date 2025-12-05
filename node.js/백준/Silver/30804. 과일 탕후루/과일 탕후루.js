const fs = require("fs");
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');


const solution = () => {
    const N = Number(input[0]);
    const array = input[1].split(' ').map(Number);
    const kinds = {};
    for (let i = 1; i <= 9; i++) {
        kinds[i] = 0;
    }
    const set = new Set();
    let start = 0;
    let result = 1;
    array.forEach((num, index) => {
        set.add(num);
        kinds[num]++;
        if (set.size > 2) {
            while (start < index) {
                kinds[array[start]]--;
                if (kinds[array[start]] === 0) {
                    set.delete(array[start]);
                    start++;
                    break;
                }
                start++;
            }
        }
        result = Math.max(result, index - start + 1);
    })


    console.log(result);
}
solution();