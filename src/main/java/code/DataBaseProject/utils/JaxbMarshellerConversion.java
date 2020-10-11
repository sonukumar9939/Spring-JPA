package code.DataBaseProject.utils;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import code.DataBaseProject.models.EkaInvoiceDocument;

public class JaxbMarshellerConversion {

	public static void main(String[] args) {
		
		
	 MarshellJavaObjectToXML();
	 MarShellXMLToJavaObject();

}

	private static void MarShellXMLToJavaObject() {
		try{
		    //getting the xml file to read
		    File file = new File("E:\\Studies\\github\\Spring-JPA\\ekaInvoiceDocument.xml");
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(EkaInvoiceDocument.class);
		    //creating the unmarshall object
		    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		    //calling the unmarshall method
		    EkaInvoiceDocument ekaInvoiceDocument=(EkaInvoiceDocument) unmarshallerObj.unmarshal(file);
		    System.out.println(ekaInvoiceDocument.toString());
		}catch(Exception e){
		    e.printStackTrace();
		}
	}

	private static void MarshellJavaObjectToXML() {
		try {
			// creating the JAXB context
			JAXBContext jContext = JAXBContext.newInstance(EkaInvoiceDocument.class);
			// creating the marshaller object
			Marshaller marshallObj = jContext.createMarshaller();
			// setting the property to show xml format output
			marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// setting the values in POJO class
			EkaInvoiceDocument ekaInvoiceDocument = new EkaInvoiceDocument("EkaMessageHeader$", "EKAdocumentHeader",
					"EKAinvoiceHeader", "EKAinvoiceItem", "EKAinvoiceAmount", "EKAtaxDetails",
					"EKApaymentInstructionsForCPBank", "EKApaymentInstructionsForOurBank", "EKApaymentSplitDetails");
			// calling the marshall method
			marshallObj.marshal(ekaInvoiceDocument, new FileOutputStream("E:\\Studies\\github\\Spring-JPA\\ekaInvoiceDocument.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	}
