#include<stdio.h>
#include<stdlib.h>
char buf[128];
/**********
	   struct avl_node* rotate_right(struct avl_node *t);
	   struct avl_node* rotate_left(struct avl_node *t);
	   を定義する
	   +int height(struct avl_node *t);
	   +void put_height(struct avl_node *t);
******************/

struct student {int id; char name[32]; int score;};
typedef struct student datatype;
struct avl_node{datatype data; struct avl_node *left, *right; int height;};

struct avl_node *get_avl(){
  struct avl_node *t;
  /*ドットだけなら葉を返す*/
  if(fgets(buf, sizeof(buf), stdin) == NULL || buf[0] == '.')
    return NULL;
  else {
    /*ドットでなければ節点を表す構造体のアドレスtを用意*/
    t = (struct avl_node*)malloc(sizeof(struct avl_node));
    /*高さを t->heigt に, 学籍番号, 名前, 得点を t->data に格納 */
    sscanf(buf, "[%d]%d,%[^,],%d",&t->height, &t->data.id, t->data.name, &t->data.score);
    /* 左の子を t->left に, t->right に格納 */
    t->left = get_avl();
    t->right = get_avl();
    /*t返却*/
    return t;
  }
}
int height(struct avl_node *t){
  if(t == NULL)return 0;
  else return t->height;
}
void put_height(struct avl_node *t){
  int h;
  h = 0;
  if(t == NULL)return;
  else{
    // printf(";%d\n",t->left->height);
    //printf(";;%d\n",t->right->height);
    if(t->left != NULL){
     
      h = t->left->height;
    }
    if(t ->right != NULL && t->right->height > h){
     
      h = t->right->height;
    }
    // printf("h:%d\n",h);
    t->height = h+1;
  }
}
  
struct avl_node* rotate_right(struct avl_node *t){
  struct avl_node *root;
  if(t->left != NULL)
    root = t->left;
  else return t;
  
  t->left = root->right;
  root->right = t;
  put_height(t);
  put_height(root);
  return root;
}
struct avl_node* rotate_left(struct avl_node *t){
  struct avl_node *root;
  if(t->right != NULL)
    root = t->right;
  else return t;
  t->right = root->left;
  root->left = t;
 
  put_height(t);
  put_height(root);
  
  return root;
}
void print_avl(struct avl_node *t){
  if(t == NULL)
    printf(".\n");
  else{
    printf("[%d]%d,%s,%d\n",t->height,t->data.id,t->data.name,t->data.score);
    print_avl(t->left);
    print_avl(t->right);
  }
}

int main(){
  struct avl_node *t;
  char c;
  scanf("%c ", &c);
  t = get_avl();
  if(c == 'R')
    t = rotate_right(t);
  else if(c == 'L')
    t = rotate_left(t);
  print_avl(t);
  return 0;
}
