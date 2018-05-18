#include<stdio.h>
#include<stdlib.h>

#define MAXSTACK 10000

typedef char elementtype;
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
    puts("Bad.");
    exit(1);
  }
  x = p->contents[p->top--];
  return x;
}

void push(stack *p, elementtype e){
  p->top++;
  //printf("%d\n",p->top);
  if(p->top >= MAXSTACK){
    puts("Overflow.");
    exit(1);
  }
  p->contents[p->top] = e;  
  return ;
}


int main(){
  stack s;
  int i=0;
  elementtype x;
  char buf[MAXSTACK];
  //char a[2]={'(',')'},b[2]={'{','}'},c[2]={'[',']'},d[2]={'<','>'};
	
  initstack(&s);
  fgets(buf,sizeof(buf),stdin);
  while(buf[i] != '\n'){
    /*
      switch 文で一つ一つ判定していくことにした
      括弧の始まりをスタックに入れていき括弧が閉じるときpopしてその括弧が
      ちゃんと閉じていないとBad.と表示し,最後にスタックが空になっていれば
      Good.と表示するようにした.
    */
    switch(buf[i]){
    case '(':
      push(&s,buf[i]);
      break;
    case '{':
      push(&s,buf[i]);
      break;
    case '[':
      push(&s,buf[i]);
      break;
    case '<':
      push(&s,buf[i]);
      break;
    case ')':
      x = pop(&s);
      if(x != '('){
	puts("Bad.");
	return 0;
      }
      break;
    case '}':
      x = pop(&s);
      if(x != '{'){
	puts("Bad.");
	return 0;
      }
      break;
    case ']':
      x = pop(&s);
      if(x != '['){
	puts("Bad");
	return 0;
      }
      break;
    case '>':
      x = pop(&s);
      if(x != '<'){
	puts("Bad.");
	return 0;
      }
      break;
    default:
      break;
    }
    i++;
  }
  //printf("s->top = %d\n",s.top);
  if(stackempty(&s) == 1)puts("Good.");      
  else puts("Bad.");
  return 0;
}
