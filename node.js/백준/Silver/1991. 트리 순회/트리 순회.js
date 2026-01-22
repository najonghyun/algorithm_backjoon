const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split(/\r?\n/);

/**
 * 설명 : 이 문제는 언뜻보면 바이너리 트리문제이지만 이미 노드와 왼쪽 오른쪽 자식이 주어졌기 때문에 객체에 저장한 후 
 * 재귀로 o(N) 만에 처리할 수 있다.
 */

const preResult = [];
const inResult = [];
const postResult = [];

function dfs(tree, node) {
    if (node === '.') return;

    preResult.push(node);
    dfs(tree, tree[node].left);
    inResult.push(node);
    dfs(tree, tree[node].right);
    postResult.push(node);
}

const solution = () => {
    const n = Number(input[0]);
    const tree = {};
    for (let i = 1; i <= n; i++) {
        const [v, l, r] = input[i].split(" ");
        tree[v] = { left: l, right: r };
    }
    dfs(tree, 'A');

    console.log(preResult.join(""));
    console.log(inResult.join(""));
    console.log(postResult.join(""));
};

solution();
