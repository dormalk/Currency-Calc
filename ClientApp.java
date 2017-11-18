package ac.shenkar.Calc;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;
public class ClientApp {

	private static CalculatorGUI cg;
	public static void main(String[] args) throws TransformerException {
		// TODO Auto-generated method stub
System.out.println("Hello");
System.out.print("Walla");
		cg=new CalculatorGUI ();
		cg.getFrame().setVisible(true);
	}

}
