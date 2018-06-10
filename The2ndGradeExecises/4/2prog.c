#include<stdio.h>
char buf[128];

typedef struct { int x; int y;}zahyo;

void update(zahyo *p, int i, int j){
  p->x += i;
  p->y += j;
  if(p->x*p->x + p->y*p->y >= 10000){//距離の式よりx座標の二乗プラスy座標の二乗が距離の二乗であるから
    p->x = 0;
    p->y = 0;
  }
  return;
}

int main(){
  zahyo xy = {0, 0};
  int i,j;
  while(fgets(buf, sizeof(buf), stdin) != NULL){
    sscanf(buf, "%d %d", &i,&j);
    update(&xy, i, j);
    printf("%d %d\n", xy.x, xy.y);
  }
  return 0;
}
