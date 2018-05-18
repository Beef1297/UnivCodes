interface Photo{
    public void TakePhoto();
}
class Cellular implements Photo{
    public void MakeCall(){
	System.out.println("携帯電話で電話を掛けました.");
    }
    public void TakePhoto(){
	System.out.println("携帯電話で写真を撮りました.");
    }
}

class Camera implements Photo {
    public void TakePhoto(){
	System.out.println("デジカメで写真を撮りました.");
    }
}

class VideoCam implements Photo {
    public void RecordMovie(){
	System.out.println("ビデオカメラで動画を撮影しました.");
    }
    public void TakePhoto(){
	System.out.println("ビデオカメラで写真を撮りました.");
    }
}

class iPhone implements Photo{
    public void TakePhoto(){
	System.out.println("iPhoneで写真を撮りました.");
    }
}
class Photographer {
    final static int MAX = 20;
    /*変数を宣言して定義*/
    private Photo l[] = new Photo[MAX];
    private int n = 0;
    
    public void add(Photo p){
	if (n >= MAX) return;
	l[n++] = p;
    }
    
    public void TakeAll(){
	for(int i=0;i<n;i++){
	    System.out.print("["+(i+1)+"]");
	    l[i].TakePhoto();
	}
    }
    public static void main(String argv[]){
	Photographer p = new Photographer();
	for(int i = 0;i < MAX; i++){
	    double r = Math.random();
	    if(r < 0.25)p.add(new Camera());
	    else if(r < 0.50)p.add(new Cellular());
	    else if(r < 0.75)p.add(new VideoCam());
	    else p.add(new iPhone());
	}
	p.TakeAll();
    }
	
}

