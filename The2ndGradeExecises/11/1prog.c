#include<stdio.h>
#include<stdlib.h>
/******************************/
char buf[128];                //
typedef int datatype;         //
struct node{                  //
  datatype data;              // 
  struct node *left, *right;  //                                 
};                            //
/******************************/
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
////////////////////////////////////////
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

int main(){
  struct node *t = get_tree();
  print_tree(t);
  printf("\n");
  return 0;
}

