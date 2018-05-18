#include<stdio.h>
#include<stdlib.h>

#define N 128

struct point{
  int x, y;
};
typedef struct point elementtype;
struct node{
  elementtype element;
  struct node *next;
};
typedef struct node *list;

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
      }else return -1;//距離とx座標は同じだったけどy座標で負けた.
    }else return -1;//距離は同じだったけどx座標で負けた.
  }else return -1;//距離で負けた.
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
void print_listpoint(list l){
    printf("---x: %d y: %d---\n",l->element.x,l->element.y);
}

void split(list l1, list l2){
  list p1,p2;
  p1 = p2 = l1;
  while(1){

    if((p2 = p2->next) == NULL || p2->next == NULL){
      break;
    }
    p1 = p1->next;
    putchar('1');
    print_listpoint(p1);
    p2 = p2->next;
    putchar('2');
    print_listpoint(p2);

  }
  l2->next = p1->next;
  p1->next = NULL;
  list n = l2;
  struct point k;
  while((n=n->next) != NULL){
    k = n->element;
    printf("-----splited l2---%d %d\n", k.x, k.y);
  }
  puts("split finished");
}

void merge(list l1, list l2){
  list p1, p2,fp1,l;
  struct point k;
  fp1 = l1;
  l = l1;
  //fp2 = l2;
  p1 = l1->next;
  p2 = l2->next;
  while(p1 != NULL && p2 != NULL){
    if(compare(p1->element, p2->element) <= 0){
      p1 = p1->next;
      fp1 = fp1->next;
      list n;
      n = (list)malloc(sizeof(struct node));
      n->element = p2->element;
      n->next = fp1->next;
      fp1->next = n;
    }else {
      list n;
      n = (list)malloc(sizeof(struct node));
      n->element = p2->element;
      n->next = fp1->next;
      fp1->next = n;
      fp1 = fp1->next;
      p1 = p1->next;
    }
    while((l=l->next) != NULL){
      k = l->element;
      printf("-----tochu---%d %d\n", k.x, k.y);
    }
  }
    if(p1 == NULL){
      fp1->next = p2;
    }

}
void merge_sort(list l){
  list p;

  p = l;
  if((p=p->next) != NULL && p->next != NULL){
    //要するにsplitできなくなったらダメということ
    list q;
    q = (list)malloc(sizeof(struct node));
    q->next = NULL;
    split(l, q);
    merge_sort(l);
    merge_sort(q);
    merge(l, q);

    free(q);
  }

}
int main(){
  char  buf[128];
  struct point p;
  list l, last;
  scanf("%c ",&kijun);

  last = l = (list)malloc(sizeof(struct node));
  while(fgets(buf,sizeof(buf),stdin) != NULL){
    sscanf(buf, "%d %d", &p.x, &p.y);
    last = last->next = (list)malloc(sizeof(struct node));
    last->element = p;
  }
  last->next = NULL;
  merge_sort(l);

  printf("%d\n", count);
  while((l=l->next) != NULL){
    p = l->element;
    printf("%d %d\n", p.x, p.y);
  }

  return 0;
}
