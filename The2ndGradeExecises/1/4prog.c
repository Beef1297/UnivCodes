#include<stdio.h>

int gf(int n1, int n2){
  int c;
  if(n1 >= n2){
    while(c != 0){
      c = n1 % n2;
      n1 = n2;
      n2 = c;
    }
    return n1;
  }else{
    while(c != 0){
      c = n2 % n1;
      n2 = n1;
      n1 = c;
    }
    return n2;
  } 
}

int main(void){
  int n1,n2,g;
  scanf("%d %d",&n1,&n2);
  g = gf(n1,n2);
  printf("%d\n",g);
  return 0;
}
