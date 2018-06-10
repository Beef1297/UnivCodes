#include<stdio.h>
#include<stdlib.h>
char buf[256];
struct node {int eki, rosen; float kyori; struct node *next;};

void add_edge(struct node *adjlist[], int eki1, int eki2, int rosen, float kyori){
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
/*
void print_adjlist(struct node *adjlist[], int n){
  int i;
  struct node *p;
  for(i = 0;i < n; ++i){
    printf("%d:",i);
    p = adjlist[i];
    while(p!=NULL){
      printf(" (%d,%d,%.3f)", p->eki, p->rosen, p->kyori);
      p = p->next;
    }
    printf("\n");
  }
  return ;
}
*/
float max_return(struct node *adjlist[], int eki, int moto){
  float max = 0.000;
  struct node *p;
  p = adjlist[eki];
  while(p != NULL){
    if(p->eki != moto){
      if(p->kyori > max){
	max = p->kyori;
      }
    }
    p = p->next;
  }
  return max;
}
float two_hop_kyori(struct node *adjlist[], int eki){
  float d=0.000,l=0.000;
  struct node *p;
  p = adjlist[eki];
  while(p != NULL){
    if(p->eki != eki){
      l = p->kyori + max_return(adjlist, p->eki, eki);
      if(l > d){
	d = l;
      }
    }
    p = p->next;
  }
  return d;
}

int main(void){
  int eki1, eki2, rosen, ekisu, i;
  float kyori;
  
  scanf("%d ", &ekisu);
  struct node *adjlist[ekisu];
  
  for(i=0;i < ekisu;++i)adjlist[i] = NULL;
  while(fgets(buf, sizeof(buf), stdin) != NULL) {
    
    sscanf(buf, "%d:%d:%d:%f ", &eki1, &eki2, &rosen, &kyori);
    
    add_edge(adjlist, eki1, eki2, rosen, kyori);
  }
  for(i=0;i<ekisu;++i)
    printf("%d: %.3f\n", i, two_hop_kyori(adjlist, i));
  return 0;
}
