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

struct node *bst_delete(struct node *t, int id){
  struct node *p,*q,*oldq;
  p = t;
  if(p == NULL){
    /*process of delete*/
    puts("ERROR(NOT FOUND)");exit(1);
  }else{
    if(p->data.id < id){
      p->right = bst_delete(p->right, id);
      return p;
    }else if(p->data.id > id){
      p->left = bst_delete(p->left, id);
      return p;
    }else{
      /*process of delete*/
      /*init*/
      q = p->right;
      oldq = p;
      /*****/
      if(q == NULL){
        return p->left;
      }else if(q->left == NULL){
          q->left = p->left;
          return q;
      }else{
        while(q->left != NULL){
          oldq = q;
          q = q->left;
        }

        oldq->left = q->right;
        q->left = p->left;
        q->right = p->right;
        return q;
      }
      /*************************/
    }
  }
}

int main(){
  struct node *t = get_tree();
  int id;
  scanf("%d ", &id);
  t = bst_delete(t, id);
  print_bst(t);
  return 0;
}
