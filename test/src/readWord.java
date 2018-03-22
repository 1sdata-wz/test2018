import java.io.IOException;

import org.apache.poi.POITextExtractor;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class readWord {
	//修改了部分内容
    public static void main(String args[]){
    	OPCPackage in;
		try {
			in = POIXMLDocument.openPackage("C://Users//Administrator//Desktop//1//upload_063bc82a_dfa2_4276_95de_0e0a8654fd43_00000006.tmp");
			XWPFDocument xwpf = new XWPFDocument(in);
	    	POITextExtractor extractor = new XWPFWordExtractor(xwpf);
	    	System.out.println(extractor.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
