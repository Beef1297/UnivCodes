//
//  Canvasのテストです。
//
import java.awt.*;//FrameやCanvasを使うのに必要なimport文
import java.awt.event.*;//FrameやCanvasのイベント処理に必要

public class GraphicsDemo {
  public static void main(String args[]){
    //アプリはここから実行されます。
    Frame frame=new Frame();//Frameを作成
    AppCanvas canvas=new AppCanvas();//Canvasの継承クラスのAppCanvasを作成
    frame.add(canvas);//canvasをフレームに追加
    frame.addWindowListener(new Adapter());//Windowのイベントの処理先を指定
    frame.setSize(300,240);//サイズを指定
    frame.setVisible(true);//表示
  }
}

//
// Canvasは継承クラスAppCanvas
//  ★Canvasの継承クラス内中の
//　　　public void paint(Graphics g)
//  は描画の必要が生じると、自動で呼びだれます。
class AppCanvas extends Canvas{
  //以下に描画したい内容を書く
  public void paint(Graphics g){
    int x,y;//描画するための横位置の基準
    int w,h;//描画するための幅の基準
    int dw,dh;//描画するときの丸み
    int px[]=new int[3];//多角形を描画する横位置
    int py[]=new int[3];//多角形を描画する時の位置
    int x2,y2;//直線の終点

    //
    // g.setColor(Color.色の名前);
    //で描画する色が設定できます。色の名前は、以下から選びます。
    //  WHITE - 白　　　　　LIGHT_GRAY　－ライトグレ
    //  GRAY  - グレイ　　　DARK_GRAY　　－ダークグレ
    //  BLACK - 黒　　　　　RED　　　　　　　－赤
    //  PINK  - ピンク　　　ORANGE　　　　　－オレンジ
    //  YELLOW- 黄　　　　　GREEN　　　　　―緑
    //  MAGENTA-マゼンダ　　CYAN　　　　　　－シアン
    //  BLUE-   青　
    // ★小文字でかいてもいいみたいです。
    g.setColor(Color.LIGHT_GRAY);//描画色をライトグレーに設定

    //描画位置を設定するための位置や高さを変数x,y,w,hにセット
    x=10;y=5;
    w=70;h=55;

    //以下の注釈文では、左からｘピクセル、上からｙピクセルの位置を（ｘ、ｙ）で示します。
    //ピクセル位置の指定が、学校でならった座標の上下がさかさまです。
    //(x,y)を左上として、幅ｗ、高さｈの長方形(塗りつぶしあり）を描画する。
    g.fillRect(x, y, w, h);

    g.setColor(Color.BLACK);//描画色を黒に
    //(x,y)を左上として、幅ｗ+1、高さｈ+1の長方形（塗りつぶし無）を描画する。
    //★塗りつぶしなしの描画では、空白の幅や高さを指定するので、全体の幅は増えまう
    g.drawRect(x, y, w, h);

    g.setColor(Color.DARK_GRAY);//描画色をダークグレに

    //角の丸みのパラメータに使う変数をセット
    dw=w/2;
    dh=h/2;
    //(x,y)を左上として、幅ｗ、高さｈの角に丸みがある長方形(塗りつぶしあり）を描画する。
    // 丸みは縦方向rw,よこ方向rhの長方形に内接する楕円の縁とします。
    g.fillRoundRect(x, y, w,h, dw, dh);

    g.setColor(Color.GREEN);//描画色を緑に変更

    //(x,y)を左上として、幅ｗ、高さｈの角に丸みがある長方形(塗りつぶし無）を描画する。
    // 丸みは縦方向rw,よこ方向rhの長方形に内接する楕円の縁とします。
    g.drawRoundRect(x, y, w,h, dw, dh);

    g.setColor(Color.WHITE);//描画色を白に変更

    //終点(x2,y2)を計算
    x2=x+w;
    y2=y+h;

    g.drawLine(x,y,x2,y2);//(x,y)-(x2,y2)の線を引く

    x=100;//描画場所を移動
    g.setColor(Color.RED);//描画色を緑に変更

    //(x,y)を左上として、幅ｗ+1、高さｈ+1の浮き出て見える長方形（塗りつぶし無）を描画する。
    g.draw3DRect(x, y, w, h, true);

    x=200;//描画場所を移動
    //(x,y)を左上として、幅ｗ、高さｈの浮き出て見える長方形（塗りつぶし有り）を描画する。
    g.fill3DRect(x, y, w, h, true);

    y=75;//描画場所を移動
    //(x,y)を左上として、幅ｗ+1、高さｈ+1の沈みこんで見える長方形（塗りつぶし無）を描画する。
    g.draw3DRect(x, y, w, h, false);

    x=100;//描画場所を移動
    //(x,y)を左上として、幅ｗ、高さｈの沈みこんで見える長方形（塗りつぶし有り）を描画する。
    g.fill3DRect(x, y, w, h, false);

    x=10;//描画場所を移動
    g.setColor(Color.PINK);//描画色をピンクに変更

    //(x,y)を左上として、幅ｗ、高さｈ長方形に内接する楕円（塗りつぶし有り）を描画する。
    g.fillOval(x, y, w, h);

    g.setColor(Color.RED);//描画色を赤に変更

    //(x,y)を左上として、幅ｗ+1、高さｈ+1長方形に内接する楕円（塗りつぶし無）を描画する。
    //★ピンクと赤の間に隙間があるのに注目してください
    g.drawOval(x, y, w, h);

    y=140;//描画位置の移動
    g.setColor(Color.YELLOW);//描画色を黄色に変更

    //(x1,y1)を左上として、幅ｗ、高さｈ長方形に内接する楕円扇（塗りつぶし有り）を描画する。
    // 開始角度45度、描画角度180度（単位は度です）
    g.fillArc(x, y, w, h, 45, 180);
    g.setColor(Color.ORANGE);

    //(x1,y1)を左上として、幅ｗ+1、高さｈ+1長方形に内接する楕円扇（塗りつぶし無り）を描画する。
    // 開始角度45度、描画角度180度（単位は度です）
    //★大きさが塗りつぶしありと少し違うのに注目しいてくだい。
    g.drawArc(x, y, w, h, 225, 180);

    x=100;//描画位置の移動

    //三角形を描画するための点の計算
    px[0]=x+w/2;py[0]=y;
    px[1]=x;py[1]=y+h;
    px[2]=x+w;py[2]=y+h;

    g.setColor(Color.CYAN);//色をシアンに変更

    // px,pyで指定される多角形を描く（塗りつぶしあり）を描く
    g.fillPolygon(px,py,3);

    g.setColor(Color.BLUE);//青をシアンに変更

    // px,pyで指定される多角形を描く（塗りつぶしなし）を描く
    g.drawPolygon(px,py,3);

    x=200;//描画位置の移動

    //三角形を描画するための点の計算
    px[0]=x+w/2;py[0]=y;
    px[1]=x;py[1]=y+h;
    px[2]=x+w;py[2]=y+h;

    // px,pyで指定される折れ線を描く（塗りつぶしなし）を描く
    // 閉じません
    g.drawPolyline(px,py,3);
  }
}
//
// WindowAdapterは、WindowListerの機能を持ちますが、
//　必要なメソッドだけを書けば、WindowListenerとして使えます
class Adapter  extends WindowAdapter {
    //右上の「×」クリックされると呼び出される
    public void windowClosing(WindowEvent e){
      System.exit(0);//アプリの終了
    }
}
