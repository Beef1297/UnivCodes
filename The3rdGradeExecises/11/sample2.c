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
