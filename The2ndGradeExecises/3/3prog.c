#include<stdio.h>

typedef struct {
  long long int a;
  long long int b;
}golden ;


golden mult_golden(golden x, golden y){
  golden z;
  z.a = x.a*y.a + x.b*y.b;
  z.b = x.a*y.b + y.a*x.b + x.b*y.b;
  return z;
}

int main(){
  golden x,y;
  scanf("%lld %lld",&x.a,&x.b);
  scanf("%lld %lld",&y.a,&y.b);
  x = mult_golden(x,y);
  printf("%lld %lld\n",x.a,x.b);
  return 0;
}
