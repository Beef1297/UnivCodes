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
void insert(list l,elementtype e){//先頭に挿入
  list n;
  n = (list)malloc(sizeof(struct node));
  if(n == NULL){
    printf("memory error\n");
    exit(1);
  }
  n->element = e;
  n->next = l->next;
  l->next = n;
}
list lastinsert(elementtype e){//末尾に挿入
  list n;
  n = (list)malloc(sizeof(struct node));
  if(n == NULL){
    printf("memory error\n");
    exit(1);
  }
  n->element = e;
  n->next = NULL;
  return n;
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

void print_list(list l){
  list n;
  n = l;
  if(l->next->element == 0){
    printf("%d\n",0);
    return ;
  }
  while(n->next != NULL){
    n = n->next;
    printf("%d",n->element);
  }
  puts("");
}

int main(){
  elementtype d;
  char c;
  list l1,l2,print;
  list  p1,p2,mov,keep;
  int n1,n2,len,k,i;
  n1 = n2 = len = k = i = 0;
  //n1,n2で数列の長さを表すex)1234 -> n = 4
  l1 = initlist();
  l2 = initlist();
  print = initlist();
  while((c = getchar()) != '\n'){
    d = (elementtype) c - '0';
    insert(l1,d);
    n1++;
  }
  //print_list(l1);
  while((c = getchar()) != '\n'){
    d = (elementtype) c - '0';
    insert(l2,d);
    n2++;
  }
  //print_list(l2);
  p1 = l1;
  p2 = l2;
  mov = print;
  keep = mov;
  while(p2->next != NULL){
    while(p1->next != NULL){
      if(mov->next == NULL){
	mov->next = lastinsert(p1->next->element * p2->next->element);
      }else{
	mov->next->element += p1->next->element * p2->next->element;
      }
      mov = mov->next;
      // printf("mov->element: %d\n",mov->element);
      p1 = p1->next;
    }
    p1 = l1;
    p2 = p2->next;
    keep = keep->next;
    mov = keep;
  }
  //print_list(print);
/*------------------------------*/
//繰り上がり処理をする
  len = n1 + n2 ;
  //printf("len: %d\n",len);
  p1 = print->next;
  /*for(k=0;k < len-1;k++)*/while(p1->next != NULL){
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
