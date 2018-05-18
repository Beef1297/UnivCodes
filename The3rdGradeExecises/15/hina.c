#include<stdio.h>
#include<stdlib.h>
#define SETMAX 10600

struct set{
  int elements[SETMAX]; int size;
};

void print_set(struct set *p){
  int i;
  printf("{");
  for(i = 0;i < p->size;++i) printf(" %d", p->elements[i]);
  printf(" }\n");
}

int main(void){
  return 0;
}

