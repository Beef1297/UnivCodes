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
    public void sort_id(){
	int i,j;
	Student temp;//一時的に保存するためのものだから,変数を用意するだけでよいと考えた
	for(i = num;i > 1;i--){//bubble sort は資料のものを引用
	    for(j=1;j<i;j++){
		if(st[j-1].getId().compareTo(st[j].getId()) > 0  ){
		    temp = st[j-1]; st[j-1] = st[j]; st[j] = temp;
		}
	    }
	}
    }
    public void sort_name(){
	int i,j;
	Student temp;
	for(i = num;i > 1;i--){
	    for(j=1;j<i;j++){
		if(st[j-1].getName().compareTo(st[j].getName()) > 0  ){
		    temp = st[j-1]; st[j-1] = st[j]; st[j] = temp;
		}
	    }
	}
    }
    public void sort_grade(){
	int i,j;
	Student temp;
	for(i = num;i > 1;i--){
	    for(j=1;j<i;j++){
		if(st[j-1].getGrade() > st[j].getGrade()){//gradeはint型のため通常の二項演算子を用いた.
		    temp = st[j-1]; st[j-1] = st[j]; st[j] = temp;
		}
	    }
	}
    }
    public void print(){
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
 
    public String getId(){ return this.id; }
    public String getName(){ return this.name; }
    public int    getGrade(){return this.grade; }
    
    public void print_short() {
	System.out.println(id+","+name+","+grade);
    }
}

class main2_2 {
    public static void main(String argv[]){
	Lesson pro = new Lesson("Pro Enshu","Yanai",100);
	int i=3;
	pro.add(new Student("01111","Suzuki",100));
	pro.add(new Student("01110", "Yamada", 80));
	pro.add(new Student("01221", "Tanaka", 78));
	while(i > 0){//それぞれのsortの場合を示すためswitch文で場合分けした.
	    switch(i){
	    case 3: 
		pro.sort_id();
		break;
	    case 2:
		pro.sort_name();
		break;
	    case 1:
		pro.sort_grade();
		break;
	    default:
		break;
	    }
	    pro.print();
	    i--;
	}
    
    }
}
