#include<stdio.h>
#include<stdlib.h>
#include<math.h>

#define N 128
char buf[N];//入力用のバッファ
int ng;//グローバル変数として配列の要素数を保持
typedef double array;//要素を配列の型として変更可能にする


void input_array(array a[]){
  FILE *fp;
  char *fn = "dataset.txt";
  /*file 名は指定してしまいfileをプログラム側から開ける*/
  if((fp = fopen(fn, "r")) == NULL){
    printf("%sを開けませんでした.\n",fn);
    exit(1);
  }

  while(fgets(buf, N, fp)){
    sscanf(buf, "%lf", &a[ng++]);
  }

  fclose(fp);
  return;
}
/*表示用の関数*/
void print_array(array a[]){
  int i;
  for(i = 0;i < ng;i++){
    printf("%lf\n",a[i]);
  }
  return;
}
/*和を求める関数*/
double cal_sum(array a[]){
  int i;
  array sum = 0;
  for(i = 0;i < ng;i++){
    sum += a[i];
  }
  return sum;
}
void sorting_array(array a[]){
  /* bubble sort の利用*/
  /*絶対値を求めるのにmath.hのfabs()を利用
    比較の際,等号を含めるか否かという問題が
    あるが,負の値を前に持ってくるというようにしたくても
    たびたび,符号判断をしなくてはいけないし,そこを
    ちゃんとしたところで和算には関係しない,かつ等号を
    入れると余計にループが回るだけなので今回は含めない*/

  int i, k;
  array temp;
  for(i = ng-1;i > 0;i--){
    for(k = 0;k < i;k++){
      if(fabs(a[k]) > fabs(a[k+1])){
	temp = a[k];
	a[k] = a[k+1];
	a[k+1] = temp;
      }
    }
  }
}
/*入力順の和を求めそれを表示する関数*/
void sum_of_def(array a[]){
  array sum;
  sum = cal_sum(a);
  printf("入力順の総和は%lf\n",sum);
  return;
}
/*渡された配列を昇順にソートして和を求め
  　表示する関数*/
void sum_of_sorted(array a[]){
  array sum;
  sorting_array(a);
  sum = cal_sum(a);
  printf("昇順の和は%lf\n", sum);
  return;
}

int main(){
  array data[N];
  input_array(data);//fileを読み込む
  printf("inputされたデータは下記の通り\n");
  puts("*****");
  print_array(data);//入力順の表示
  puts("*****");
  sum_of_def(data);//そのままの和
  sum_of_sorted(data);//ソート後の和
  return 0;
}
