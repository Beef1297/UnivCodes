#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define SETMAX 10600
char buf[256];

struct station{ int eki; char name[64], rosen[64]; };

int main(){
  int i=0, ekisize, l, r, m,cmp=0;
  struct station *ekidata[SETMAX], *st;
  char ekiname[64];
  FILE *fp = fopen("./ekisen.txt","r");
  while(fgets(buf, sizeof(buf), fp) != NULL && i<SETMAX){
    st = (struct station *)malloc(sizeof(struct station));
    sscanf(buf, "%[^:]:%d:%[^\n]*c", st->name, &st->eki, st->rosen);
    ekidata[i] = st;
    ++i;
  }
  fclose(fp);
  ekisize = i;
  scanf("%[^\n]%*c", ekiname);
  //puts(ekiname);
  l = 0; r = ekisize -1;
  /* nibutan */
  while(l <= r){
    m = (l + r)/2;
    /*ekiname の方が辞書的に大きいと１
    ekidata の方が辞書的に大きいと ー１
    */
    //printf("m: %d,l: %d,r: %d",m,l,r); puts(ekidata[m]->name);
    //fgets(buf, sizeof(buf), stdin);
    cmp = strcmp(ekiname, ekidata[m]->name);
    //printf("cmp: %d\n",cmp);
    if(cmp > 0){
      l = m+1;
    }else if(cmp < 0){
      r = m-1;
    }else{
      //printf("find: ekidata, %s\n",ekidata[m]->name);
      if(m-1 != 0){
        while(strcmp(ekiname, ekidata[m-1]->name) == 0)m--;
      }
      break;
    }
  }

  if(r<l){
    fprintf(stderr, "%s: 駅名がありません\n", ekiname); exit(1);
  }else {
    while(strcmp(ekiname, ekidata[m]->name) == 0){
      printf("%d:%s\n",ekidata[m]->eki, ekidata[m]->rosen);
      m++;
    }
  }
  return 0;
}
