 class Student3 extends Student {
      private String course; // コース名

      void print() {
        super.print();  // super で１つ上の親クラスのprint() を呼び出す．
        System.out.println("Course: "+course); // コース名の表示の追加 
      }
      void setCourse(String c){
        course=c;
      }
  }
  class Student {
      private String id;    // 学籍番号
      private String name;  // 名前
      private int grade;    // 成績
     
      void print() {
        System.out.println("ID   : "+id);  
        System.out.println("Name : "+name);
        System.out.println("Grade: "+grade);
      }
      void setId(String i){
        id=i;
      }
      void setName(String n){
        name=n;
      }
      void setGrade(int g){
        grade=g;
      }
  }

 class Student3_main {
       public static void main(String argv[]){
         Student3 st3 = new Student3();

	 st3.setId("123456");         st3.setName("電通 太郎");
         st3.setGrade(100);      st3.setCourse("メディア情報学コース");

         st3.print();
       }
   }
