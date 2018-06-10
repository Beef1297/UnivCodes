#include<stdio.h>
#include<stdlib.h>

typedef /*element*/int elementtype;

struct dlnode{
  elementtype element;
  struct dlnode *prev,*next;
};
typedef struct dlnode *dllist;

extern void insert(dllist p, elementtype e);
extern void delete(dllist p);
extern void prin_dllist(dllist d);
extern void append_dllist(dllist d1, dllist d2);
/*---------------------------------------*/
dllist initlist(){
  dllist n1;
  n1 = (dllist)malloc(sizeof(struct dlnode));
  n1->next = n1->prev = n1;
  return n1;
}
  
void insert(dllist p, elementtype e){
  dllist n,l;
  n = (dllist)malloc(sizeof(struct dlnode));
  n->element = e;
  p->prev = n;
  n->next = p;
  l = p;
  while(l->next != p){
    l = l->next;
  }
  n->prev = l;
  l->next = n;
}
void delete(dllist p){
  dllist n;
  n = p;
  p->prev->next = p->next;
  p->next->prev = p->prev;
  free(n);
}
void print_dllist(dllist d){
  dllist n;
  n = d->next;
  while(n != d){
    printf("[%d]",n->element);
    n = n->next;
  }
  n = d->prev;
  while(n != d){
    printf("{%d}",n->element);
    n = n->prev;
  }
  putchar('\n');
}

void append_dllist(dllist d1, dllist d2){
  if(d2->next == d2 && d2->prev == d2){
    free(d2);
    return ;
  }
  dllist d1last,d2head,d2last;
  d1last = d1->next;
  d2head = d2last = d2->next;
  
  while(d1last ->next != d1){
    d1last = d1last->next;
  }
  while(d2last->next != d2){
    d2last = d2last->next;
  }
  d1last->next = d2head;
  d2head->prev = d1last;
  d2last->next = d1;
  d1->prev = d2last;
  free(d2);
}



int main(){
  char buf[128];
  int i;
  dllist d1,d2;
  dllist n = NULL;
  //初期化
  d1 = initlist();
  d2 = initlist();
  //
  while(fgets(buf,sizeof(buf),stdin) != NULL){
    sscanf(buf, "%d",&i);
    insert(d1, i);
    insert(d2, i);
  }
  print_dllist(d1);
  print_dllist(d2);
  
  /* ここでdeleteを繰り返してd1に含まれる奇数要素を削除*/
  n = d1;
  do{
    //printf("n->next->element:%d\n",n->next->element);
    if(n->next->element % 2 != 0)delete(n->next);
    else n = n->next;
  }while(n->next !=d1);
  /* 同様にd2の偶数要素を削除*/
  n = d2;
  while(n->next != d2){
    if(n->next->element % 2 == 0)delete(n->next);
    else n = n->next;
  }
  print_dllist(d1);
  print_dllist(d2);

  append_dllist(d1,d2);
  print_dllist(d1);
  return 0;
}
