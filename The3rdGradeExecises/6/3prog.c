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
  if(n1 < n2){
    len = n2;

  }else{
    len = n1;
  }
  for(k=0;k < len;k++){
    a = extract(l1,k);
    b = extract(l2,k);
    d = a+ b;
    insert(print,d);
  }
  //print_list(print);
/*------------------------------*/
//繰り上がり処理をする
  keep = print->prev;
  while(keep->prev != print){
    //printf("element:%d\n",keep->element);
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
