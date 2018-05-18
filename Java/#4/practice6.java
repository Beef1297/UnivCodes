interface I{ }
class S { }
class C extends S implements I { }

class AssignTest{
    I i;
    S s;
    C c;
    void g1(I i) { }
    void g2(S s) { }
    void g3(C c) { }
    void f() {
	i = new I();//13error
	s = new I();//14error
	c = new I();//15error
	i = new S();//16error
	s = new S();
	c = new S();//18error
	i = new C();
	s = new C();
	c = new C();
	g1(i);
	g2(i);//23error
	g3(i);//24error
	g1(s);//25error
	g2(s);
	g3(s);//26error
	g1(c);
	g2(c);
	g3(c);
    }
}
