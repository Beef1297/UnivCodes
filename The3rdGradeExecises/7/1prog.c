#include<stdio.h>
#define N 128


int max_index(int a[], int n){
  int i,max,index;
  max = a[0];
  index = 0;
  for(i = 1;i < n;++i){
    if(max <= a[i]){
      max = a[i];
      index = i;
    }
  }
  return index;
}


int main(){
  char buf[N];
  int arr[N], i = 0;
  while(fgets(buf,sizeof(buf),stdin) != NULL && i < 128)
    sscanf(buf, "%d", &arr[i++]);
  printf("%d\n",max_index(arr,i));
  return 0;
}
