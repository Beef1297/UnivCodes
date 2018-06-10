#include<stdio.h>
#define div 2017

typedef struct {
  long long int a;
  long long int b;
}golden ;

golden mult_golden(golden x, golden y){;
  golden z;
  z.a = (((x.a%div)*(y.a%div))%div + ((x.b%div)*(y.b%div))%div)%div;
  z.b = (((x.a%div)*(y.b%div))%div + ((y.a%div)*(x.b%div))%div + ((x.b%div)*(y.b%div))%div)%div;
  //実際、ここまで余りを求める必要はないとおもうが念には念をということで余りをもとめまくった
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
  long int n;
  //scanf("%lld %lld",&x.a,&x.b);
  scanf("%ld",&n);
  x = power_golden(x,n-1);
  if(x.a < 0)x.a += 2017;
  /*
    c 言語の仕様上余りがマイナスになってしまうことがあるため
    最後にチェックしてマイナスだった場合modの考えより2017を足した値も同等の価値を
    もつため,最後に2017を足す処理をする
   */
  printf("%lld\n",x.a);
  return 0;
}
