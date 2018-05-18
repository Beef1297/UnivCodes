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
void print_element_list(list l){
  list n;
  n = l->next;
  while(n != NULL){
    printf("%d\n",n->element);
    n = n->next;
  }
}

void insert(list l,int i, elementtype e){
  list p,move;
  int n;
  move = l;
  p = (list)malloc(sizeof(struct node));
  p->element = e;
  for(n=0;n < i && move->next != NULL;n++){
    move = move->next;
  }
  p->next = move->next;
  move->next = p;
  return;
}
int main(){
  int i,j,k;
  char buf[128];
  list l,last,check;
  l = initlist();

  while(fgets(buf,sizeof(buf),stdin) != NULL){
    sscanf(buf,"%d", &i);
    last = check = l;
    j = 0;
    k = 0;
    if(l->next == NULL){
      l->next = cons(i,NULL);
    }else{
      while(check->next != NULL){
        if(i == check->next->element)k=1;
        check = check->next;
      }
      if(k == 0){
        while(last->next != NULL){
          if(abs(i) > abs(last->next->element)){
            insert(l,j,i);
            break;
          }else if(abs(i) == abs(last->next->element)){
            if(i * last->next->element < 0){
              i < 0  ? (insert(l,j+1,i)) : (insert(l,j,i));
            }
            break;
          }
          j++;
          last = last->next;
        }
        if(last->next == NULL){
          last->next = cons(i,NULL);
        }
      }
    }
  }
  print_element_list(l);
  return 0;
}
