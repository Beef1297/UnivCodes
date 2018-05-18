#include<stdio.h>
#include<stdlib.h>
#define MAXQUEUE 128
typedef int elementtype;
struct queue {
  int front;
  int rear;
  elementtype contents[MAXQUEUE];
};
int check;
void initqueue(struct queue *p){
  p->front = 0;
  p->rear = 0;
}
int queueempty(struct queue *p){
  return p->front == p->rear;
}

elementtype getq(struct queue *p){
  elementtype e;
  if(queueempty(p) == 1 && check <= 0){
    puts("Underflow.");
    exit(1);
  }
  check --;
  e = p->contents[p->front++];
  if(p->front == MAXQUEUE)p->front = 0;
  return e;
}
void putq(struct queue *p, elementtype e){
  check++;
  if(check == MAXQUEUE){
    puts("Overflow.");
    exit(1);
  }
  
  p->contents[p->rear++] = e;
  if(p->rear == MAXQUEUE)p->rear = 0;
}
void print(struct queue *q){
  int f,r,k,i;
  f = q->front;
  r = q->rear;
  for(k=f,i=1;i <= check;k++,i++){
    if(k==MAXQUEUE)k=0;
    printf("[%d]",q->contents[k]);
  }
  putchar('\n');
  return;
}

  
int main(){
  char buf[MAXQUEUE];
  int x;
  struct queue q;
  initqueue(&q);
  
  while( fgets(buf,sizeof(buf),stdin) != NULL){
    if(buf[0] == 'g'){
      x = getq(&q);
    }else {
      sscanf(buf,"%d",&x);
      putq(&q,x);
    }

    print(&q);
  }
  return 0;

}
