#include<stdio.h>
#include<stdlib.h>
#define SETMAX 10600
char buf[128];
struct set{
  int elements[SETMAX]; int size;
};

void init_set(struct set *p, int n, int e){
  int i,j=0;
  p->size = n-1;
  
  for(i=0;i<n;i++){
    if(i == e)continue;
    p->elements[j] = i;
    j++;
  }
  return ;
}

void print_set(struct set *p){
  int i;
  printf("{");
  for(i = 0;i < p->size;++i) printf(" %d", p->elements[i]);
  printf(" }\n");
}


int main(void){
  int n, e;
  struct set s;
  scanf("%d %d ", &n, &e);
  init_set(&s, n, e);
  print_set(&s);
  return 0;
}

