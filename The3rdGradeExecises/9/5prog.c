#include<stdio.h>
#include<stdlib.h>
struct point {int x, y;};
/* x基準 x座標が大きいほうを大きい座標 */
/*
void bucket_sort(struct point a[], int n){
  struct point b[128];
  int c[100] = {};
  int i,k;
  i = k = 0;
  for(i=0;i < n;i++){
    c[a[i].x]++;
    b[i] = a[i];
  }
  for(i=1;i < 100;i++){
    c[i] = c[i] + c[i-1];
  }
  for(k=n-1;k >= 0;k--){
    //printf("b[%d]:x %d,y %d\n",k,b[k].x,b[k].y);
    i = b[k].x;
    c[i]--;
    a[c[i]] = b[k];
  }
}
*/
int bits(int z, int k, int j){
  return (z>>k) & ((1 << j) -1);
}
void radix_sort(struct point a[], int n, int r, int dmax){
  //r: 基数 dmax:比較する桁数
  int i,j,k,l,m;
  i = k = j = l = m = 0;
  int init = (1 << r);
  int c[init];
  
  struct point b[128];

  for(l=0;l < dmax;l++){

    for(j=0;j < init;j++){
      c[j] = 0;
    }
    for(j=0;j < n;j++){
      m = a[j].x;
      k = bits(m, l*r, r);//(m >> (l * r)) & ((1 << r) - 1);
      c[k]++;
      b[j] = a[j];
    }
    /*
      for(j=0;j < n;j++){
      printf("---b[%d]:x %d, %d\n",j,b[j].x,b[j].y);
      }
    */
    for(j=1;j < init;j++){
      c[j] = c[j] + c[j-1];
    }
    for(j=n-1;j >= 0;j--){
      m = b[j].x;
      i = bits(m, l*r, r);//(b[j].x >> (l * r)) & ((1 << r) - 1);
      c[i]--;
      //printf("c[i %d] : %d\n",i,c[i] );
      a[c[i]] = b[j];
    }
    for(j=0;j < n;j++){
      printf("%d %d\n",a[j].x,a[j].y);
    }
    puts("--");
  }


}

int main(){
    char buf[128];
    struct point p,arr[128];
    int i = 0, n, r, dmax;
    scanf("%d %d ",&r, &dmax);
    while(fgets(buf, sizeof(buf), stdin) != NULL && i < 128){
      sscanf(buf, "%d %d", &p.x, &p.y);
      arr[i] = p;
      ++i;
    }
    n = i;
    radix_sort(arr, n, r, dmax);
    return 0;
}
