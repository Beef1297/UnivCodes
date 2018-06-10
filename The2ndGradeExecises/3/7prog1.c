#include<stdio.h>
#include<math.h>


typedef struct {
  long long int a;
  long long int b;
}golden;

int comp(golden *x,golden *y){
  long long int t1,t2;
  t1 = 2*(x->a - y->a) + (x->b - y->b);
  t2 = (x->b - y->b)*(x->b -y->b);
  /*
    a + b(1 + sqrt(5))/2 > c + d(1 + sqrt(5))/ 2
    この不等式をといた。まずはsqrt(5)を消すため右辺に寄せ
    2(a-c) + (b-d) > -sqrt(5)(b-d)
    とした。このとき a=c && b=d のときは両辺とも0になるため,return 0として
    最初に例外的に処理をして以降a!=c,b!=dが前提となるようにした。
    次にa>c && b>d のときは不等式は自動的に真になるためその時点でreturn 1とし 
    a<c && b<d のときは同様にreturn -1とした。残りの a<c && b>d , a>c && b<d 
    のときは両辺の符号を考えて二乗して単純に値比較をした.
  */
  if(x->a == y->a && x->b == y->b)return 0;
  if(x->a-y->a > 0){
    if(x->b-y->b > 0) return 1;
    else {
      if(t1 < 0 ) return -1;
      else if(t1*t1 > 5*t2) return 1;
      else return -1;
    }
  }else{
    if(x->b-y->b < 0) return -1;
    else {
      if(t1 > 0)return 1;
      else if(t1*t1 < 5*t2)return 1;
      else return -1;
    }
  }

	     
}

int main(void){
  golden x,y;
  int c;
  
  scanf("%lld %lld",&x.a,&x.b);
  scanf("%lld %lld",&y.a,&y.b);
  c = comp(&x,&y);
  printf("%d\n",c);
  return 0;
}
  
  
