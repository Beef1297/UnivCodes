#include<stdio.h>
#include<stdlib.h>

#define MAXSTACK 128

typedef int elementtype;
typedef struct stack{
  int top;
  elementtype contents[MAXSTACK];
}stack;

void initstack(stack *p){
  p->top = -1;
  return;
}
int stackempty(stack *p){
  return p->top <= -1;
}
elementtype pop(stack *p){
  elementtype x;
  if(stackempty(p) > 0){
    puts("Underflow.");
    exit(1);
  }
  x = p->contents[p->top--];
  return x;
}

void push(stack *p, elementtype e){
  p->top++;
  if(p->top >= MAXSTACK){
    puts("Overflow.");
    exit(1);
  }
  p->contents[p->top] = e;  
  return ;
}


int main(){
  stack s;
  int i;
  elementtype x;
  char buf[128];
  initstack(&s);
  while(fgets(buf,sizeof(buf),stdin) != NULL){
    if(buf[0] == 'p'){
      x = pop(&s);
    }else{
      sscanf(buf, "%d", &i);
      push(&s,i);
    }
    for(i=0;i<=s.top;i++)printf("[%d]",s.contents[i]);
    putchar('\n');
  }
  return 0;
}
