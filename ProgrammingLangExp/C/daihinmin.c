#include<stdio.h>
#include<string.h>
#include<strings.h>
#include"common.h"

void search_low_card(int out_cards[8][15],int my_cards[8][15],int use_joker_flag){
  /*
    低い方から探して,最初に見つけたカードを一枚out_cardsにのせる。
    use_joker_flagが1のとき,カードが見つからなければ,jokerを一枚out_cardsにのせる。
  */
  int i,j,find_flag=0;

  clear_table(out_cards);                  //テーブルをクリア
  for(j=1;j<14&&find_flag==0;j++){        //低い方からさがし
    for(i=0;i<4&&find_flag==0;i++){
      if(my_cards[i][j]==1){              //カードを見つけたら               
        find_flag=1;                      //フラグを立て
        out_cards[i][j]=my_cards[i][j];   //out_cardsにのせ,ループを抜ける。
      }
    }
  }
  if(find_flag==0&&use_joker_flag==1){       //見つからなかったとき
    out_cards[0][14]=2;                  //ジョーカーをのせる
  }
}

void make_info_table(int info_table[8][15], int my_cards[8][15]){
  int i,j;
  clear_table(info_table);
  for(j = 1;j < 14;j++){//jokerは考えない
    for(i = 0;i < 4;i++){
      info_table[4][j] += my_cards[i][j];
    }
  }
  for(i = 0;i < 4;i++){
    for(j = 13;j > 0;j--){
      if(my_cards[i][j] == 1)info_table[i][j] = info_table[i][j+1] + 1;
      else info_table[i][j] = 0;
    }
  }
  for(i = 0;i < 4;i++)
    for(j = 0;j < 14;j++)
      if(info_table[i][j] == 2)info_table[i][j] = 0;
  
}

int search_low_pair(int dst_cards[8][15], int info_table[8][15], int my_cards[8][15]){
  int i,j;
  clear_table(dst_cards);
  
  for(i=1;i<14;i++){
    if(info_table[4][i] >= 2){
      for(j=0;j<4;j++)
	dst_cards[j][i] = my_cards[j][i];
      return 1;
    }
  }
  return 0;
}
int search_low_sequence(int dst_cards[8][15], int info_table[8][15]){
  int i,j,k;
  for(j = 1;j < 14;j++){
    for(i = 0;i < 4;i++){
      if(info_table[i][j] >= 3){
	k = 0;
	while(k < info_table[i][j]){
	  dst_cards[i][j+k] = 1;
	  k++;
	}
	return 1;
      }
    }
  }
  return 0;
}

