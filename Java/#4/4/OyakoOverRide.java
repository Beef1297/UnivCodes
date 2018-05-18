class Oya3 {
  protected int x=0;
  public void setX(int x0){ x=x0; }
  public void printX() { 
    System.out.println("Oya: x="+x); 
  }
  public void plusX() { 
    x++; 
  }
}

class Kodomo3 extends Oya3 {
  public void plusX() { 
    x += 10; 
  }
}

class OyakoOverRide {
  public static void main(String argv[]) {
    Kodomo3 k = new Kodomo3();  
    
    k.setX(10);  // Oya のフィールドに値をセット
    k.printX();  // Oyaのメソッドを直接呼び出し

    k.plusX();   // +1 と +10 のどちらでしょう？
    k.printX();  // Oyaのメソッドを直接呼び出し
  }
}
