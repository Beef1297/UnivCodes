#include<stdio.h>

int main(void){
  char str[128];
  fgets(str,sizeof(str),stdin);
  printf("%s",str);
  return 0;
}
