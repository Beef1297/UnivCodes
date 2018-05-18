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
/*****************************************************************/
struct node *SetNewnode(struct node *t, struct student d){
  struct node *n;
  n = (struct node*)malloc(sizeof(struct node));
  n->data = d;
  n->left = n->right = t->left;
  return n;
}

void print_bst_dummy(struct node *t, struct node *dummy){
  if(t == dummy)
    printf(".\n");
  else {
    printf("%d,%s,%d\n",t->data.id, t->data.name, t->data.score);
    print_bst_dummy(t->left,dummy);
    print_bst_dummy(t->right,dummy);
  }
  return;
}

void print_bst(struct node *t){
  print_bst_dummy(t->right, t->left);
  return;
}

void bst_insert(struct node *t, struct student d){
  struct node *p, *prev, *dummy;
  dummy = t->left;
  dummy->data = d;
  p = t->right;
  prev = t;
  while(p != dummy){
    prev = p;
    if(p->data.id < d.id ){
      p = p->right;
      if(p == dummy){
        prev->right = SetNewnode(t,d);
        return;
      }
    }else if(p->data.id > d.id){
      p = p->left;
      if(p == dummy){
        prev->left = SetNewnode(t,d);
        return;
      }
    }
  }
  /*ここに来る場合はwhileに入らないときだけ、それはrootがないときのはずwhile(p != t->left)のときは*/
  /*まとめてしまえばいい*/
  if(prev == t){
    prev->right = SetNewnode(t, d);
    return;
  }
}
/*****************************************************************/


int main(){
  char buf[128];
  struct node *t = (struct node*)malloc(sizeof(struct node)),
              *dummy = (struct node*)malloc(sizeof(struct node));
  struct student st;
  t->left = t->right = dummy->left = dummy->right = dummy;
  while(fgets(buf, sizeof(buf),stdin) != NULL){

    sscanf(buf, "%d,%[^,], %d", &st.id, st.name, &st.score);
    bst_insert(t, st);
  }
  print_bst(t);
  return 0;
}
