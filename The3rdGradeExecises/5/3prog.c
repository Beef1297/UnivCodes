#include<stdio.h>
#include<stdlib.h>

typedef /*element*/char  elementtype;

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
void print_char_list(list l){
  list n;
  n = l->next;
  while(n != NULL){
    printf("%c",n->element);
    n = n->next;
  }
  putchar('\n');
}
void insert(list l, elementtype e){
  list p;
  p = (list)malloc(sizeof(struct node));
  p->element = e;
  p->next = l->next;
  l->next = p;
}

void myinsert(list l,int i, elementtype e){
  list move;
  int n;
  move = l;
  for(n=0;n < i && move->next != NULL;n++){
    move = move->next;
  }
  insert(move,e);
}
int main(){
  int i;
  char c,buf[128];
  list l,last;
  l = initlist();
  last = l;
  fgets(buf,sizeof(buf),stdin);
  for(i = 0; (c = buf[i])!= '\n';++i){
    last->next = cons(c,NULL);
    last = last->next;
    /*問題2をそのまま利用した*/
  }
  print_char_list(l);

  while(fgets(buf,sizeof(buf),stdin) != NULL){
    sscanf(buf,"%d %c", &i, &c);
    myinsert(l,i,c);
    print_char_list(l);
  }
  return 0;
}
