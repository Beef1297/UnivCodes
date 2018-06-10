#include<stdio.h>
#include<stdlib.h>

typedef /*element*/int  elementtype;

struct node{
  elementtype element;
  struct node *next;
};
typedef struct node *list;
 
list initlist(elementtype e){
  list l;
  l = (list)malloc(sizeof(struct node));
  l->element = e;
  l->next = NULL;
  return l;
}
list nohead(elementtype e, list l){
  list n,p;
  if(l == NULL){
    n = initlist(e);
    n ->next = n;
    return n;
  }else{
    n = initlist(e);
    p = l;
    while(p->next != l){
      p = p->next;
    }
    p->next = n;
    p->next->next = l;
    
    return l;
  } 
}
int length(list l){
  list n;
  n = l;
  int count = 0;
  while(n != NULL){
    n = n->next;
    count++;
  }
  return count;
}
void print_int_list(list l){
  list n;
  n = l;
  while(1){
    printf("[%d]",n->element);
    n = n->next;
    if(n == l)break;
  }
  putchar('\n');
}

int main(){
  int i,max;
  char buf[128];
  list l=NULL,keep=NULL,p;
  while(fgets(buf,sizeof(buf),stdin) != NULL){
    sscanf(buf,"%d", & i);
    l = nohead(i, l);
  }
  p = l;
  max = l->element;
  while(1){
    if(p->element >= max){
      max = p->element;
      keep = p;
    }
    p = p->next;
    if(p == l)break;
  }
  //printf("length=%d\n", length(l));
  print_int_list(keep);
  return 0;
}
