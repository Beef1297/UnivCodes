#include<stdio.h>
#include<math.h>


typedef struct {
  long long int a;
  long long int b;
}golden;

int comp(golden *x,golden *y){
  long double g,z,w;
  g = (1 + sqrtl(5) )/2;
  z = (long double)x->a + x->b * g;
  w = (long double)y->a + y->b * g;
  if(z > w)return 1;
  else if(z < w)return -1;
  else return 0;
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
  
  
