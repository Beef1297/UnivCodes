#include<stdio.h>
#define N 128

struct point{
  int x, y;
};
typedef struct point element;
char kijun;
int count = 0;
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
  ++count;
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

void merge(struct point a[], int m, int n, int h){
  struct point arr[128];
  int i,e,m1;
  m1 = m;
  e = h+1;
  i = m;
  while((m1 <= h) && (e <= n)){
    if(compare(a[m1], a[e]) <= 0){
      arr[i] = a[m1];
      m1++;
    }else {
      arr[i] = a[e];
      e++;
    }

    // printf("m: %d(h: %d),e: %d(n: %d)\n",m,h,e,n);
    i++;
  }

  if(m1 <= h){
    while(m1 <= h){
      arr[i] = a[m1];
      m1++;
      i++;
    }
  }
  if(e <= n){
    while(e <= n){
      //printf("in e<=n, a[x]: %d,a[y]: %d\n",a[e].x,a[e].y);
      arr[i] = a[e];
      e++;
      i++;
    }
  }
  for(m1=m;m1 <= n;m1++){
    a[m1] = arr[m1];
  }

}

int main(){
  char  buf[128];
  struct point p, arr[128];
  int i = 0, h, n;
  scanf("%c %d ",&kijun, &h);
  while(fgets(buf,sizeof(buf),stdin) != NULL && i < 128){
    sscanf(buf, "%d %d", &p.x, &p.y);
    arr[i] = p;
    ++i;
  }
  n = i;
  merge(arr, 0, n-1, h);
  printf("%d\n", count);
  for(i = 0;i < n;++i)
    printf("%d %d\n", arr[i].x, arr[i].y);
  return 0;
}
