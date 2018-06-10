#include<stdio.h>
#include<stdlib.h>
#define N 1000
int comb(int n,int k){
  int i,j;
  int c[N][N];
  for(i =0;i<=N;++i){
    c[i][0] = 1;
    c[i][i] = 1;
  }
  for(i = 2;i <= n;++i){
    for(j = 1;j <= k;++j){
      if(j > i){
	break;
      }
      c[i][j] = c[i-1][j]+c[i-1][j-1];
      c[i][j] %= 2017;
    }
  }
  return c[n][k];
}

int main(void){
  int n,k,c;
  scanf("%d %d",&n,&k);
  c = comb(n,k);
  printf("%d\n",c);
  return 0;
}
