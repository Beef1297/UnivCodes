#include<stdio.h>
#include<stdlib.h>

/*------------------------------------------------------*/
typedef /*element*/char  elementtype;

struct node{
  elementtype element;
  struct node *next;
};
typedef struct node *list;
/*-------------------------------------------------------*/ 
list initlist(){
  list n;
  n = (list)malloc(sizeof(struct node));
  n ->next = NULL;
  return n;
}

list cons(elementtype e, list l){
  list n;
  n = (list)malloc(sizeof(struct node));
  n->element = e;
  n->next = l;
  return n;
} 
int length(list l){
  list n;
  n = l->next;
  int count = 0;
  while(n != NULL){
    n = n->next;
    count++;
  }
  return count;
}
void print_char_list(list l){
  list n;
  n = l->next;
  while(n != NULL){
    printf("%c",n->element);
    n = n->next;
  }
  putchar('\n');
  //puts("print finished);
}
void insert(list l,int pos, elementtype e){
  list p,move;
  int n;
  move = l;
  p = (list)malloc(sizeof(struct node));
  p->element = e;
  for(n=0;n < pos && move->next != NULL;n++){
    move = move->next;
  }
  p->next = move->next;
  move->next = p;
  return;
}
void delete(list l, int del){
  if(l->next == NULL || del >= length(l))return;
  list n,m;
  int k;
  n = l;
  for(k=0;k < del;k++){
    n = n->next;
  }
  m = n->next;
  n->next = n->next->next;
  free(m);
}

int position(list l, elementtype e){
  int a = 0;
  list n;
  n = l->next;
  while(n->element != e ){
    //printf("%d",a);
    a++;
    n = n->next;
  }
  //puts("position finished");
  return a;
}

void dmarukakko(list l){
  list n,left,right,keep,cont;
  cont = l;

  while(1){
    left = cont->next;
    right = cont->next;
    keep = cont->next;
    while(left != NULL &&left->element != '(' ){
      //printf("%c\n",left->element);
      left = left->next;
    }
    //puts("left finished");
    if(left == NULL) return;
    //printf("%c\n",left->element);
    cont->next = left->next;
    while(right->next->element != ')'){
      right = right->next;
    }
    //puts("right finished");
    n = right->next;
    right ->next = keep;
    left->next = n;

    //print_char_list(l);
    delete(cont,position(cont, '('));
    delete(cont,position(cont, ')'));
    cont = right;
    // puts("delete finished");
  }
}
void dkakukakko(list l){
  list left,right,cont;
  /*cont を末尾にもってく処理*/
  for(cont = l;cont->next != NULL;cont = cont->next);
  
  while(1){
    left = l->next;
    right = l->next;
    puts("delete start");
    printf("cont->element: %c\n",cont->element);
    while(left != NULL && left->element != '['){
      left = left->next;
      printf("left->element: %c\n",left->element);
    }   
    if(left == NULL)return;
    puts("right start:");
    while(right->element != ']'){
      right = right->next;
    }
    if(left->next == right)goto del;
    printf("right->element :%c\n",right->element);
    cont->next = left->next;
    left->next = right;

    for(;cont->next != right;cont = cont->next){
      printf("cont element: %c\n",cont->element);
    }
    cont->next = NULL;
    
    print_char_list(l);
  del:
    print_char_list(l);
    delete(l,position(l,'['));
    delete(l,position(l,']'));
    print_char_list(l);
  }
}
int main(){
  int j;
  char c,buf[128];
  list l,last;
  l = initlist();
  last = l;
  
  fgets(buf,sizeof(buf),stdin);
  for(j = 0; (c = buf[j])!= '\n';++j){
    last->next = cons(c,NULL);
    last = last->next;
    /*問題2をそのまま利用した*/
  }
  // print_char_list(l);
  /*----------以上が入力part----------*/
  dkakukakko(l);
  dmarukakko(l);
  //print_char_list(l);
  //dkakukakko(l);
  print_char_list(l);
  return 0;
}

