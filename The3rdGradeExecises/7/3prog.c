#include<stdio.h>
#define N 128

/*---------------------------------------------------*/
struct point{
  int x, y;
};
int count = 0;
/*--------------------------------------------------*/
extern int X(struct point p1, struct point p2);
extern int Y(struct point p1, struct point p2);
extern int D(struct point p1, struct point p2);
extern int compare_by(struct point p1,struct point p2, char c);
extern int max_index_by(struct point a[], int n, char c);
extern void selection_sort(struct point a[], int n, char c);
/*------------------------------------------------------*/
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
int max_index_by(struct point a[], int n, char c){
  int index,i;
  struct point max;
  index = 0;
  max.x = a[0].x;
  max.y = a[0].y;

  for(i = 1;i < n;++i){
    if(compare_by(a[i],max,c) >= 0){
	max = a[i];
	//printf("if x: %d,y: %d\n",max.x,max.y);
	index = i;
    }
  }
  return index;
}
void selection_sort(struct point a[], int n, char c){
  int i, k;
  struct point temp;
  i = k = 0;
  for(k = n;k >= 2;k--){
    i = max_index_by(a, k, c);
    //printf("count %d\n",count);
    // printf("a[i:%d]x: %d, a[i:%d]y: %d\n",i,a[i].x,i, a[i].y);
    //printf("a[%d]x: %d, a[%d]y: %d\n",k-1,a[k-1].x,k-i,a[k-1].y);
    temp = a[i];
    //temp = a[i];
    a[i] = a[k-1];
    //a[i].y = a[k-i].y;
    a[k-1] = temp;
    //a[k-i].y = temp.y;
  }
}
/*--------------------------------------------------------------------*/
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
  selection_sort(arr, n, c);
  printf("%d\n", count);
  for(i = 0;i < n;i++)
    printf("%d %d\n",arr[i].x, arr[i].y);
  return 0;
}
