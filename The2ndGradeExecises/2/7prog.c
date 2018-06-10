#include<stdio.h>

#define N 128

int main(){
  char *p[2],buf[2][N];
  int i,j,check;

  for(i = 0;i <= 1;i++){
    fgets(buf[i],sizeof(buf[i]),stdin);
    for(p[i] = buf[i];*p[i] != '\n' && p[i] != NULL;p[i]++);
    if(p[i] != NULL)*p[i] = '\0';
  }
  //printf("%s,%s\n",buf[0],buf[1]);
  /*------ここまでが入力の部分----------*/
  i = 0;
  while(buf[0][i] != '\0'){
    if(buf[0][i] == buf[1][0]){
      j = 1;
      check = i + 1;
      while(buf[1][j] == buf[0][check]){
	if(buf[1][j+1] == '\0'){
	  printf("%d\n",i);
	  return 0;
	}
	j++; check++;
      }
    }
    i++;
  }
  printf("%d\n",-1);
  return 0;
}
