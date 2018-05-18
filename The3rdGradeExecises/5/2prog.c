#include<stdio.h>
#include<stdlib.h>

typedef /*element*/int  elementtype;

struct node{
  elementtype element;
  struct node *next;
};
typedef struct node *list;
 
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
void print_int_list(list l){
  list n;
  n = l->next;
  while(n != NULL){
    printf("[%d]",n->element);
    n = n->next;
  }
  putchar('\n');
}

int main(){
  int i;
  char buf[128];
  list l, last;
  l = initlist();
  last = l;
  while(fgets(buf,sizeof(buf),stdin) != NULL){
    sscanf(buf,"%d", & i);
    last->next = cons(i,NULL);
    last = last->next;
  }
  printf("length=%d\n", length(l));
  print_int_list(l);
  return 0;
}
