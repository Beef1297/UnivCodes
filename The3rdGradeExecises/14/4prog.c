#include<stdio.h>
#include<stdlib.h>
char buf[256];

void warshall(int n, int adjmat[n][n], int result[n][n]){
  int reach[n+1][n][n];
  int i,j,k;
  for(i=0;i<n;++i){
    for(j=0;j<n;j++){
      reach[0][i][j] = adjmat[i][j];
      if(i==j)reach[0][i][j] = 1;
      //printf("%d",reach[0][i][j]);
    }
    //printf("\n");
  }
  for(k=1;k <= n;k++){
    for(j=0;j < n;j++){
      for(i=0;i < n;i++){
	reach[k][i][j] = 0;
	if(reach[k-1][i][j] == 1)reach[k][i][j]=1;
	if(reach[k-1][i][k-1] == 1 && reach[k-1][k-1][j] == 1){
	  reach[k][i][j] = 1;
	}//if
      }//for3
    }//for2
  }//for1
  for(i = 0;i < n; ++i){
    for(j=0;j < n; ++j){
      result[i][j] = reach[n][i][j];
    }
  }
}

int main(){
  int ekisu,eki1,eki2,rosen,i,j;
  float kyori;
  scanf("%d ", &ekisu);
  /*
  int **adjmat;
  adjmat = (int**)malloc(sizeof(int*)*ekisu);
  for(i=0;i < ekisu;++i) adjmat[i] = (int*)mallic(sizeof(int)*ekisu);
  */
  int adjmat[ekisu][ekisu], result[ekisu][ekisu];
  for(i = 0;i < ekisu;++i){
    for(j = 0;j < ekisu;++j){
      adjmat[i][j] = result[i][j] = 0;
    }
  }
  while(fgets(buf, sizeof(buf), stdin) != NULL){
    sscanf(buf, "%d:%d:%d:%f ", &eki1, &eki2, &rosen, &kyori);
    adjmat[eki1][eki2] = adjmat[eki2][eki1] = 1;
  }
  warshall(ekisu, adjmat, result);
  for(i = 0;i < ekisu;++i){
    for(j = 0;j < ekisu;++j){
      printf("%d",result[i][j]);
    }
    puts("");
  }
  return 0;
}
