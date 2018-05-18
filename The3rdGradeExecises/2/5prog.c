#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define MAXLEN 128
char buf[MAXLEN + 2];

int main(){
  char *p,*q,str[MAXLEN + 2];
  int i,j,in;

  
  while( fgets(buf, sizeof(buf), stdin) != NULL){
    p = strchr(buf, '\n');
    if(p != NULL) *p = '\0';
    q = buf;
    in = 0;
    for(i=0;i<MAXLEN+2;i++)str[i] = ' ';
    while( sscanf(q, "%d", &i) != EOF ){
      if(in == 0)in = 1;
      str[i] = '#';
      //putchar(str[i]);
      q = strchr(q, ' '); 
      if(q == NULL) break;
      else while(*q == ' ') ++q;
    }
    if(in == 1){
      for(j=0;j<=i;j++)printf("%c",str[j]);
    }
    printf("\n");
  }
  
  return 0;
}
