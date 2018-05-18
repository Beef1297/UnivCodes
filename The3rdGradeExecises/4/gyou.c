#include<stdio.h>

int main(void){
  char buf[256];
  int i=0,j=0;

  while(fgets(buf,sizeof(buf),stdin) != NULL){
    if(buf[0] == 'g')i++;
    else{
      j++;
    }
  }
  printf("数の数は %d,gの数は %d\n",j,i);
  return 0;
}
