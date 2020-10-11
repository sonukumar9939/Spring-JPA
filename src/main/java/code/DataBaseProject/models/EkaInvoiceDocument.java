package code.DataBaseProject.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "messageHeader",
    "documentHeader",
    "invoiceHeader",
    "invoiceItem",
    "invoiceAmount",
    "taxDetails",
    "paymentInstructionsForCPBank",
    "paymentInstructionsForOurBank",
    "paymentSplitDetails"
})

@XmlRootElement(name = "EkaInvoiceDocument")
public class EkaInvoiceDocument {

	@XmlElement(name = "DocumentHeader", required = true)
	protected String documentHeader;
	@XmlElement(name = "MessageHeader", required = true)
	protected String messageHeader;
	@XmlElement(name = "InvoiceHeader", required = true)
	protected String invoiceHeader;
	@XmlElement(name = "InvoiceItem", required = true)
	protected String invoiceItem;
	@XmlElement(name = "InvoiceAmount", required = true)
	protected String invoiceAmount;
	@XmlElement(name = "TaxDetails")
	protected String taxDetails;
	@XmlElement(name = "PaymentInstructionsForCPBank")
	protected String paymentInstructionsForCPBank;
	@XmlElement(name = "PaymentInstructionsForOurBank")
	protected String paymentInstructionsForOurBank;
	@XmlElement(name = "PaymentSplitDetails")
	protected String paymentSplitDetails;
	
	public EkaInvoiceDocument() {
		super();
	}
	public EkaInvoiceDocument(String messageHeader, String documentHeader, String invoiceHeader, String invoiceItem,
			String invoiceAmount, String taxDetails, String paymentInstructionsForCPBank,
			String paymentInstructionsForOurBank, String paymentSplitDetails) {
		super();
		this.messageHeader = messageHeader;
		this.documentHeader = documentHeader;
		this.invoiceHeader = invoiceHeader;
		this.invoiceItem = invoiceItem;
		this.invoiceAmount = invoiceAmount;
		this.taxDetails = taxDetails;
		this.paymentInstructionsForCPBank = paymentInstructionsForCPBank;
		this.paymentInstructionsForOurBank = paymentInstructionsForOurBank;
		this.paymentSplitDetails = paymentSplitDetails;
	}
	
	public String getMessageHeader() {
		return messageHeader;
	}
	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}
	public String getDocumentHeader() {
		return documentHeader;
	}
	public void setDocumentHeader(String documentHeader) {
		this.documentHeader = documentHeader;
	}
	public String getInvoiceHeader() {
		return invoiceHeader;
	}
	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}
	public String getInvoiceItem() {
		return invoiceItem;
	}
	public void setInvoiceItem(String invoiceItem) {
		this.invoiceItem = invoiceItem;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getTaxDetails() {
		return taxDetails;
	}
	public void setTaxDetails(String taxDetails) {
		this.taxDetails = taxDetails;
	}
	public String getPaymentInstructionsForCPBank() {
		return paymentInstructionsForCPBank;
	}
	public void setPaymentInstructionsForCPBank(String paymentInstructionsForCPBank) {
		this.paymentInstructionsForCPBank = paymentInstructionsForCPBank;
	}
	public String getPaymentInstructionsForOurBank() {
		return paymentInstructionsForOurBank;
	}
	public void setPaymentInstructionsForOurBank(String paymentInstructionsForOurBank) {
		this.paymentInstructionsForOurBank = paymentInstructionsForOurBank;
	}
	public String getPaymentSplitDetails() {
		return paymentSplitDetails;
	}
	public void setPaymentSplitDetails(String paymentSplitDetails) {
		this.paymentSplitDetails = paymentSplitDetails;
	}
	@Override
	public String toString() {
		return "EkaInvoiceDocument [messageHeader=" + messageHeader + ", documentHeader=" + documentHeader
				+ ", invoiceHeader=" + invoiceHeader + ", invoiceItem=" + invoiceItem + ", invoiceAmount="
				+ invoiceAmount + ", taxDetails=" + taxDetails + ", paymentInstructionsForCPBank="
				+ paymentInstructionsForCPBank + ", paymentInstructionsForOurBank=" + paymentInstructionsForOurBank
				+ ", paymentSplitDetails=" + paymentSplitDetails + "]";
	}

	
	

}
