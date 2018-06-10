#include<stdio.h>
#define N 11
char buf[N+2];
char canvas[N][N];

struct zahyo { int x, y;};

int main(){
  int i,j;
  i = 0;
  /* 入力を1行ずつ読み込んで heya へ格納 */
  while( fgets(buf,sizeof(buf),stdin) != NULL){
    for(j = 0;j < N;;++j)
      canvas[i][j] = buf[j];
    ++i;
  }
    
  
  /* canvas の出力 */
  for(i = 0;i < N;++i){
    for(j = 0;j < N;++j){
      printf("%c",canvas[i][j]);
    }
    printf("\n");
    }
  return 0;
}
