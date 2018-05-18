
// 機能限定版

interface Photo {
    void TakePhoto();
}
class Cellular implements Photo {
    public void MakeCall() {
      System.out.println("携帯電話で電話を掛けました．");
    }
    public void TakePhoto() {
      System.out.println("携帯電話で写真を撮りました．");
    }
}

class Camera implements Photo {
    public void TakePhoto() {
      System.out.println("デジカメで写真を撮りました．");
    }
}

class VideoCam implements Photo {
    public void RecordMovie() {
      System.out.println("ビデオカメラで動画を撮影しました．");
    }
    public void TakePhoto() {
      System.out.println("ビデオカメラで写真を撮りました．");
    }
}

class Photographer {
   final static int MAX=20; // final static int は定数の定義です．
   private Photo camera[] = new Photo[MAX];
   private int num=0;
   
   public void add(Photo p){
     if (num>=MAX) return; 
     camera[num++]=p;
   }

   public void TakeAll(){
     // 1枚しか撮影しません．各自,改変してください．
       camera[0].TakePhoto();
   }

   public static void main(String argv[]){
      Photographer p=new Photographer();
      p.add(new Cellular());  // 1つだけしか登録しません．
      p.TakeAll();
   }
}


