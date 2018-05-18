class A {
    private int x=0;
    public A() {
        x = 10;
    }
    public A(int n) {
        x = n;
    }
    public void print() {
        System.out.println(x);
    }    
}
class B extends A {
    int a;
    public B(int n) {
      a=0;
      super(n);  // 親クラスのコンストラクタ A(n) が呼び出される．
      // ここには Bクラスでの初期設定などを書く．
    }
}
