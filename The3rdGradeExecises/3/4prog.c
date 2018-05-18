#include<stdio.h>

typedef struct {
  long long int a;
  long long int b;
}golden ;

int number = 0;
golden mult_golden(golden x, golden y){
  number += 1;
  golden z;
  z.a = x.a*y.a + x.b*y.b;
  z.b = x.a*y.b + y.a*x.b + x.b*y.b;
  return z;
}
golden power_golden(golden x,int n){
  golden z,temp = {1,0};
  if(n == 0){
    return temp;
  }else{
    z = mult_golden(x,power_golden(x,n-1));
//x^k+1 = x * x^k に則ってこれを再帰にした。
//x^k+1 = x * x * x * ... * x * 1(1 の代りにgolden型の 1 + 0*φ を用意した)
    return z;
  }
}

int main(){
  golden x;
  int n;
  scanf("%lld %lld",&x.a,&x.b);
  scanf("%d",&n);
  x = power_golden(x,n);
  printf("%lld %lld\n",x.a,x.b);
  printf("%d\n",number);
  return 0;
}
