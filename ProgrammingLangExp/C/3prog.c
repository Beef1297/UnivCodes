#include<stdio.h>
#include<stdlib.h>
#include<string.h>


typedef struct elementtype{
  char c;
  int pri;
} elemtype;
//優先度をもたせて要素の型を定義しなおす
typedef struct node {
  elemtype e;
  struct node *next;
} *stack;


void init(stack *s){
  *s = (stack)malloc(sizeof(struct node));
  (*s)->next = NULL;
  return;
}
//stackが空かを判断,空だったら1を返す
int is_empty(stack s){
  return s->next == NULL;
}
//先頭ノードの要素の優先度を返す
int top_pri(stack s){
  if(is_empty(s)){
    fprintf(stderr, "stack does not have any element.\n");
    exit(1);
  }
  return s->next->e.pri;
}
//先頭ノードの要素をかえす.
//この場合なら,charを返す.
char top_ch(stack s){
  if(is_empty(s)){
    fprintf(stderr, "stack does not have any element.\n");
    exit(1);
  }
  return s->next->e.c;
}

void push(stack s, elemtype y){
  stack t;
  t = (stack)malloc(sizeof(struct node));
  if(t == NULL){
    fprintf(stderr, "stack is overflow\n");
    exit(1);
  }
  t->e = y;
  t->next = s->next;
  s->next = t;
  return;
}
//先頭のノードを消す.
//正確には,渡された場所の次のノードを削除する.
void delete(stack s){
  stack t;
  if(!is_empty(s)){
    t = s->next;
    s->next = s->next->next;
    free(t);
  }else{
    fprintf(stderr, "you have a mistake in use of the f:delete\n");
    exit(1);
  }
  return ;
}

elemtype pop(stack s){
  elemtype y;
  if(is_empty(s)){
    fprintf(stderr, "stack is underflow\n");
    exit(1);
  }
  delete(s);
  return y;
}

void print_s(stack s){
  stack t;
  t = s;
  while(t->next != NULL){
    t = t->next;
    printf("%c",t->e.c);
  }
  printf("\n");
}
void rev_polish(){
  stack sp;
  elemtype y;
  init(&sp);
  printf("算術式を入力(ただしオペランドは=,-,+,/,*のみ)\n");
  while((y.c = fgetc(stdin)) != EOF){
    /*そこまで演算子も多くなかったのでswitch文で対応した*/
    switch(y.c){
      case '=': y.pri = 0;
        break;
      case '(': y.pri = 4;
        break;
      case ')': y.pri = 1;
        break;
      case '+': y.pri = 2;
        break;
      case '-': y.pri = 2;
        break;
      case '*': y.pri = 3;
        break;
      case '/': y.pri = 3;
        break;
      /*fgetc では改行まで含めてしまうためそれの処理,ついてで空白も
        処理してしまうことで誤って空白をいれて入力してしまっても
        対応できるようにした*/
      case '\n': continue;
      case ' ': continue;
      /**********************/
      /*演算子以外のもの(今回は,数となる)*/
      default : y.pri = 5;
      break;
    }
    while(!is_empty(sp) && top_ch(sp) != '(' && (y.pri <= top_pri(sp))){
      printf("%c",top_ch(sp));
      delete(sp);
    }
    if(y.c != ')'){
      push(sp, y);
    }else{
      delete(sp);
    }
  }
  while(!is_empty(sp)){
    printf("%c",top_ch(sp));
    delete(sp);
  }
  printf("\n");
  return;
}

int main(){
  rev_polish();
  printf("逆ポーランド記法では上記のようになる\n");
  return 0;
}
