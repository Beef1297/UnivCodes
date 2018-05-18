#include<stdio.h>
#define N 128

struct point{
  int x, y;
};
struct point_loc{ int loc; struct point p;};


int X(struct point p1,struct point p2){
  if(p1.x > p2.x){
    return  1;
  }else if(p1.x == p2.x)return 0;
  else return -1;
}

int Y(struct point p1,struct point p2){
  if(p1.y > p2.y){
    return 1;
  }else if(p1.y == p2.y)return 0;
  else return -1;
}

int D(struct point p1,struct point p2){
  int a,b;
  a = p1.x*p1.x + p1.y*p1.y;
  b = p2.x*p2.x + p2.y*p2.y;
  if(a > b){
    return  1;
  }else if(a == b)return 0;
  else return -1;//距離で負けた.
}
int compare_by(struct point p1,struct point p2, char c){
  
  switch(c){
  case 'x':
    return X(p1,p2);
  case 'y':
    return Y(p1,p2);
  case 'd':
    return D(p1,p2);
  default:
    printf("Alphabet error\n");
    return -2;
  }
}

void stable_selection_sort(struct point a[], int n, char c){
  struct point_loc arr[128],max,temp;

  int i,k,com,index;
  i = k = com = 0;
  for(i=0;i < n;i++){
    arr[i].p = a[i];
    arr[i].loc = i;
  } 
  for(k=n-1;k >= 0;k--){
    index = 0;
    max = arr[0];
    //printf("arr[0]:%d %d\n",arr[0].p.x,arr[0].p.y);
    for(i=0;i<=k;i++){
   
      com = compare_by(arr[i].p,max.p,c);
      if(com > 0){
	max = arr[i];
	index = i;
      }else if(com == 0){
	if(arr[i].loc > max.loc){
	  max = arr[i];
	  index = i;
	}
      }
      //printf("max:%d %d k: %d\n",max.p.x,max.p.y,k);
    }
    temp = arr[k];
    arr[k] = arr[index];
    arr[index] = temp;
  }
  
  for(i=0;i < n;i++)
    a[i] = arr[i].p;
}

int main(){
  char c, buf[128];
  struct point q, arr[128];
  int i = 0,n;
  scanf("%c ",&c);
  while(fgets(buf,sizeof(buf),stdin) != NULL && i < 128){
    sscanf(buf, "%d %d", &q.x, &q.y);
    arr[i] = q;
    ++i;
  }
  n = i;
  stable_selection_sort(arr, n, c);
  for(i=0;i < n;i++)
    printf("%d %d\n",arr[i].x,arr[i].y);
  return 0;
}
