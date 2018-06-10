#include<stdio.h>

typedef struct {
  long long int a;
  long long int b;
} golden;

golden double_golden(golden x){
  golden y;
  y.a = x.a << 1;
  y.b = x.b << 1;
  
  return y;
}

int main(void){
  golden x;
  scanf("%lld %lld",&x.a,&x.b);
  x = double_golden(x);
  printf("%lld %lld\n",x.a,x.b);
  return 0;
}
