class Oya {
  private int x=0;
  public void setX(int x0){ x=x0; }
  public void printX() { 
    System.out.println("Oya: x="+x); 
  }
  public void plusX() { 
    x++; 
  }
}

class Kodomo extends Oya {
  public void printX1() { 
    System.out.print("Kodomo: "); 
    printX();
  }
}

class Oyako {
  public static void main(String argv[]) {
    Kodomo k = new Kodomo();  
    
    k.setX(10);  // Oya のフィールドに値をセット
    k.printX();  // Oyaのメソッドを直接呼び出し
    k.printX1(); // Kodomoのメソッド経由で結果を表示

    k.plusX();   // +1
    k.plusX();   // +1
    k.printX();  // Oyaのメソッドを直接呼び出し
    k.printX1(); // Kodomoのメソッド経由で結果を表示
  }
}
