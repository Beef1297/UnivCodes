#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define SIZE 101

struct student { 
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
  struct node *p,*keep;
  int k;
  k = hash(st.name);
  p = (struct node*)malloc(sizeof(struct node));
  p->data = st;
  p->next = NULL;
  if(table[k] != NULL){
    keep = table[k];
    while(keep->next != NULL){
      keep = keep->next;
    }
    keep->next = p;
  }else{
    table[k] = p;
  }
}

int add_score(struct node *table[], struct student st){
  //nameが一致するものを探す
  //加えて、一致したものがあったらscoreを加える
  int h,com;
  struct node *p;
  h = hash(st.name);
  //printf("hash: %d\n",h);
  p = table[h];
  while(p != NULL){
    com = strcmp(st.name, p->data.name);
    if(com == 0){
      p->data.score += st.score;
      //printf("score: %d\n",p->data.score);
      return 1;
    }
    p = p->next;
  }
  return -1;
}

int main(){
  int i;
  char buf[128];
  struct student st;
  struct node *table[SIZE],*p;
  
  for(i = 0;i < SIZE;++i) table[i] = NULL;
  while(fgets(buf, sizeof(buf),stdin) != NULL){
    sscanf(buf, "%[^,],%d",st.name, &st.score);
    if(add_score(table, st) < 0){
      //puts("set");
      set_data(table, st);
    }
  }
  for(i = 0;i < SIZE;++i){
    p = table[i];
    while(p!=NULL){
      st = p->data;
      printf("%s,%d\n",st.name, st.score);
      p = p->next;
    }
  }
  return 0;
}
