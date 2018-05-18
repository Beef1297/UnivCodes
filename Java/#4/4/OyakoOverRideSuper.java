class Oya4 {
  protected int x=0;
  public void setX(int x0){ x=x0; }
  public void printX() { 
    System.out.println("Oya: x="+x); 
  }
  public void plusX() { 
    x++; 
  }
}

class Kodomo4 extends Oya4 {
  public void plusX() { 
    super.plusX();
    x += 10; 
  }
}

class OyakoOverRideSuper {
  public static void main(String argv[]) {
    Kodomo4 k = new Kodomo4();  
    
    k.setX(10);  // Oya のフィールドに値をセット
    k.printX();  // Oyaのメソッドを直接呼び出し

    k.plusX();   // +1 と +10 のどちらでしょう？
    k.printX();  // Oyaのメソッドを直接呼び出し
  }
}
