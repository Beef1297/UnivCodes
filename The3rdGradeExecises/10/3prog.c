#include<stdio.h>
#define SIZE 101

struct student { 
  int id;
  char name[32];
  int score;
};

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
int hash(char *s){
  int i = 0,k,S,T;
  k = length(s);
  S = 0;
  T = (int)s[i]%SIZE;
  //printf("amari: %d\n",117*128*128%101);
  for(i=1;i < k;i++){
    
    S = (T*128 + (int)s[i])%SIZE;
    T = S;
    //printf("T:%d, *s:%c\n",T,s[k-1-i]);
  } 
  return T;
}

int main(){
  char s[32];
  scanf("%[^\n]", s);
  printf("%d\n", hash(s));
  return 0;
}
