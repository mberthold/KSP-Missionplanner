import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;

public class Mission {
	
	private float MUNCHFACTOR = 1.8f;
	private float LENGTH_OF_DAY = 6.0f;
	private int DURATION = 1;
	private int CREW = 1;
	
	private ArrayList<Converter> availableConverters = new ArrayList<Converter>();
	private ArrayList<Converter> usedConverters = new ArrayList<Converter>();
	
	public Mission() {
		System.out.println("Creating new Mission...");
		
		getScenario();
	}
	
	public static void main(String arg[]) {
		new Mission();
		
		System.out.println("Terminated.");
	}
	
	private void getScenario() {
		String filename;
		
		// Get filename from user.
		// If input string does not end with ".xml" use default scenario file
		System.out.println("Enter scenario-file...");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			if (s.endsWith(".xml")){
				filename = s;
			}
			else {
				filename = "scenario.xml";
			}
		} catch (IOException e){
			System.out.println("IOExpetion! Could not read string!");
			filename = "scenario.xml";
		}
		
		// After we have sorted our filename let's open the actual file!
		String basePath = new File("").getAbsolutePath();
		try {
			File scen = new File(basePath+"/res/"+filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(scen);
	        doc.getDocumentElement().normalize();
	        NodeList nLst = doc.getElementsByTagName("*");
	        Node munch = nLst.item(1);
	        Node length_of_day = nLst.item(2);
	        
	        this.MUNCHFACTOR = Float.parseFloat(munch.getTextContent());
	        this.LENGTH_OF_DAY = Float.parseFloat(length_of_day.getTextContent());
	        
	        //System.out.println("Munch = "+this.MUNCHFACTOR);
	        //System.out.println("Length of day: "+this.LENGTH_OF_DAY);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
