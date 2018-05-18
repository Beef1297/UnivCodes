class Student {
    private String id;
    private String name;
    private int grade;
    
    public void Print(){
	System.out.println("ID     :"+this.id);
	System.out.println("Name   :"+this.name);
	System.out.println("Grade  :"+this.grade);
    }
    public void setId(String id){
	this.id = id;
    }
    public void setName(String name){
	this.name = name;
    }
    public void setGrade(int grade){
	this.grade = grade;
    }
}

class Student3 extends Student{
    private String course;
    
    public void setCourse(String course){
	this.course = course;
    }
    @Override public void Print(){
	super.Print();
	System.out.println("course :"+this.course);
    }
}
class Student4 extends Student3{
    private String supervisor;
    
    public void setSupervisor(String supervisor){
	this.supervisor = supervisor;
    }
    @Override public void Print(){
	super.Print();
	System.out.println("supervisor:"+supervisor);
    }
}

class OVERRIDE{
    public static void main(String argv[]){
	Student3 st3 = new Student3();
	Student4 st4 = new Student4();
	
	st3.setId("123456");st3.setName("Edwin");
	st3.setGrade(90); st3.setCourse("メディア情報学");

	st4.setId("134567");st4.setName("Levis");
	st4.setGrade(80); st4.setCourse("経営情報学");
	st4.setSupervisor("Dr.pepper");

	System.out.println("[3年生]");
	st3.Print();
	System.out.println("[4年生]");
	st4.Print();
    }
}
