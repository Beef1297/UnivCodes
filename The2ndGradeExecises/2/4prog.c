#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define N 1000

void ptr(char *str,int n){
  int i,j,s;
  for(i = 0;i < n;i++){
    s = 0;
    for(j = 1;j <= i+1;j++){
      if((i+1) % j == 0)s++;
    }
    if(s == 2)printf("%c",str[i]);
  }
  return;
}

int main(void){
  char str[N];
  int n=0;
  fgets(str,N,stdin);
  for(;str[n]!='\n';)n++;
  ptr(str,n);
  puts("");
  return 0;
}
