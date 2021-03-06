//============================================================================
// Name        : Desafio1.cpp
// Author      : Juarez A.S.F.
// Version     : final
// Description : Estudando para a maratona de promagação UnB 2°/2013
//============================================================================

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int process(int n)
{
	if(n != 1)
	{
		if(n%2 == 0)
			return process(n/2) + 1;
		else
			return process(3*n + 1) + 1;
	}
	else
		return 1;
}

int main() {

	int inf = 1;
	int sup = 1;


	while(1)
	{
		cin >> inf;
		if(inf < 1)
			break;
		cin >> sup;



		vector<int> data;

		for(int i = inf; i <= sup; i++)
			data.push_back(process(i));

		sort(data.begin(), data.end());

		cout << inf << "\t" << sup << "\t" << data[data.size() - 1] <<endl;

		data.clear();

	}
	return 0;
}
