class Oya2 {
  protected int x=0;
  public void setX(int x0){ x=x0; }
  public void printX() { 
    System.out.println("Oya: x="+x); 
  }
  public void plusX() { 
    x++; 
  }
}

class Kodomo2 extends Oya2 {
  public void printX1() { 
    System.out.print("Kodomo: "); 
    printX();
  }
  public void plusX10() { 
    x += 10; 
  }
}

class OyakoProtected {
  public static void main(String argv[]) {
    Kodomo2 k = new Kodomo2();  
    
    k.setX(10);  // Oya のフィールドに値をセット
    k.printX();  // Oyaのメソッドを直接呼び出し
    k.printX1(); // Kodomoのメソッド経由で結果を表示

    k.plusX();   // +1
    k.plusX10(); // +10
    k.printX();  // Oyaのメソッドを直接呼び出し
    k.printX1(); // Kodomoのメソッド経由で結果を表示
  }
}
