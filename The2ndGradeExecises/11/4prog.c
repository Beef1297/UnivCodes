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

void find_info(struct node *t, int id){
  struct node *p;
  p = t;
  if(p->data.id == id){
    printf("%s,%d\n",p->data.name, p->data.score);
    return;
  }else if(p->data.id < id && p->right != NULL){
    find_info(p->right, id);
    return;
  }else if(p->left != NULL){
    find_info(p->left, id);
    return;
  }
  puts("Not found.");
}

int main(){
  int id;
  struct node *t = get_tree();
  scanf("%d ", &id);
  find_info(t,id);
  return 0;
}
