#include<stdio.h>
#include<stdlib.h>

/*------------------------------------------------------*/
typedef /*element*/char  elementtype;

struct node{
  elementtype element;
  struct node *next;
};
typedef struct node *list;
int del = 0;
/*-------------------------------------------------------*/ 
list initlist(){
  list n;
  n = (list)malloc(sizeof(struct node));
  n ->next = NULL;
  return n;
}

list cons(elementtype e, list l){
  list n;
  n = (list)malloc(sizeof(struct node));
  n->element = e;
  n->next = l;
  return n;
} 
int length(list l){
  list n;
  n = l->next;
  int count = 0;
  while(n != NULL){
    n = n->next;
    count++;
  }
  return count;
}
void print_char_list(list l){
  list n;
  n = l->next;
  while(n != NULL){
    printf("%c",n->element);
    n = n->next;
  }
  putchar('\n');
}
void insert(list l,int pos, elementtype e){
  list p,move;
  int n;
  move = l;
  p = (list)malloc(sizeof(struct node));
  p->element = e;
  for(n=0;n < pos && move->next != NULL;n++){
    move = move->next;
  }
  p->next = move->next;
  move->next = p;
  return;
}
void delete(list l){
  if(l->next == NULL || del >= length(l))return;
  list n,m;
  int k;
  n = l;
  for(k=0;k < del;k++){
    n = n->next;
  }
  m = n->next;
  n->next = n->next->next;
  free(m);
}

int main(){
  int j;
  char c,buf[128];
  list l,last;
  l = initlist();
  last = l;
  fgets(buf,sizeof(buf),stdin);
  for(j = 0; (c = buf[j])!= '\n';++j){
    last->next = cons(c,NULL);
    last = last->next;
    /*問題2をそのまま利用した*/
  }
  print_char_list(l);

  while(fgets(buf,sizeof(buf),stdin) != NULL){
    sscanf(buf,"%d", &del);
    /*i　をグローバル変数として定義する。*/
    delete(l);
    print_char_list(l);
  }
  return 0;
}
