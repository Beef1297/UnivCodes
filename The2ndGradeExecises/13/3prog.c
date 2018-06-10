#include<stdio.h>
#include<stdlib.h>
char buf[128];
////////////////////////////////////
//define resolve_red_pair function//
////////////////////////////////////
struct student {int id; char name[32]; int score;};
typedef struct student datatype;
struct rb_node{ datatype data; struct rb_node *left, *right; int black; };
//(black == 0)->red (black == 1)->black;

int is_red(struct rb_node *t){
  return (t != NULL && !t->black);
}
int is_red_family(struct rb_node *t){
  if(is_red(t)){
    if(is_red(t->left))return 1;//case 1
    if(is_red(t->right))return 2;//case 2
    return 0;
  }else{
    return 0;
  }
}
struct rb_node* get_rbtree(){
  struct rb_node *t;
  char c;
  if(fgets(buf, sizeof(buf), stdin) == NULL || buf[0] == '.')
    return NULL;
  else{
    t = (struct rb_node*)malloc(sizeof(struct rb_node));
    sscanf(buf, "[%c]%d,%[^,],%d",&c,&t->data.id,t->data.name,&t->data.score);
    t->black = (c == 'b');
    t->left = get_rbtree(); t->right = get_rbtree();
    return t;
  }
}

void print_rbtree(struct rb_node *t) {
  if(t == NULL) printf(".\n");
  else {
    printf("[%c]%d,%s,%d\n",t->black?'b' : 'r',t->data.id,t->data.name,t->data.score);
    print_rbtree(t->left); print_rbtree(t->right);
  }
}

struct rb_node* rotate_right(struct rb_node *t){
  struct rb_node *rt;
  if(t == NULL || t->left == NULL)return t;
  else{
    rt = t->left;
    t->left = rt->right;
    rt->right = t;
    return rt;
  }
}
struct rb_node* rotate_left(struct rb_node *t){
  struct rb_node *rt;
  if(t == NULL || t->right == NULL)return t;
  else{
    rt = t->right;
    t->right = rt->left;
    rt->left = t;
    return rt;
  }
}

struct rb_node *resolve_red_pair(struct rb_node *t){
  if(t == NULL){
    return t;
  }
  if(is_red_family(t->left) == 1){
    if(is_red(t->right)){
      t->black = 0;
      t->left->black = 1;
      t->right->black = 1;
    }else{
      t = rotate_right(t);
      t->black = 1;
      t->left->black = 0;
      t->right->black = 0;
    }
  }
  if(is_red_family(t->left) == 2){
    if(is_red(t->right)){
      t->black = 0;
      t->left->black = 1;
      t->right->black = 1;
    }else{
      t->left = rotate_left(t->left);
      t = rotate_right(t);
      t->black = 1;
      t->left->black = 0;
      t->right->black = 0;
    } 
  }
  if(is_red_family(t->right) == 2){
    if(is_red(t->left)){
      t->black = 0;
      t->left->black = 1;
      t->right->black = 1;
    }else{
      t = rotate_left(t);
      t->black = 1;
      t->left->black = 0;
      t->right->black = 0;
    }
  }
  if(is_red_family(t->right) == 1){
     if(is_red(t->left)){
      t->black = 0;
      t->left->black = 1;
      t->right->black = 1;
    }else{
      t->right = rotate_right(t->right);
      t = rotate_left(t);
      t->black = 1;
      t->left->black = 0;
      t->right->black = 0;
     } 
  }
  //t->left = resolve_red_pair(t->left);
  //t->right = resolve_red_pair(t->right);
  return t;
}

int main(){
  struct rb_node *t = get_rbtree();
  t = resolve_red_pair(t);
  print_rbtree(t);
  return 0;
}
