#include<stdio.h>
#include<stdlib.h>

#define MAXSTACK 128
#define N 11

char buf[N+2];
char canvas[N][N];

struct zahyo { int x, y;};
typedef struct zahyo elementtype;
typedef struct stack{
  int top;
  elementtype z[MAXSTACK];
}stack;

void initstack(stack *p){
  p->top = -1;
  return;
}
int stackempty(stack *p){
  return p->top <= -1;
}
elementtype pop(stack *p){
  elementtype e;
  if(stackempty(p) > 0){
    puts("Underflow.");
    exit(1);
  }
  e.x = p->z[p->top].x;
  e.y = p->z[p->top].y;
  --p->top;
  //printf("***p->top: %d*********\n",p->top);
  return e;
}

void push(stack *p, int x, int y){
  p->top++;
  if(p->top >= MAXSTACK){
    puts("Overflow.");
    exit(1);
  }
  //printf("[push]x: %d,y: %d\n",x,y);
  p->z[p->top].x = x;
  p->z[p->top].y = y;
  return ;
}

int main(){
  int i,j;
  i = 0;
  /* 入力を1行ずつ読み込んで heya へ格納 */
  while( fgets(buf,sizeof(buf),stdin) != NULL){
    for(j = 0;j < N;++j)
      canvas[i][j] = buf[j];
    ++i;
  }
  elementtype e;
  stack s;
  initstack(&s);
  push(&s,N/2,N/2);
  canvas[N/2][N/2] = '.';
  while(stackempty(&s) != 1){
    e = pop(&s);
    //printf("<<e.x: %d,  e.y: %d>>\n",e.x,e.y);
    //printf("e.x: %d,e.y-1: %d\n",e.x,e.y-1);
    if(canvas[e.x][e.y-1] == ' '){
      push(&s,e.x,e.y-1);
      canvas[e.x][e.y-1] = '.';
    }
    //printf("e.x: %d,e.y+1: %d\n",e.x,e.y+1);
    if(canvas[e.x][e.y+1] == ' '){
      push(&s,e.x,e.y+1);
      canvas[e.x][e.y+1] = '.';
    }
    //printf("e.x+1: %d,e.y: %d\n",e.x+1,e.y);
    if(canvas[e.x+1][e.y] == ' '){
      push(&s, e.x+1, e.y);
      canvas[e.x+1][e.y] = '.';
    }
    //printf("e.x-1: %d,e.y: %d\n",e.x-1,e.y);
    if(canvas[e.x-1][e.y] == ' '){
      push(&s, e.x-1,e.y);
      canvas[e.x-1][e.y] = '.';
    }
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

