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
    if(n % 2 != 0){
      z = mult_golden(x,power_golden(x,n-1));
	return z;
    }else {
      z = power_golden(x,n/2);//z にx^kを代入する処理
      z = mult_golden(z,z);
      return z;
    }
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
