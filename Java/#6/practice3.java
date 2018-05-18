import java.util.*;

class Lesson {
    private String name;
    private String teacher;
    private int max;
    private int num;
    private ArrayList<Student> st;
    
    public Lesson(String name,String teacher,int max){
	this.name = name;
	this.teacher = teacher;
	this.max = max;
	this.num = 0;
	st = new ArrayList<Student>();
    }
    public void add(Student s){
	int i=0;
	++num;
	if(st.isEmpty() == true){
	    st.add(s);
	    return;
	}
	for(Student std: st){
	    if(std.getId().compareTo(s.getId()) >= 0){ 
		break;
	    }
	    i++;
	}
	st.add(i,s);
    }
    public void print(){
	System.out.println("Lesson: "+name);
	System.out.println("Teacher: "+teacher);
	System.out.println("#Studnets: "+num);
	for(Student s: st){
	    s.print_short();
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
    public String getId(){
	return id;
    }
}

class SortIdStudent {
    public static void main(String argv[]){
	Lesson pro = new Lesson("Pro Enshu","Yanai",100);
	pro.add(new Student("01111","Suzuki",100));
	pro.add(new Student("01110", "Yamada", 80));
	pro.add(new Student("01221", "Tanaka", 78));
	pro.print();
    }
}
