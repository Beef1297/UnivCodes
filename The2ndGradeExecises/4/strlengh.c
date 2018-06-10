#include<stdio.h>
#include<string.h>

int main(){
  int i;
  char buf[10000];
  fgets(buf,sizeof(buf),stdin);
  i = strlen(buf);
  printf("length is %i\n",i);
  return 0;
}
