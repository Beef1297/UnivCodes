#include<stdio.h>
#include<stdlib.h>
/******************************/
char buf[128];                //
typedef int datatype;         //
struct node{                  //
  datatype data;              // 
  struct node *left, *right;  //                                 
};                            //
/*****************************************************************/
struct node* get_tree(){
  struct node *t;
  
  if(fgets(buf, sizeof(buf), stdin) == NULL || buf[0] == '.'){
    return NULL;
  }else{
    t = (struct node *)malloc(sizeof(struct node));
    sscanf(buf, "%d ", &t->data);
    t->left = get_tree();
    t->right = get_tree();
    return t;
  }
}
void print_tree(struct node *t){
  struct node *p;
  p = t;
  if(p != NULL)printf("%d", p->data);
  else{
    printf("-");
    return;
  }
  putchar('(');
  print_tree(p->left);
  putchar(',');
  print_tree(p->right);
  putchar(')');
}
/*****************************************************************/

int size(struct node *t){
  struct node *p;
  int i = 0;
  p = t;
  if(p != NULL){
    i = 1;
  }else{
    return i;
  }
  return i + size(t->left) + size(t->right);
}
/*
int height(struct node *t){
  struct node *p;
  int h=0,h2=0;
  p = t;
  if(p == NULL){
    return h;
  }else{
    h++;
    h += height(p->left);
    h2++;
    h2 += height(p->right);
    if(h > h2)return h;
    else return h2;
  }
    
}
*/
int height(struct node *t){
  int h,lh,lr;
  h = lh = lr = 0;
  if(t==NULL)
    return h;
  else{
    h++;
    lh = height(t->left);
    lr = height(t->right);
    if(lh > lr)return h+lh;
    else return h+lr;
  }
}

int sum(struct node *t){
  struct node *p;
  int S=0;
  p = t;
  if(p == NULL){
    return 0;
  }else{
    S = p->data;
    return S + sum(p->left) + sum(p->right);
  }
}

int main(){
  struct node *t = get_tree();
  printf("%d\n", size(t));
  printf("%d\n", height(t));
  printf("%d\n", sum(t));
  return 0;
}

