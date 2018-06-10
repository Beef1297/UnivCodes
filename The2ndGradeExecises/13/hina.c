#include<stdio.h>
#include<stdlib.h>
char buf[128];

struct student {int id; char name[32]; int score;};
typedef struct student datatype;
struct rb_node{ datatype data; struct rb_node *left, *right; int black; };
//(black == 0)->red (black == 1)->black;

int is_red(struct rb_node *t){
  return (t != NULL && !t->black);
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

int bnum,kuro;
int is_rbtree(struct rb_node *t){
  struct rb_node *p;
  int bl = 0,br = 0;
  int path;
  /* limitation 3 */
  if(t == NULL){
    if(bnum == 0)bnum = kuro;
    if(kuro == bnum)return 1;
    else  return 0;
  }
  /****************/
  p = t;
  if(p->black) kuro++;
  path = kuro;
  /*limitation 2*/
  if(!p->black ){
    if(is_red(p->left) || is_red(p->right))  return 0;
  }
  /**************/
  bl = is_rbtree(p->left);
  if(path != kuro)kuro = path;
  br = is_rbtree(p->right);
  /*部分木もすべて赤黒木になることより*/
  if(bl && br) return 1;
  else return 0;
  
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

int main(){
  return 0;
}
