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
  //puts("delete start");
  if(l->next == NULL || del >= length(l))return;
  list n,m;
  int k;
  n = l;
  // printf("del: %d\n",del);
  for(k=0;k < del;k++){
    n = n->next;
  }
  //printf("n->next->element:%c\n",n->next->element);
  m = n->next;
  n->next = n->next->next;
  free(m);
}

int positionkakko(list l){
  int a = 0;
  list n;
  n = l;
  n = n->next;
  //print_char_list(l);
  //puts("positionkakko start");
  while(n->next != NULL){
    //printf("a: %d\n",a);
    //printf("%c, %c\n",n->element,n->next->element);
    if(n->element == '('){
      if(n->next->element == ')')return a;
      //else continue;
    }
    if(n->element == '['){
      if(n->next->element == ']')return a;
      //else continue;
    }
    a++;
    n = n->next;
  } 
  
   
  puts("position error");
  exit(1);
}

list dmarukakko(list l, list left){
  list n, right,keep;

  right = left;
  keep = l->next;
  //printf("%c\n",left->element);
  //puts("dmarukakko start");
  while(right->next->element != ')'){
    right = right->next;
  }
  //printf("right ->element :%c\n",right->element);
  if(left->next == right->next){
    //puts("skip");
      //goto delmaru;
      return l;
      
    }
  l->next = left->next;
  //puts("right finished");
  n = right->next;
  right ->next = keep;
  left->next = n;
  /*
    delmaru: 
  printf("left->element: %c || n->next->element %c\n",left->element,n->element);
  print_char_list(l);
  delete(left,position(left, '('));
  print_char_list(l);
  delete(n,position(n, ')'));
  puts("dmarudelete finished");
*/
  //print_char_list(l);
  return right;
}


void dkakukakko(list l, list left){
  list right,cont,keep;
  /*cont を末尾にもってく処理*/
  for(cont = l;cont->next != NULL;cont = cont->next);
  
  //puts("dkaku start");
  
  right = left->next;
  //printf("cont->element: %c\n",cont->element);
  //puts("right start:");
  while(right->element != ']'){
    right = right->next;
  }
  if(left->next == right)return;
  //printf("right->element :%c\n",right->element);
  cont->next = left->next;
  left->next = right;
  
  for(;cont->next != right;cont = cont->next){
    //printf("cont element: %c\n",cont->element);
  }
  cont->next = NULL;
  
  //print_char_list(l);
}
int main(){
  int j,n,k=0;
  int i = 0;
  char c,buf[128],en[128];
  list l,last,cont,po[128],keep;
  l = initlist();
  last = l;

  fgets(buf,sizeof(buf),stdin);
  for(j = 0; (c = buf[j])!= '\n';++j){
    last->next = cons(c,NULL);
    last = last->next;
    /*問題2をそのまま利用した*/
  }
  //print_char_list(l);
  /*----------以上が入力part----------*/
  elementtype check = '\0';
  keep = l;
  while(1){
    i = 0;
    //print_char_list(l);
    for(cont = l->next; (cont->element != '(' && cont->element != '[') && cont->next != NULL;cont = cont->next);
    if(cont->next == NULL)break;
    //puts("search start");
    /*^^-^-^-^-^-^-*/
    /*-^-^-^-^-^-^-^-*/
    for(cont = l->next;/*cont->element != ')' && cont->element != ']'*/cont->next != NULL;cont = cont->next){
      //fgets(en,sizeof(en),stdin);
      //po[0]になにも入ってないときに干渉するとセグメントエラーを起こす
      if(check == '('){
	if(cont->element == ')')break;
      }
      if(check == '['){
        if(cont->element == ']')break;
      }
      
      //printf("cont:%c\n",cont->element);
      if(cont->element == '('){
        po[i++] = cont;
	check = po[0]->element; 
        //printf("(po)%d:%c\n",i-1,po[i-1]->element);
      }
      if(cont->element == '['){
        po[i++] = cont;
	check = po[0]->element;
        //printf("[po]%d:%c\n",i-1,po[i-1]->element);
      }
      
    }
    j = 1;
    do{
      if(i == 1)j = 0;
      j = j % i;
      //printf("j: %d\n",j);
      if(po[j]->element == '('){
        //puts("dmaru in");
        keep = dmarukakko(keep,po[j]);
      }
      if(po[j]->element == '['){
        //puts("dkaku in");
        dkakukakko(l,po[j]);
      }
      n = positionkakko(l);
      delete(l,n);
      //print_char_list(l);
      delete(l,n);
      //print_char_list(l);
      j++;
    }while(j != i);
   }
  print_char_list(l);
  return 0;
}
