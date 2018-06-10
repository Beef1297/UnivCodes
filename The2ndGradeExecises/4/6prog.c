#include<stdio.h>
#include<stdlib.h>
#define MAXQUEUE 128
typedef int elementtype;
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
  return p->front <= -1;
}

elementtype getq(struct queue *p){
  elementtype e;
  p->front++;
  if(queueempty(p) == 1 || p->front > p->rear){
    puts("Underflow.");
    exit(1);
  }
  e = p->contents[p->front];
  return e;
}
void putq(struct queue *p, elementtype e){
  p->rear++;
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
  char buf[MAXQUEUE];
  int x,i,j=0;
  struct queue q;
  initqueue(&q);
  
  while( fgets(buf,sizeof(buf),stdin) != NULL){
    if(buf[0] == 'g'){
      x = getq(&q);
      j++;
    }else {
      sscanf(buf,"%d",&x);
      putq(&q,x);
    }

    for(i=j;i <= q.rear;i++)  printf("[%d]",q.contents[i]);
    
    printf("\n");
  }
  return 0;

}
