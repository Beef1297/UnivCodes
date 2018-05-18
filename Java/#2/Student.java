class Student {
    String id;    // 学籍番号
    String name;  // 名前
    int grade;    // 成績

    void print() {
      System.out.println("ID   : "+id);  
      System.out.println("Name : "+name);
      System.out.println("Grade: "+grade);
    }
}

class main {

    public static void main(String argv[]){
      Student st = new Student();
      st.id="01110";
      st.name="Suzuki";
      st.grade=100;
    
      st.print();
    }
}
