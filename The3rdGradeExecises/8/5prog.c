#include<stdio.h>
#include<stdlib.h>
#include<time.h>

#define N 128

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
    exit(1);
  }
}
/*--------------------------------------------------------------*/
void print_point(struct point a[], int m, int n){
  int i;
  for(i=m;i <= n;i++)
    printf("buf of a> x: %d -- y: %d\n",a[i].x,a[i].y );
}
int partition(struct point a[], int m, int n){
  //print_point(a,m,n);
  element pivot,temp;
  int l,r; l = m; r = n;
  int piv;
  piv = m + rand() % (n-m+1);
  pivot = a[piv];
  //printf("pivot is (%d,%d)\n",pivot.x,pivot.y );
  //printf("m: %d,n: %d, piv: %d\n",m,n,piv);
  while(1){
    while((l < n) && (compare(a[l],pivot) < 0))l++;
    while((r > m) && (compare(a[r],pivot) > 0))r--;
    //printf("l: %d,r: %d,piv: %d\n",l,r,piv);

    if(l < r){
      if(piv == l){ piv = r; }
      else if(piv == r){ piv = l; }
      temp = a[l];
      a[l] = a[r];
      a[r] = temp;
    }else break;
    l++; r--;
  }
  if(piv > l){
    a[piv] = a[l];
    a[l] = pivot;
    piv = l;
  }else if(piv < r){
    a[piv] = a[r];
    a[r] = pivot;
    piv = r;
  }
  //print_point(a,m,n);
  return piv;
}

void quicksort(struct point a[], int m, int n){
  int p = 0;
  if(m < n){
    p = partition(a, m ,n);
    quicksort(a, m, p-1);
    quicksort(a, p+1, n);
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
  quicksort(arr, 0, n-1);
  for(i = 0;i < n;++i)
    printf("%d %d\n", arr[i].x, arr[i].y);
  return 0;
}
