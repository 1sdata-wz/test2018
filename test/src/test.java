import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class test {
    public static void main(String[] args) {
    	Pattern p1 = Pattern.compile("^����");
    	Matcher m = p1.matcher("���ݡ��л����񹲺͹�������������ʮ����֮�涨��ԭ��Ҫ�����֮���ţ������ϸù涨��Ҫ��������Ӧ֧��");
		if(m.matches()){
			System.out.println(m.group(0));
		}else{
			System.out.println("12");
		}
	}
}
