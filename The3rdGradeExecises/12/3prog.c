#include<stdio.h>
#include<stdlib.h>
char buf[128];
/****************
avl_insert();の実装
***************/

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
    if(t->left != NULL){
     
      h = t->left->height;
    }
    if(t ->right != NULL && t->right->height > h){
     
      h = t->right->height;
    }
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
struct avl_node* balance(struct avl_node *t){
  int hl,hr,hl2,hr2;
  hl2 = hr2 = 0;
  hl = height(t->left);
  hr = height(t->right);
  if(abs(hl-hr) <= 1)return t;
  else{
    if(hl > hr){
      hl2 = height(t->left->left);
      hr2 = height(t->left->right);
      if(hr2 > hl2){
	t->left = rotate_left(t->left);
      }
      t = rotate_right(t);
    }else if(hl < hr){
      hl2 = height(t->right->left);
      hr2 = height(t->right->right);
      if(hl2 > hr2){
	t->right = rotate_right(t->right);
      }
      t = rotate_left(t);
    }    
    return t;
  }
}

struct avl_node* avl_insert(struct avl_node *t, struct student d){
  if(t == NULL){
    struct avl_node* add;
    add = (struct avl_node*)malloc(sizeof(struct avl_node));
    add->left = add->right = NULL;
    add->data = d;
    add->height = 1;
    return add;
  }else{
    if(t->data.id > d.id){
      t->left = avl_insert(t->left, d);
    }
    if(t->data.id < d.id){
      t->right = avl_insert(t->right, d);
    }
    put_height(t);
    t = balance(t);
    return t;
  }
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
  struct student st;
  struct avl_node *t = NULL;
  while(fgets(buf, sizeof(buf), stdin) != NULL){
    /*gakusei no jouhou wo yomitori*/
    sscanf(buf, "%d,%[^,],%d",&st.id,st.name,&st.score);
    /*AVL tree ni sono data wo tsuika*/
    t = avl_insert(t, st);
  }
  print_avl(t);
  return 0;
}
