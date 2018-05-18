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
void print_point(struct point a[], int n){
    int i;
    for(i = 0;i < n; i++){
      printf("a[n: %d]---x: %d y: %d\n",n,a[i].x,a[i].y);
    }
}

void merge(struct point a[], int m, int n, int h){
  struct point arr[128];
  int i,e,f;
  f = m;
  e = h+1;
  i = m;
  //print_point(a, n+1);
  while((f <= h) && (e <= n)){
      if(compare(a[f], a[e]) <= 0){
      arr[i] = a[f];
      f++;
    }else {
      arr[i] = a[e];
      e++;
    }

    //printf("m: %d(h: %d),e: %d(n: %d)\n",f,h,e,n);
    i++;
  }

  if(f <= h){
    while(f <= h){
      arr[i] = a[f];
      f++;
      i++;
    }
  }
  if(e <= n){
    while(e <= n){
      arr[i] = a[e];
      e++;
      i++;
    }
  }
  //print_point(arr,n+1);
  for(f = m;f <= n;f++){
    a[f] = arr[f];
  }

}

void merge_sort(struct point a[], int m, int n){
  int h;
  if(m < n){
    h = (m+n)/2;
    merge_sort(a, m, h);
    merge_sort(a, h+1, n);
    merge(a, m, n, h);
  }

}
int main(){
  char  buf[128];
  struct point p, arr[128];
  int i = 0, n;
  scanf("%c ",&kijun);
  while(fgets(buf,sizeof(buf),stdin) != NULL && i < 128){
    sscanf(buf, "%d %d", &p.x, &p.y);
    arr[i] = p;
    ++i;
  }
  n = i;
  merge_sort(arr, 0, n-1);
  printf("%d\n", count);
  for(i = 0;i < n;++i)
    printf("%d %d\n", arr[i].x, arr[i].y);
  return 0;
}
