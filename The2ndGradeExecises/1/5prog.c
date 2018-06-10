#include<stdio.h>
#include<string.h>
#define N 128


int main(void){
  int n[N],i,top,sec;
  char str[N],*c;
  fgets(str,N,stdin);
  c = strtok(str," \n");
  for(i = 0;i < 10;i++){
    if(c == NULL){
      break;
    }else{
      n[i] = atoi(c);
      //printf("%d\n",n[i]);
    }
    c = strtok(NULL," \n");
  }
  top = n[0];
  sec = -1000000;
  for(i = 1;i < 10;i++){
    if(top < n[i]){
      top = n[i];
    }
  }
  //printf("top: %d\n",top);
  for(i = 0;i < 10;i++){
    if(top > n[i] && sec < n[i]){
      sec = n[i];
      //printf("sec(): %d\n",sec);
    }else continue;
  }
  printf("%d\n",sec);
  return 0;
}
