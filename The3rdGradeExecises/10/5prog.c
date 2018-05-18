#include<stdio.h>
#include<stdlib.h>
#include<string.h>
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
  p = (struct node*)malloc(sizeof(struct node));
  p->data = st;
  p->next = table[k];
  table[k] = p;
}

int find_score(struct node *table[], char *s){
  //nameが一致するものを探す
  int h,com;
  struct node *p;
  h = hash(s);
  p = table[h];
  while(p != NULL){
    com = strcmp(s, p->data.name);
    if(com == 0)return p->data.score;
    p = p->next;
  }
  return -1;
}

int main(){
  int i, v;
  char buf[128], name[32];
  struct student st;
  struct node *table[SIZE];
  
  for(i = 0;i < SIZE;++i) table[i] = NULL;
  while(fgets(buf, sizeof(buf),stdin) != NULL){
    sscanf(buf, "%d,",&v);
    if(v > 0){
      sscanf(buf, "%d,%[^,],%d", &st.id, st.name, &st.score);
      set_data(table, st);
    }else{
      sscanf(buf,"0,%[^,\n]",name);
    }
  }
  v = find_score(table, name);
  if(v < 0) printf("Not found.\n");
  else      printf("%d\n", v);
  return 0;
}
