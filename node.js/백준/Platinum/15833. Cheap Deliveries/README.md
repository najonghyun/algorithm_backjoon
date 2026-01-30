# [Platinum IV] Cheap Deliveries - 15833 

[문제 링크](https://www.acmicpc.net/problem/15833) 

### 성능 요약

메모리: 49328 KB, 시간: 1008 ms

### 분류

다이나믹 프로그래밍, 그래프 이론, 비트마스킹, 최단 경로, 데이크스트라, 비트필드를 이용한 다이나믹 프로그래밍, 외판원 순회 문제, 불변량 찾기

### 제출 일자

2026년 1월 31일 01:22:31

### 문제 설명

<p>Abu runs a delivery service where he deliver items from one city to another. As with any business, Abu wants to decrease his cost as much as possible. The further he travel, the more fuel he will use.</p>

<p>In any particular day, Abu have k items to deliver. Each item needs to be delivered from a start city to a destination city. Each city is represented by an integer. Because of his business policies, he can only deliver one item at a time. However, he can deliver the items in any order that he wants, as long as he deliver all of them. So, everyday he starts at an item's start city and deliver the item to its destination city. Then, he goes to the next items's start city and deliver the item to the its destination city. And, he does this until he does not have any item left to deliver.</p>

<p>From experimentation, Abu notices that the distance he travels change if he change the order of his delivery. He thought, he can save a lot of money if he knows the best delivery order. He knows that you are very good at solving this kind of problem. So he wants you to solve it for him. Given a list of cities, a list of roads between the cities (and the road's length), and a description of deliveries he must do, determine what is the minimum total travel distance, given that he execute his delivery in the most efficient order.</p>

<p>Every road is bidirectional and there can be more than one road between two cities. Abu can use any road as many time as he wants.</p>

### 입력 

 <p>The first line consists of two integer n, m, k (2 ≤ n, m ≤ 10<sup>4</sup>), (1 ≤ k ≤ 18) which is the number of cities, the number of roads and the number of items respectively.</p>

<p>The next m line each consist of 3 integers, u<sub>i</sub>, v<sub>i</sub>, l<sub>i</sub> (1 ≤ u<sub>i</sub>, v<sub>i</sub> ≤ 10<sup>4</sup>), (1 ≤ l<sub>i</sub> ≤ 10<sup>6</sup>), which denotes that a road exists from city u<sub>i</sub> to v<sub>i</sub> with length l<sub>i</sub>.</p>

<p>The next k line each consist of 2 integers, f<sub>i</sub>, d<sub>i</sub> (1 ≤ f<sub>i</sub>, d<sub>i</sub> ≤ 10<sup>4</sup>) which denotes that the ith item is from city f<sub>i</sub> and its destination is city d<sub>i</sub>.</p>

### 출력 

 <p>A single integer, which is the minimum total travel distance given that Abu deliver all items optimally, or -1 if its is impossible for him to deliver all items.</p>

