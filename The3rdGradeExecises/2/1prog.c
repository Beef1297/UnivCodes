#include<stdio.h>
#include<stdlib.h>
#define N 100000

int fib(int n){
  int m[N] = {0,1};
  int i;
  for(i=2;i<=n;++i){
    m[i] = m[i-1]+m[i-2];
    m[i] = m[i] % 2017;
  }
  
  return m[n];
}
  
 
void main(void){
  int n;
  scanf("%d",&n);
  n = fib(n);
  printf("%d\n",n);
  return ;
}
