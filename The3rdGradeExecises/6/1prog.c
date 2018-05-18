#include<stdio.h>
#define DIGITS 126
/*-------------------------*/
char buf[DIGITS + 2];
//char buf2[DIGITS + 2];

/*------------------------*/
int main(){
  int i,j,len;
  int arr1[DIGITS+1] = {};
  //int arr2[DIGITS] = {};
  //int moveup[DIGITS] = {};
  i = 0;
  j = 0;
  /*
    いっかい126桁での繰り上がりを考えないで
    かいてみる
  */
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
    arr1[j] += (int)(buf[len -1 -j]-'0');

  if(i<j)len = j-1;
  else len = i-1;
  printf("len%d\n",len);
  for(i=0;i < len;i++){
    if(arr1[i] >= 10){
      arr1[i] -= 10;
      arr1[i+1] += 1;
    }
  }

  //printf("Your input is ");
  for(i = len;i >= 0;--i) printf("%d", arr1[i]);
  printf("\n");

  return 0;
}
