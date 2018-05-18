#include<stdio.h>

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
      }else return -1;
    }else return -1;
  }else return -1;
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
void pushdown(struct point a[], int m, int n){
  int i,p;
  struct point temp;
  i = p = 0;
  /*               */
  if((2*m+1) == n){
    i = 2*m + 1;
    if(compare(a[i], a[m]) > 0){
      temp = a[i];
	a[i] = a[m];
	a[m] = temp;
    }
    return;
  }else if((2*m + 1) > n) return ;

  /*例外的処理を先にしてしまう*/

  if((2*m+2)<=n){
    if(compare(a[2*m+2], a[2*m+1]) >= 0)i = 2*m+2;
    else i = 2*m+1;
    
    if(compare(a[i], a[m]) > 0){
      temp = a[i];
      a[i] = a[m];
      a[m] = temp;
      //printf("pushdown(a, %d, %d)\n",i,n);
      pushdown(a, i, n);
    }  
  }
  
}

int main(){
  char buf[128];
  struct point p, arr[128];
  int i = 0, n;
  scanf("%c ", &kijun);
  while(fgets(buf, sizeof(buf), stdin) != NULL && i < 128){
    sscanf(buf, "%d %d", &p.x, &p.y);
    arr[i] = p;
    ++i;
  }
  n = i;
  pushdown(arr, 0, n-1);
  printf("%d\n",count);
  for(i=0;i<n;++i)
    printf("%d %d\n", arr[i].x, arr[i].y);
  return 0;
}
