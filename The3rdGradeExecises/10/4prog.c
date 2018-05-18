#include<stdio.h>
#include<stdlib.h>
#define SIZE 101

struct student { 
  int id;
  char name[32];
  int score;
};
struct node {
  struct student data;
  struct node *next;
};
/*
int length(char *s){
  char *t;
  int i = 0;
  t = s;
  while(*t != '\0'){
    i++;
    t++;
  }
  return i;
}
*/
int hash(char *s){
  int i = 0,S,T;
  S = 0;
  T = (int)s[i]%SIZE;
  for(i=1;s[i] != '\0';i++){
    S = (T*128 + (int)s[i])%SIZE;
    T = S;
  } 
  return T;
}

void set_data(struct node *table[], struct student st){
  struct node *p;
  int k;
  k = hash(st.name);
  //printf("hash: %d\n",k);
  p = (struct node*)malloc(sizeof(struct node));
  p->data = st;
  p->next = table[k];
  table[k] = p;
  
}

int main(){
  int i;
  char buf[128];
  struct student st;
  struct node *table[SIZE], *p;
  
  for(i = 0;i < SIZE;++i) table[i] = NULL;
  while(fgets(buf, sizeof(buf),stdin) != NULL){
    sscanf(buf, "%d,%[^,],%d", &st.id, st.name, &st.score);
    set_data(table, st);
  }
  for(i = 0;i < SIZE;++i){
    p = table[i];
    while(p!=NULL){
      st = p->data;
      printf("(%d)%d,%s,%d\n", i, st.id, st.name, st.score);
      p = p->next;
    }
  }
  return 0;
}
