#include<stdio.h>
#include<stdlib.h>

typedef int elementtype;
struct node{
  elementtype element;
  struct node *next;
};
typedef struct node *list;

list initlist(){
  list n;
  n = (list)malloc(sizeof(struct node));
  n->next = NULL;
  return n;
}
void/*list*/ insert(list l,elementtype e){//先頭に挿入
  list n;
  n = (list)malloc(sizeof(struct node));
  if(n == NULL){
    printf("memory error\n");
    exit(1);
  }
  n->element = e;
  n->next = l->next;
  l->next = n;
  /*return n;*/
}
void reverse(list l){
  list oldp,p,keep;
  p = l->next; oldp = keep = NULL;
  while(p != NULL){
    oldp = p;
    p = p->next;
    oldp->next = keep;
    keep = oldp;
  }
  l->next = oldp;
}
elementtype extract(list l, int num){//先頭を0番目とする
  list n;
  int i;
  n = l;
  elementtype e;
  for(i = 0;i <= num ;i++){
    n = n->next;
    if(n == l){
        e = 0;
        return e;
    }
  }
  e = n->element;
  return e;
}

void print_list(list l){
  list n;
  n = l;
  while(n->next != NULL){
    n = n->next;
    printf("%d",n->element);
  }
  puts("");
}

int main(){
  elementtype a,b,d;
  char c;
  list l1,l2,print;
  list  p1,p2;
  int n1,n2,len,k,i;
  n1 = n2 = len = k = i = 0;
  //n1,n2で数列の長さを表すex)1234 -> n = 4
  l1 = initlist();
  l2 = initlist();
  print = initlist();
  //last = l1;
  while((c = getchar()) != '\n'){
    d = (elementtype) c - '0';
    /*last->next = */insert(l1,d);
    /*last = last->next;*/
    n1++;
  }
  //print_list(l1);
  //last = l2;
  while((c = getchar()) != '\n'){
    d = (elementtype) c - '0';
    /*last->next = */insert(l2,d);
    /*last = last->next;*/
    n2++;
  }
  //print_list(l2);
  if(n1 < n2){
    len = n2;

  }else{
    len = n1;
  }
  p1 = l1->next;
  p2 = l2->next;
  for(k=0;k < len;k++){
    if(p1 == NULL)a = 0;
    else {a = p1->element; p1 = p1->next;}
    if(p2 == NULL)b = 0;
    else {b = p2->element; p2 = p2->next;}
    d = a+ b;
    insert(print,d);
  }
  reverse(print);
  //print_list(print);
/*------------------------------*/
//繰り上がり処理をする
  p1 = print->next;
  for(k=0;k < len-1;k++){
    if(p1->element >= 10){
      d = (int)(p1->element / 10);
      p1->element -= d * 10;
      p1->next->element += d;
    }
    p1 = p1->next;
  }
/*-----------------------------*/
  //printf("your put is\n");
  reverse(print);
  print_list(print);

  return 0;
}
