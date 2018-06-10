#include<stdio.h>
#include<stdlib.h>
char buf[256];
/*

void add_adjmat(int a[][station], int ekisu){
  int eki1, eki2, rosen;
  float kyori;
   while(fgets(buf, sizeof(buf), stdin) != NULL){
     sscanf(buf, "%d:%d:%d:%f ", &eki1, &eki2, &rosen, &kyori);
     a[eki1][eki2] = a[eki2][eki1] = 1;
   }
   
}
void print_adjmat(int a[][station], int ekisu){
  int i,j;
  for(j = 0;i < ekisu;++i){
    for(i = 0;j < ekisu;++j){
      printf("%d",a[i][j]);
    }
    puts("");
  }
}
*/
int main(){
  int ekisu,eki1,eki2,rosen;
  float kyori;
  scanf("%d ", &ekisu);
  int adjmat[ekisu][ekisu];
  int i,j;
  for(i = 0;i < ekisu;++i){
    for(j = 0;j < ekisu;++j){
      adjmat[i][j] = 0;
    }
  }
  //add_adjmat(adjmat, ekisu);
  while(fgets(buf, sizeof(buf), stdin) != NULL){
    sscanf(buf, "%d:%d:%d:%f ", &eki1, &eki2, &rosen, &kyori);
    adjmat[eki1][eki2] = adjmat[eki2][eki1] = 1;
  }
  //print_adjmat(adjmat, ekisu);
  for(i = 0;i < ekisu;++i){
    for(j = 0;j < ekisu;++j){
      printf("%d",adjmat[i][j]);
    }
    puts("");
  }
  return 0;
}
