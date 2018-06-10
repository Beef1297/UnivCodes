#include<stdio.h>
#include<stdlib.h>
#define MAXQUEUE 128
#define N 10
char buf[N+2];
char heya[N][N];

struct zahyo {int x,y;};

typedef struct zahyo elementtype;
struct queue {
  int front;
  int rear;
  elementtype contents[MAXQUEUE];
};

void initqueue(struct queue *p){
  p->front = -1;
  p->rear = -1;
}
int queueempty(struct queue *p){
  return p->rear < p->front;
}

elementtype getq(struct queue *p){
  elementtype e;
  p->front++;
  if(queueempty(p) == 1){
    puts("Underflow.");
    exit(1);
  }
  e = p->contents[p->front];
  return e;
}
void putq(struct queue *p, int  ex,int ey){
  p->rear++;
  elementtype e;
  e.x = ex;
  e.y = ey;
  if(p->rear >= MAXQUEUE){
    puts("Sorry.");
    exit(1);
  }else if(p->front >= MAXQUEUE){
    puts("Sorry.");
    exit(1);
  }
  p->contents[p->rear] = e;
}
int main(){
  int i,j,kyori[N][N];
  struct zahyo start,goal;
  goal.x = goal.y = 0;
  struct queue q;
  elementtype e;
  initqueue(&q);
  for(i=0;i<N;i++)
    for(j=0;j<N;j++)
      kyori[i][j]=-1;
  
  i = 0;
  while( fgets(buf,sizeof(buf),stdin) != NULL){
    for(j = 0;j < N;++j){
      if(buf[j] == 'S'){
	start.x = i; start.y = j;
	putq(&q,start.x,start.y);
      }else if(buf[j] == 'G'){
	goal.x = i; goal.y = j;
      }
      heya[i][j] = buf[j];
    }
    ++i;
  }
  //printf("goal.x= %d,goal.y= %d\n",goal.x,goal.y);
  e = getq(&q);
  kyori[e.x][e.y] = 0;
  while(e.x != goal.x || e.y != goal.y){
    //printf("e.x: %d,e.y: %d, kyori[e.x][e.y]: %d\n",e.x,e.y,kyori[e.x][e.y]);
    if(kyori[e.x+1][e.y] == -1 && heya[e.x+1][e.y] != '*'){
      putq(&q,e.x+1,e.y);
      kyori[e.x+1][e.y] = kyori[e.x][e.y] + 1;
    }
    if(kyori[e.x-1][e.y] == -1 && heya[e.x-1][e.y] != '*'){
      putq(&q,e.x-1,e.y);
      kyori[e.x-1][e.y] = kyori[e.x][e.y] + 1;
    }
    if(kyori[e.x][e.y+1] == -1 && heya[e.x][e.y+1] != '*'){
      putq(&q,e.x,e.y+1);
      kyori[e.x][e.y+1] = kyori[e.x][e.y] + 1;
    }
    if(kyori[e.x][e.y-1] == -1 && heya[e.x][e.y-1] != '*'){
      putq(&q,e.x,e.y-1);
      kyori[e.x][e.y-1] = kyori[e.x][e.y] + 1;
    }
    e = getq(&q);
    
  }
  printf("%d\n",kyori[goal.x][goal.y]);

  return 0;  
}
