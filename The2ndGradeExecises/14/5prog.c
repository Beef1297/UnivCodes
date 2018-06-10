#include<stdio.h>
#include<stdlib.h>
char buf[256];

void print_adj(int n, int adjmat[n][n]){
  int i,j;
  for(i = 0;i < n;i++){
    for(j=0;j < n;j++){
      printf("%d ",adjmat[i][j]);
    }
    printf("\n");
  }
  return ;
}

int diameter(int n, int adjmat[n][n]){
  int d = 1;
  int i,j,k;

  for(k=0;k<n;k++){
    for(j=0;j<n;j++){
      if(adjmat[k][j] != 0){
        for(i=0;i<n;i++){
          if(adjmat[i][k] != 0 && j != i){
            if(adjmat[i][j] == 0){
              adjmat[i][j] = adjmat[i][k] + adjmat[k][j];
            }else if(adjmat[i][j] > (adjmat[i][k] + adjmat[k][j])){
              adjmat[i][j] = adjmat[i][k] + adjmat[k][j];
            }
            if(adjmat[j][i] == 0)adjmat[j][i] = adjmat[i][j];
          }
        }
      }
    }
    // printf("k=%d%s\n",k,"****************");
    // print_adj(n,adjmat);
  }
  for(j=0;j<n;j++){
    for(i=j+1;i<n;i++){
      if(d < adjmat[i][j])d = adjmat[i][j];
    }
  }
  return d;
}


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
  while(fgets(buf, sizeof(buf), stdin) != NULL){
    sscanf(buf, "%d:%d:%d:%f ", &eki1, &eki2, &rosen, &kyori);
    adjmat[eki1][eki2] = adjmat[eki2][eki1] = 1;
  }
  printf("%d\n", diameter(ekisu, adjmat));
  return 0;
}
