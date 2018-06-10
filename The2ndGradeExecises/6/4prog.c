#include<stdio.h>
#include<stdlib.h>

typedef int elementtype;
struct node{
  elementtype element;
  struct node *next, *prev;
};
typedef struct node *list;

list initlist(){
  list n;
  n = (list)malloc(sizeof(struct node));
  n->next = n;
  n->prev = n;
  return n;
}
void insert(list l,elementtype e){//先頭に挿入
  list n,r;
  n = (list)malloc(sizeof(struct node));
  if(n == NULL){
    printf("memory error\n");
    exit(1);
  }
  r = l->next;
  n->element = e;
  if(l->next == l && l->prev == l){
    l->prev = n;
    n->next = l;
  }
  n->next = r;
  n->prev = l;
  r->prev = n;
  l->next = n;

}
int count;
elementtype extract(list l, int num){//先頭を0番目とする
  list n;
  int i;
  n = l;
  printf("count:%d\n",count);
  count++;
  for(i = 0;i <= num ;i++){
    n = n->next;
  }
  return n->element;
}
void print_list(list l){
  list n;
  n = l;
  if(n->next->element == 0){
    printf("%d\n",0);
    return;
  }
  while(n->next != l){
    n = n->next;
    printf("%d",n->element);
  }
  puts("");
}

int main(){
  elementtype a,b,d;
  char c;
  list l1,l2,print,keep;
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
  while((c = getchar()) != '\n'){
    d = (elementtype) c - '0';
    insert(l2,d);
    n2++;
  }
  //print_list(l1);
  len = n1 + n2 -2;
  for(k=0;k <= len;k++){
    d = 0;
    for(i=0;i <= k;i++){
        if(i >= n1 || k-i >= n2)continue;
          a = extract(l1,i);
          b = extract(l2,k-i);
          d += a*b;
    }
    insert(print,d);
  }
  //print_list(print);
/*------------------------------*/
//繰り上がり処理をする
  keep = print->prev;
  while(keep->prev != print){
    if(keep->element >= 10){
      //printf("element: %d\n",keep->element);
      a = (elementtype)(keep->element/10);
      keep->element -= a * 10;
      keep->prev->element += a;
    }
    keep = keep->prev;
  }
  //printf("your put is\n");
  print_list(print);

  return 0;
}
