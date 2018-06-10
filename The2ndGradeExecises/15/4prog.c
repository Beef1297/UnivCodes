#include<stdio.h>
#include<stdlib.h>
#include<limits.h>
#define ROSENZU "rosenzu.txt"
#define SETMAX 10600
char buf[256];
int dist[SETMAX];


struct node {int eki, rosen; int kyori; struct node *next;};
struct set { int elements[SETMAX]; int size;};

void init_set(struct set *p, int n, int e){
  int i,j=0;
  p->size = n-1;
  
  for(i=0;i<n;i++){
    if(i == e)continue;
    p->elements[j] = i;
    j++;
  }
  return ;
}

int delete_min(struct set *p){
  int min = INT_MAX, i, j;
  i = j = 0;
  if(p->size == 0){
    return -1;
  }else{
    for(j = 0;j < p->size;j++){
      if(min > dist[p->elements[j]]){
	min = dist[p->elements[j]];
	i = j;
      }//等しい場合はtrueにならない
    }
    min = p->elements[i];
    p->elements[i] = p->elements[p->size - 1];
    p->size--;
    return min; 
  }
}
void add_edge(struct node *adjlist[], int eki1, int eki2, int rosen, int kyori){
  struct node *n1,*n2;
  n1 = (struct node *)malloc(sizeof(struct node));
  n2 = (struct node *)malloc(sizeof(struct node));
  n1->next = n2->next = NULL;
  n2->eki = eki1;
  n1->eki = eki2;
  n1->rosen = n2->rosen = rosen;
  n1->kyori = n2->kyori = kyori;
  
  n1->next = adjlist[eki1];
  adjlist[eki1] = n1;
  
  n2->next = adjlist[eki2];
  adjlist[eki2] = n2;
  return;
}
void print(int ekisu){
  int i;
  printf("dist: { ");
  for(i=0;i < ekisu;i++){
    if(dist[i] == INT_MAX){
      printf("INT_MAX ");
    }else if(dist[i] < 0){
      printf("minus ");
    }else{
      printf("%d ", dist[i]);
    }
  }
  printf("}\n");
}

void print_set(struct set *p){
  int i;
  printf("{");
  for(i = 0;i < p->size;++i) printf(" %d", p->elements[i]);
  printf(" }\n");
}

int dijkstra(struct node *adjlist[], int eki1, int eki2, int ekisu){
  struct set unknown;
  struct node *p;
  int i,cur;
  init_set(&unknown, ekisu, eki1);
  cur = eki1;
  for(i = 0;i < SETMAX;i++){
    if(i == eki1) continue;
    dist[i] = INT_MAX;
  }
  while((unknown.size > 0) && (cur != eki2)){
    p = adjlist[cur];
    while(p != NULL){
      if(dist[p->eki] > dist[cur] + p->kyori){
	dist[p->eki] = dist[cur] + p->kyori;
      }
      p = p->next;
    }
    cur = delete_min(&unknown);
  }
  return dist[eki2];
}

int main(void){
  int eki1, eki2, rosen, ekisu, i,kyori;
  FILE *fp = fopen(ROSENZU, "r");
  fscanf(fp, "%d ",&ekisu);
  struct node *adjlist[ekisu];
  
  for(i = 0;i < ekisu;++i) adjlist[i] = NULL;
  while(fgets(buf,sizeof(buf), fp)!= NULL){
    
    sscanf(buf, "%d:%d:%d:%d ", &eki1, &eki2, &rosen, &kyori);

    add_edge(adjlist, eki1, eki2, rosen, kyori);
  }
  fclose(fp);
  scanf("%d %d ", &eki1, &eki2);
  printf("%d\n", dijkstra(adjlist, eki1, eki2, ekisu));
  /* for(i = 0;i < ekisu; ++i)
     if(dist[i] < INT_MAX)
     printf("%d: %d\n", i, dist[i]); */
  return 0;
}
