#include<stdio.h>
#include<stdlib.h>
#include<math.h>

char buf[256];

typedef double elemtype;

typedef struct node{
  elemtype em;
  struct node *right;
  struct node *left;
} *tree;

void init_tree(tree *t){
  *t = NULL;
}

void p_inorder(tree t){
  if(t == NULL){
    return;
  }else{
    p_inorder(t->left);
    printf("%18.6f\n",t->em);
    p_inorder(t->right);
  }
}
tree insert(tree t, elemtype e){
  /*Binary Search Tree の作成を再帰的に定義挿入すべき場所に
    たどり着いたら,ノードを作り挿入する*/
  if(t == NULL){
    t = (tree)malloc(sizeof(struct node));
    if(t == NULL){
      fprintf(stderr, "overflow\n");
      exit(1);
    }
    t->em = e;
    t->right = t->left = NULL;
    return t;
  }else{
    if(fabs(t->em) >= fabs(e))t->left = insert(t->left, e);
    else t->right = insert(t->right, e);
    return t;
  }

}

elemtype sumg;
void sum_inorder(tree t){
  if(t == NULL)return;
  /*折角,木構造にしたのにそこから毎回最小値を求めて...とかする
    のはもったいない気がしたので中順で足していくことにした*/
  sum_inorder(t->left);
  sumg += t->em;
  sum_inorder(t->right);
  return;
}

void read_source(tree *t){
  elemtype e;
  while(fgets(buf, sizeof(buf), stdin) != NULL){
    sscanf(buf, "%lf", &e);
    *t = insert(*t, e);//新しいノードを挿入した木の根が返ってくる.
  }
}
void free_tree(tree *t){
  if(*t == NULL)return;
  free_tree(&(*t)->left);
  free_tree(&(*t)->right);
  free(*t);
  //postorderで各ノードを開放していくことで
  //木全体を開放する.
}

int main(void){
  tree bst;
  init_tree(&bst);
  read_source(&bst);
  printf("BSTを中順で表示すると\n");
  p_inorder(bst);
  printf("であるから");
  sum_inorder(bst);
  free_tree(&bst);
  printf("絶対値による昇順の和は%lf\n",sumg);
  return 0;
}
