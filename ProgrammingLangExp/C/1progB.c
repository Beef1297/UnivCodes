#include<stdio.h>
#include<stdlib.h>
#include<math.h>

#define N 128
typedef double elementtype;//要素の型をelementtypeとする
struct node{
  elementtype elem;
  struct node *next;
};
typedef struct node *list;

char buf[N];//入力専用Buffer

list init_list(){//ヘッダ付きlist初期化関数
  list n;
  n = (list)malloc(sizeof(struct node));
  n->next = NULL;
  return n;
}
/*add an element at the last*/
void insert_r(list l, elementtype e){
  list n,p;
  p = l;
  n = (list)malloc(sizeof(struct node));
  while(p->next != NULL)p = p->next;
  n->elem = e;
  n->next = p->next;
  p->next = n;
}

void input(list l){
  double d = 0;
  FILE *fp;
  char *fn = "dataset.txt";
  /*Aと同様にFileを用いて入力する*/
  if((fp = fopen(fn, "r")) == NULL){
    printf("%sを開けませんでした.%sのファイルがあるか確認してください\n",fn,fn);
    exit(1);
  }
  
  while(fgets(buf, N, fp)){
    sscanf(buf, "%lf", &d);
    insert_r(l, d);
    
  }
  fclose(fp);
  return;
}

void print_list(list l){//listを表示する関数
  list p;
  p = l;
  while(p->next != NULL){
    p = p->next;
    printf("%lf\n",p->elem);
  }
  return;
}
elementtype cal_sum(list l){//listの和を求める関数
  list p;
  p = l;
  elementtype sum;
  while(p->next != NULL){
    p=p->next;
    sum += p->elem;
  }
  return sum;
}

void div_list(list n1, list n2){//listを分割する関数
  /*渡されたlist n1をだいたい半分で分割し
    片方をn2から参照できるようにする*/
  list p1,p2;
  p1 = p2 = n1;
  while(p1 != NULL && p1->next != NULL  && p2->next != NULL){
    p1 = p1->next->next;
    p2 = p2->next;
  }
  n2->next = p2->next;
  p2->next = NULL;
}
//sortにmergesortを用いる
//そのためのlistを合わせて一つにする関数
//n1にまとめるようにする
void merge(list n1, list n2){
  list p1,p2,keep;
  p1 = n1;
  p2 = n2;
  while(p1->next != NULL && p2->next != NULL){
    /*Aと同様の理由で等号は含まない*/
    /*それぞれのlistの先頭を比較していってn1のlist
      で昇順になるように並び替えていく*/
    /*つまり,比較の際n1のほうが小さかったらそのままnextに進むだけで
      n2の方が大きかったら比較したn1の要素の前に挿入するようにしていく*/
    
    if(fabs(p1->next->elem) > fabs(p2->next->elem)){
      keep = p2->next;
      p2->next = p2->next->next;
      keep->next = p1->next;
      p1->next = keep;
      p1 = p1->next;
    }else{
      p1 = p1->next;
    }
  }
  if(p1->next == NULL){
    p1->next = p2->next;
    free(n2);
  }else if(p2->next == NULL){
    free(n2);
  }
  
}
//mergesortをする再帰関数
void merge_sort(list l){
  list tail;
  tail = init_list();
  if(l->next == NULL || l->next->next == NULL)return;
  div_list(l, tail);
  merge_sort(l); merge_sort(tail);
  merge(l, tail);
}
//渡されたlistからそのまま和を求め出力する.
void default_sum(list l){
  elementtype sum = 0;
  sum = cal_sum(l);
  printf("入力順の総和は%f\n",sum);
  return;
}
//渡されたlistをsortしその和を求め出力する.
void sorted_sum(list l){
  elementtype sum = 0;
  merge_sort(l);
  sum = cal_sum(l);
  printf("絶対値による昇順の総和は%f\n", sum);
  return;
}

int main(){
  list data;
  data = init_list();
  input(data);
  printf("入力されたデータは下記の通り\n");
  puts("*********");
  print_list(data);
  puts("*********");
  default_sum(data);
  sorted_sum(data);
  return 0;
}
