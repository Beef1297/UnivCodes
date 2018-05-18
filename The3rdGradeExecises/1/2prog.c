#include<stdio.h>
#include<stdlib.h>

int main(void){

  char str[32];
  int num;
  fgets(str,sizeof(str),stdin);
  num = atoi(str);
  printf("%d\n",num+1);
  return 0;
}
