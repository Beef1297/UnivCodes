#include<stdio.h>
#define DIGITS 126
/*-------------------------*/
char buf[DIGITS + 2];

/*------------------------*/
int main(){
  int i,j,k,len,n1,n2;
  //int moveup[10] ={};
  int arr1[DIGITS] = {};
  int arr2[DIGITS] = {};
  int print[DIGITS*2] = {};
  i = j = k = n1 = n2 = 0;


  fgets(buf,sizeof(buf),stdin);
  while(buf[i] != '\n') ++ i;
  len = i;
  for(i = 0; i < len ; ++i)
    arr1[i] = (int)(buf[len -1 -i]-'0');
//このときiはlenと同値のはず
  fgets(buf,sizeof(buf),stdin);
  while(buf[j] != '\n') ++ j;
  len = j;
  for(j = 0; j < len ; ++j)
    arr2[j] = (int)(buf[len -1 -j]-'0');
//配列を二つ用意する
  len = i+j-2;//lenは数字の個数だから
  n1 = i;
  n2 = j;

  for(k=0;k <= len;k++){
    for(i=0;i <= k;i++){
      if(i >= n1 || k-i >= n2)continue;
      print[k] += arr1[i] * arr2[k-i];
      //printf("print[%d]: %d\n",k,print[k]);
    }
  }
  //繰り上がりを考える
  for(i=0;i<len;i++){
    if(print[i] >= 10){
      // printf("print[i]:%d  moveup:%d\n",print[i],(int)(print[i]/10));
      k = (int)(print[i]/10);
      print[i] -= k * 10;
      print[i+1] += k;
    }
  }


  //printf("Your input is ");
  for(i = len;i >= 0;--i) {
    if(print[len] == 0){
      printf("%d",0);
      break;
    }
    printf("%d", print[i]);
  }
  printf("\n");

  return 0;
}
