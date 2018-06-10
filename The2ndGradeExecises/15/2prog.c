#include<stdio.h>
#include<stdlib.h>
#define SETMAX 10600
char buf[256];

struct set{
  int elements[SETMAX]; int size;
};



int delete_min_int(struct set *p){
  int min=SETMAX,j,i=0;
  if(p->size == 0){
    return -1;
  }else{
    for(j=0;j<p->size;j++){
      if(min > p->elements[j]){
	i = j;
	min = p->elements[j];
      }
    }
    min = p->elements[i];
    p->elements[i] = p->elements[p->size - 1];
    p->size--;
    return min;
  }
}
void print_set(struct set *p){
  int i;
  printf("{");
  for(i = 0;i < p->size;++i) printf(" %d", p->elements[i]);
  printf(" }\n");
}


int main(void){
  int i,m;
  struct set s;
  i = 0;
  while(fgets(buf, sizeof(buf), stdin) != NULL){
    sscanf(buf, "%d ", &s.elements[i]);
    ++i;
  }
  s.size = i;
  while(1){
    m = delete_min_int(&s);
    if(m<0) break;
    printf("%d : ",m); print_set(&s);
  }
  return 0;
}

