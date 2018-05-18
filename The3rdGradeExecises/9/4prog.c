#include<stdio.h>

struct point {int x, y;};
/* x基準 x座標が大きいほうを大きい座標 */
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

int main(){
    char buf[128];
    struct point p,arr[128];
    int i = 0, n;
    while(fgets(buf, sizeof(buf), stdin) != NULL && i < 128){
      sscanf(buf, "%d %d", &p.x, &p.y);
      arr[i] = p;
      ++i;
    }
    n = i;
    bucket_sort(arr, n);
    for(i = 0;i < n;++i)
      printf("%d %d\n", arr[i].x, arr[i].y);
    return 0;
}
