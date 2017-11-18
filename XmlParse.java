package ac.shenkar.Calc;
import java.net.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;
import org.apache.log4j.*;

public class XmlParse implements Runnable{
	
	private NodeList name;
	private NodeList unit;
	private NodeList code;
	private NodeList country;
	private NodeList rate;
	private NodeList change;
	private DocumentBuilderFactory factory; 
	private DocumentBuilder builder;
	private URL url;
	private CurrencyTable currTable;
	private Logger log;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
	public XmlParse() throws IOException, ParserConfigurationException, SAXException, TransformerException
	{
		log=Logger.getLogger("CurrencyLogs");
		BasicConfigurator.configure();
		try{
			log.addAppender(new FileAppender(new SimpleLayout(),"log.txt"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();		
		url = new URL("http://www.boi.org.il/currency.xml");
		if(url == null)
		{
			System.out.println("error!");
			return;
		}
		init();

		currTable=new CurrencyTable(name.getLength()); 
	}
	
	@Override
	public void run() {
		try {
			init();
			Date d=new Date();
			log.info(sdf.format(d));
			int length = name.getLength();
			for (int i = 0; i < length; i++) {
				currTable.addElement(i, new Currency(
						name.item(i).getFirstChild().getNodeValue()
						,Integer.parseInt(unit.item(i).getFirstChild().getNodeValue())
						,code.item(i).getFirstChild().getNodeValue()
						,country.item(i).getFirstChild().getNodeValue()
						,Double.parseDouble(rate.item(i).getFirstChild().getNodeValue())
						,Double.parseDouble(change.item(i).getFirstChild().getNodeValue())));
				log.info(currTable.getRow(i).getRate());
			}
		} catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void init() throws IOException, ParserConfigurationException, SAXException, TransformerException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		LocalDate localDate = LocalDate.now();
		OutputStream fs=new FileOutputStream (".\\history\\"+dtf.format(localDate)+"_currency.xml");

		InputStream in = null;
		Document doc = null;
				
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();

		if (con!=null)
		{

			in = con.getInputStream();
			doc = builder.parse(in);			
			//read xml URL to history currency xml files
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer xform = tfactory.newTransformer();
			xform.transform(new DOMSource(doc), new StreamResult(fs));
			fs=new FileOutputStream ("cur_currency.xml");
			
			//read xml URL to update currency xml file
			tfactory = TransformerFactory.newInstance();
			xform = tfactory.newTransformer();
			xform.transform(new DOMSource(doc), new StreamResult(fs));
			con.disconnect();
		}
		
		//read xml data from local xml file
		in=new FileInputStream("cur_currency.xml");
		doc = builder.parse(in);
		name = doc.getElementsByTagName("NAME");
		unit = doc.getElementsByTagName("UNIT");
		code = doc.getElementsByTagName("CURRENCYCODE");
		country = doc.getElementsByTagName("COUNTRY");
		rate = doc.getElementsByTagName("RATE");
		change = doc.getElementsByTagName("CHANGE");
	
		con.disconnect();
		
	}	

	public CurrencyTable getCurrencyTable(){
		return currTable;
	}
	

}
