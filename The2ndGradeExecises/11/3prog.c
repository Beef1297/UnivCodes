#include<stdio.h>
#include<stdlib.h>
/******************************/
char buf[128];                //
typedef char datatype;        //
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
    sscanf(buf, "%c ", &t->data);
    t->left = get_tree();
    t->right = get_tree();
    return t;
  }
}
void print_tree(struct node *t){
  struct node *p;
  p = t;
  if(p != NULL)printf("%c", p->data);
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

void print_preorder(struct node *t){
  struct node *p;
  p = t;
  if(p != NULL)printf("%c",p->data);
  else  return;
  print_preorder(p->left);
  print_preorder(p->right);
}

void print_inorder(struct node *t){
  struct node *p;
  p = t;
  if(p == NULL)return;
  print_inorder(t->left);
  printf("%c",p->data);
  print_inorder(t->right);
}

void print_postorder(struct node *t){
  struct node *p;
  p = t;
  if(p == NULL)return;
  print_postorder(t->left);
  print_postorder(t->right);
  printf("%c",p->data);
}

int main(){
  struct node *t = get_tree();
  print_preorder(t); printf("\n");
  print_inorder(t); printf("\n");
  print_postorder(t); printf("\n");
  return 0;
}
