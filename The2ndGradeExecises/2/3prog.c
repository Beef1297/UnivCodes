#include<stdio.h>
#include<stdlib.h>
//pow でやるとオーバーフローする

int pom(int a,int b){
  int p,q;

  if(b == 0){
    return 1;
  }
  
  if(b == 1){
    return a%2017;
  }else if(b % 2 == 1){
    return p = ((pom(a,b-1)%2017) * (a % 2017))%2017;
  }else{
    q = pom(a,b/2);
    return p = ((q % 2017)*(q % 2017))%2017;
  }
}
    

int main(void){
  int a,b,p;
  scanf("%d %d",&a,&b);
  p = pom(a,b);
  printf("%d\n",p%2017);
  return 0;
}
