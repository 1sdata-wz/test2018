import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class test {
    public static void main(String[] args) {
    	Pattern p1 = Pattern.compile("^根据");
    	Matcher m = p1.matcher("根据《中华人民共和国婚姻法》第三十二条之规定，原告要求离婚之主张，不符合该规定的要求，依法不应支持");
		if(m.matches()){
			System.out.println(m.group(0));
		}else{
			System.out.println("12");
		}
	}
}
