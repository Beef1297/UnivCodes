#include<stdio.h>
#include<string.h>
#define N 128

int main(void){
  char str[N],*p;
  int i,count = 0;
  fgets(str,N,stdin);
  p = strstr(str,"//");
  for(i=0;str[i] != '\n';i++){
    if(&str[i] == p){  
      break;
    }
    count++;
  }
  printf("%d\n",count);
  return 0;
}
