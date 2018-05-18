#include<stdio.h>
#include<stdlib.h>
/******************************/
char buf[128];                //
struct student{               //
  int id;                    //
  char name[32];             //
  int score;                 //
};                           //
typedef struct student datatype;   //
struct node{                  //
  datatype data;              // 
  struct node *left, *right;  //                                 
};                            //
                              //
/*****************************************************************/
struct node* get_tree(){
  struct node *t;
  
  if(fgets(buf, sizeof(buf), stdin) == NULL || buf[0] == '.'){
    return NULL;
  }else{
    t = (struct node *)malloc(sizeof(struct node));
    sscanf(buf, "%d,%[^,],%d", &t->data.id,t->data.name,&t->data.score);
    t->left = get_tree();
    t->right = get_tree();
    return t;
  }
}
void print_tree(struct node *t){
  struct node *p;
  p = t;
  if(p != NULL)printf("%d", p->data.id);
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
struct node *set_node(struct student d){
  struct node *n;
  n = (struct node*)malloc(sizeof(struct node));
  n->data = d;
  n->left = n->right = NULL;
  return n;
}

struct node *bst_insert(struct node *t, struct student d){
  struct node *p, *q;
  p = t;
  if(p == NULL){
    q = set_node(d);
    return q;
  }else{
    if(p->data.id < d.id){
      p->right = bst_insert(p->right, d);
      return p;
    }else{
      p->left = bst_insert(p->left, d);
      return p;
    }
  }
}


void print_bst(struct node *t){
  struct node *p;
  p = t;
  if(p != NULL)printf("%d,%s,%d\n",p->data.id,p->data.name,p->data.score);
  else {
    printf(".\n");
    return;
  }
  print_bst(p->left);
  print_bst(p->right);
}


int main(){
  struct node *t =  get_tree();
  struct student d;
  scanf("%d,%[^,],%d ", &d.id, d.name, &d.score);
  t = bst_insert(t, d);
  print_bst(t);
  return 0;
}
