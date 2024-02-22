#include <iostream>
#include<stdbool.h>
using namespace std;

void Eratos(long long m, long long n) {
	bool* state = new bool[n + 1];
	if (n == 1) {
		return;
	}
	for (int i = 2; i <= n; i++) {
		state[i] = true;
	}

	for (long long i = 2; i <= n; i++) {
		if (state[i]) {
			if (i >= m) {
				cout << i << '\n';
			}
			for (long long j = i * i; j <= n; j += i) {
				state[j] = false;
				
			}
		}
	}
}

int main() {
	int M, N;

	cin >> M >> N;
	
	Eratos(M, N);

	return 0;

}
