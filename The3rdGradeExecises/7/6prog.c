#include<stdio.h>
#define N 128

struct point{
  int x, y;
};
int count = 0;

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
int compare_by(struct point p1,struct point p2, char c){
  ++count;

  switch(c){
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

void bubble_sort(struct point a[], int n, char c){
  int k,l;
  struct point temp;
  k = l = 0;
  for(k=n-2;k >= 0;k--){
    for(l=0;l <= k;l++){
      if(compare_by(a[l],a[l+1],c) > 0){
	temp = a[l];
	a[l] = a[l+1];
	a[l+1] = temp;
      }
    }
  }
}

int main(){
  char c, buf[128];
  struct point p, arr[128];
  int i = 0,n;
  scanf("%c ",&c);
  while(fgets(buf,sizeof(buf),stdin) != NULL && i < 128){
    sscanf(buf, "%d %d", &p.x, &p.y);
    arr[i] = p;
    ++i;
  }
  n = i;
  bubble_sort(arr, n, c);
  printf("%d\n",count);
  for(i=0;i < n;i++)
    printf("%d %d\n",arr[i].x, arr[i].y);
  return 0;
}
