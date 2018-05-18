import java.awt.Font;
import java.awt.GraphicsEnvironment;


public class FontsList {
	Font [] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

	FontsList(String as[]){
		char dspchr=0;
		int fnumber=fonts.length;
		int sp,n=0;
		if (as.length>0) dspchr=as[0].charAt(0);

		for (int i=0;i<fnumber;++i){
			if (dspchr!=0) {
				if (!fonts[i].canDisplay(dspchr)) continue;
			}
//			if (!fonts[i].canDisplay('A')) continue;
			String fns=fonts[i].getName();

			if (-1!=fns.indexOf("bold")) continue;
			if (-1!=fns.indexOf("Bold")) continue;
			if (-1!=fns.indexOf("italic")) continue;
			if (-1!=fns.indexOf("Italic")) continue;
			if (-1!=fns.indexOf("イタリック")) continue;
			if (-1!=fns.indexOf("ボールド")) continue;
			sp=fns.indexOf(".plain");
			if (-1!=sp) fns=fns.substring(0,sp);
			sp=fns.indexOf(" Regular");
			if (-1!=sp) fns=fns.substring(0,sp);
			sp=fns.indexOf("-Regular");
			if (-1!=sp) fns=fns.substring(0,sp);

			System.out.println(fns);
			++n;

		}// end for
	
		System.out.println(String.format("\n# Number of fonts=%d/%d", n,fnumber));
	}

	public static void main(String[] args) {
		new FontsList(args);
	}

}
