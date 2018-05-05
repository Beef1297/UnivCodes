#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define N 256
typedef char string;
struct node{
  string firstname[N];
  string lastname[N];
  struct node *next;
};
typedef struct node *doclist;

char buf[N];

void init_list(doclist *l){
  *l = (doclist)malloc(sizeof(struct node));
  (*l)->next = NULL;
  return;
}

void print_str(doclist l){
  doclist p;
  p = l;
  while(p->next != NULL){
    p = p->next;
    printf("%s %s\n",p->firstname, p->lastname);
  }
  return;
}


void insert_firstname(doclist l, doclist org){
  doclist p,q,m,keep;
  q = org;
  /*基準となる要素をまず一つだけ挿入してしまう*/
  if(q->next != NULL){
    q = q->next;
    m = (doclist)malloc(sizeof(struct node));
    m->next = NULL;
    strncpy(m->firstname, q->firstname, strlen(q->firstname));
    m->firstname[strlen(q->firstname)] = '\0';
    strncpy(m->lastname, q->lastname, strlen(q->lastname));
    m->lastname[strlen(q->lastname)] = '\0';
    l->next = m;
  }
  /*先頭から順にstrcmpでfirstnameを比較して適当な位置に挿入する*/
  while(q->next != NULL){
    p = keep = l;
    q = q->next;

    m = (doclist)malloc(sizeof(struct node));
    strncpy(m->firstname, q->firstname, strlen(q->firstname));
    m->firstname[strlen(q->firstname)] = '\0';
    strncpy(m->lastname, q->lastname, strlen(q->lastname));
    m->lastname[strlen(q->lastname)] = '\0';
    //printf("**%s, %s**\n",m->firstname, m->lastname);
    while(p->next != NULL){
      p = p->next;
      if(strcmp(p->firstname, m->firstname) > 0){
	m->next = p;
	keep->next = m;
	break;
      }
      keep = keep->next;
    }
    if(p->next == NULL && keep->next != m){
      m->next = p->next;
      p->next = m;
    }
  }
  return;
}

void insert_lastname(doclist l, doclist str){
  doclist p,keep;
  p = keep = l;
  while(p->next != NULL){
    p = p->next;
    if(strcmp(p->lastname, str->lastname) > 0){
      keep->next = str;
      str->next = p;
      return;
    }
    keep = keep->next;
  }
  str->next = p->next;
  p->next = str;
  return;
}

void input(doclist ll){
  char fn[N], ln[N];
  doclist t1;
  puts("inputed order");
  while(fgets(buf, sizeof(buf), stdin)!=NULL){
    sscanf(buf, "%s %s", fn, ln);
    //printf("%s, %s\n",fn, ln);
    t1 = (doclist)malloc(sizeof(struct node));
    strncpy(t1->firstname, fn, strlen(fn));
    t1->firstname[strlen(fn)] = '\0';
    strncpy(t1->lastname, ln, strlen(ln));
    t1->lastname[strlen(ln)] = '\0';
    insert_lastname(ll, t1);
  }
  puts("****");
  return;
}


int main(void){
  doclist df,dl;
  printf("\"firstname\" \"lastname\"\n");
  init_list(&df);init_list(&dl);
  input(dl);
  printf("lastname で昇順に並べた場合\n");
  print_str(dl);
  insert_firstname(df, dl);
  printf("firstname で昇順に並べた場合\n");
  print_str(df);
  return 0;
}
