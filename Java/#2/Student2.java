class Lesson {
    private String name;
    private String teacher;
    private int max;
    private int num;
    private Student st[];
    
    public Lesson(String name,String teacher,int max){
	this.name = name;
	this.teacher = teacher;
	this.max = max;
	this.num = 0;
	st = new Student[max];
    }
    public void add(Student s){
	st[this.num++] = s;
    }
    public void print(){
	/*基本的に,講義,資料通りコードを書いたためそこまで頭を使う部分がなかった*/
	System.out.println("Lesson: "+name);
	System.out.println("Teacher: "+teacher);
	System.out.println("#Studnets: "+num);
	for(int i=0;i < num;i++){//num は人数を表しているから扱いには注意した
	    st[i].print_short();
	}
	System.out.println("-----------");
    }
    
}
class Student {
    private String id;    // 学籍番号
    private String name;  // 名前
    private int grade;    // 成績
    public Student(String id,String name, int grade){
	this.id = id; this.name = name; 
	if(grade < 0)grade = 0;
	else if(grade > 100)grade = 100;
	this.grade = grade;
    }
 
    public void print_short() {
	System.out.println(id+","+name+","+grade);
    }
}

class main2_2 {
    public static void main(String argv[]){
	Lesson pro = new Lesson("Pro Enshu","Yanai",100);
	pro.add(new Student("01110","Suzuki",100));
	pro.add(new Student("01111", "Yamada", 80));
	pro.add(new Student("01221", "Tanaka", 78));
	pro.print();
    }
}
