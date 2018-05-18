class A {
    protected int x=0;
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
    int y=0;
}
class main2 {
  public static void main(String argv[]) {
    B b=new B(10);
    b.print();
  }
}
