#include<stdio.h>
#define div 2017

typedef struct {
  long long int a;
  long long int b;
}golden ;

golden mult_golden(golden x, golden y){;
  golden z;
  z.a = (((x.a)*(y.a)%div) + ((x.b)*(y.b))%div);
  if(z.a >= div) z.a %= div;
  z.b = (((x.a)*(y.b)) + ((y.a)*(x.b)) + ((x.b)*(y.b)))%div;
  printf("z.a=%lld:z.b=%lld\n",z.a,z.b);
  return z;
}
golden power_golden(golden x,int n){
  golden z,temp = {1,0};
  printf("n = %d\n",n);
  if(n == 0){
    return temp;
  }else{
    if(n % 2 != 0){
      printf("x.a=%d,x.b=%d");
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
  long int n;
  //scanf("%lld %lld",&x.a,&x.b);
  scanf("%ld",&n);
  x = power_golden(x,n-1);
  //if(x.a < 0)x.a += 2017;
  printf("%lld\n",x.a);
  //printf("%d\n",pnum);
  //printf("%d\n",mnum);
  return 0;
}
