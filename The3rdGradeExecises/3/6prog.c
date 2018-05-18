#include<stdio.h>

typedef struct {
  long long int a;
  long long int b;
}golden ;

int mnum = 0;
int pnum = 0;
golden mult_golden(golden x, golden y){;
  golden z;
  //問題文にあることに則ってコードをかいた
  z.a = x.a*y.a + x.b*y.b;
  z.b = x.a*y.b + y.a*x.b + x.b*y.b;
  pnum += 3;
  mnum += 5;
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
      z = power_golden(x,n/2);
      z = mult_golden(z,z);
      return z;
    }
  }    
}
  
int main(){
  golden x={1,-1};
  int n;
  //scanf("%lld %lld",&x.a,&x.b);
  scanf("%d",&n);
  x = power_golden(x,n-1);
  printf("%lld\n",x.a);
  printf("%d\n",pnum);
  printf("%d\n",mnum);
  return 0;
}
