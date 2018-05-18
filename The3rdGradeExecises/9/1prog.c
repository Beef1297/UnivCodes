#include<stdio.h>

struct point{
  int x, y;
};
typedef struct point element;
char kijun;
/*--------------------------------------------------------------*/
int X(struct point p1,struct point p2){
  if(p1.x > p2.x){
    return  1;
  }else if(p1.x == p2.x){
    if(p1.y > p2.y){
      return  1;
    }else if(p1.y == p2.y){
      return  0;
    }else{
      return -1;
    }
  }else return -1;
}

int Y(struct point p1,struct point p2){
  if(p1.y > p2.y){
    return 1;
  }else if(p1.y == p2.y){
    if(p1.x > p2.x){
      return 1;
    }else if(p1.x == p2.x){
      return 0;
    }else return -1;
  }else return -1;
}

int D(struct point p1,struct point p2){
  int a,b;
  a = p1.x*p1.x + p1.y*p1.y;
  b = p2.x*p2.x + p2.y*p2.y;
  if(a > b){
    return  1;
  }else if(a == b){
    if(p1.x > p2.x){
      return 1;
    }else if(p1.x == p2.x){
      if(p1.y > p2.y){
	return 1;
      }else if(p1.y == p2.y){
	return 0;
      }else return -1;//距離とx座標は同じだったけどy座標で負けた.
    }else return -1;//距離は同じだったけどx座標で負けた.
  }else return -1;//距離で負けた.
}
int compare(struct point p1,struct point p2){
  switch(kijun){
  case 'X':
    return X(p1,p2);
  case 'Y':
    return Y(p1,p2);
  case 'D':
    return D(p1,p2);
  default:
    printf("Alphabet error\n");
    return -2;
  }
}
/*--------------------------------------------------------------*/
int is_heap(struct point a[], int n){
  int i,ret;
  ret = 1;
  i = 0;
  
  while((2*i + 1) < n){
    if(compare(a[i],a[2*i+1]) < 0)ret = 0;
    if((2*i+2)<n){
      if(compare(a[i],a[2*i+2]) < 0)ret = 0;
    }
    i++;
  }
  return ret;
}

int main(){
  char buf[128];
  struct point p, arr[128];
  int i = 0, n;
  scanf("%c ",&kijun);
  while(fgets(buf,sizeof(buf),stdin) != NULL && i<128){
    sscanf(buf, "%d %d", &p.x, &p.y);
    arr[i] = p;
    ++i;
  }
  n = i;
  if(is_heap(arr, n)) printf("Yes.\n");
  else printf("No.\n");
  return 0;
}
