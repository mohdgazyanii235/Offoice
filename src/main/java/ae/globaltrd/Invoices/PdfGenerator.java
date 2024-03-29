package ae.globaltrd.Invoices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

public class PdfGenerator {

  private Invoice invoice;
  private String storageLocation;
  private HSSFWorkbook workbook;
  private HSSFSheet sheet;
  private int value;
  private double finalTotal;

  public PdfGenerator(Invoice invoice, String storageLocation) {
    this.invoice = invoice;
    this.storageLocation = storageLocation;
    this.workbook = new HSSFWorkbook();
    this.sheet = this.workbook.createSheet();
    this.value = 0;
  }

  private int getNext() {
    return this.value += 1;
  }

//  private String nameToPdf(String name) {
//    String finalString = name.substring(0, name.length() - 3) + "pdf";
//    System.out.println(finalString);
//    return finalString;
//  }

  private void setTitle() throws IOException {
    CellStyle titleStyle = this.workbook.createCellStyle();
    Font titleFont = this.workbook.createFont();
    titleFont.setBold(true);
    titleFont.setFontHeightInPoints((short) 14);
    titleFont.setFontName("Verdana");
    titleStyle.setFont(titleFont);
    titleStyle.setAlignment(HorizontalAlignment.CENTER);



    int titleRow = this.getNext();
    // Set title of Invoice.
    this.sheet.createRow(titleRow);
    this.sheet.getRow(titleRow).createCell(2).setCellValue("Global Trading FZC");
    this.sheet.getRow(titleRow).getCell(2).setCellStyle(titleStyle);


  }

  private void setSubTitle() {
    CellStyle subTitleStyle = this.workbook.createCellStyle();
    Font subTitleFont = this.workbook.createFont();
    subTitleFont.setBold(true);
    subTitleFont.setFontHeightInPoints((short) 11);
    subTitleFont.setFontName("Arial");
    subTitleStyle.setFont(subTitleFont);
    subTitleStyle.setAlignment(HorizontalAlignment.CENTER);

    int subTitleRow = this.getNext();
    // Set sub title of Invoice.
    this.sheet.createRow(subTitleRow);
    this.sheet.getRow(subTitleRow).createCell(2).setCellValue("P.O.BOX 117353, RAK-FTZ(UAE)");
    this.sheet.getRow(subTitleRow).getCell(2).setCellStyle(subTitleStyle);

  }

  private void setDocumentTypeTitle() {
    CellStyle documentTypeTitleStyle = this.workbook.createCellStyle();
    Font documentTypeTitleFont = this.workbook.createFont();
    documentTypeTitleFont.setBold(true);
    documentTypeTitleFont.setFontHeightInPoints((short) 10);
    documentTypeTitleFont.setFontName("Arial");
    documentTypeTitleStyle.setFont(documentTypeTitleFont);
    documentTypeTitleStyle.setAlignment(HorizontalAlignment.CENTER);

    this.getNext(); // waste the 3rd row.
    int documentTypeTitleRow = this.getNext();
    this.sheet.createRow(documentTypeTitleRow);
    this.sheet.getRow(documentTypeTitleRow).createCell(2).setCellValue("Invoice");
    this.sheet.getRow(documentTypeTitleRow).getCell(2).setCellStyle(documentTypeTitleStyle);

  }

  private void setCompanyAddress() {
    CellStyle companyNameStyle = this.workbook.createCellStyle();
    Font companyNameFont = this.workbook.createFont();
    companyNameFont.setBold(true);
    companyNameFont.setFontHeightInPoints((short) 10);
    companyNameFont.setFontName("Arial");
    companyNameStyle.setFont(companyNameFont);
    companyNameStyle.setAlignment(HorizontalAlignment.LEFT);

    CellStyle companyAddressStyle = this.workbook.createCellStyle();
    Font companyAddressFont = this.workbook.createFont();
    companyAddressFont.setBold(false);
    companyAddressFont.setFontHeightInPoints((short) 10);
    companyAddressFont.setFontName("Arial");
    companyAddressStyle.setFont(companyAddressFont);
    companyAddressStyle.setAlignment(HorizontalAlignment.LEFT);


    // Set company name.
    this.getNext(); // waste the 5th row.
    int companyNameRow = this.getNext();
    this.sheet.createRow(companyNameRow);
    this.sheet.getRow(companyNameRow).createCell(1)
        .setCellValue(this.invoice.getInvoiceeName().toUpperCase());
    this.sheet.getRow(companyNameRow).getCell(1).setCellStyle(companyNameStyle);

    // Set company address.
    ArrayList<String> companyAddress = this.invoice.getInvoiceeAddress();
    for (int i = 0; i < companyAddress.size(); i++) {
      int addressRow = this.getNext();
      this.sheet.createRow(addressRow);
      this.sheet.getRow(addressRow).createCell(1).setCellValue(companyAddress.get(i).toUpperCase());
      this.sheet.getRow(addressRow).getCell(1).setCellStyle(companyAddressStyle);
    }
  }

  private void setDeliveryInformation() {
    CellStyle deliveryInformationStyle = this.workbook.createCellStyle();
    Font deliveryInformationFont = this.workbook.createFont();
    deliveryInformationFont.setBold(true);
    deliveryInformationFont.setFontHeightInPoints((short) 10);
    deliveryInformationFont.setFontName("Arial");
    deliveryInformationStyle.setFont(deliveryInformationFont);
    deliveryInformationStyle.setAlignment(HorizontalAlignment.LEFT);

    this.getNext();
    this.getNext();

    int podRow = this.getNext();
    int containerRow = this.getNext();

    this.sheet.createRow(podRow);
    this.sheet.getRow(podRow).createCell(1)
        .setCellValue("P.O.D.: " + this.invoice.getPortOfDocking().toUpperCase());
    this.sheet.getRow(podRow).getCell(1).setCellStyle(deliveryInformationStyle);

    this.sheet.createRow(containerRow);
    this.sheet.getRow(containerRow).createCell(1)
        .setCellValue("Containers: " + this.invoice.getContainerDetails().toUpperCase());
    this.sheet.getRow(containerRow).getCell(1).setCellStyle(deliveryInformationStyle);
  }

  private void setInvoicerInformation() {
    CellStyle invoicerInformationStyle = this.workbook.createCellStyle();
    Font invoicerInformationFont = this.workbook.createFont();
    invoicerInformationFont.setBold(true);
    invoicerInformationFont.setFontHeightInPoints((short) 9);
    invoicerInformationFont.setFontName("Verdana");
    invoicerInformationStyle.setFont(invoicerInformationFont);
    invoicerInformationStyle.setAlignment(HorizontalAlignment.LEFT);


    CellStyle invoiceAndBlNoStyle = this.workbook.createCellStyle();
    Font invoiceAndBlNoFont = this.workbook.createFont();
    invoiceAndBlNoFont.setBold(true);
    invoiceAndBlNoFont.setFontHeightInPoints((short) 10);
    invoiceAndBlNoFont.setFontName("Arial");
    invoiceAndBlNoFont.setUnderline(Font.U_SINGLE);
    invoiceAndBlNoStyle.setFont(invoiceAndBlNoFont);
    invoiceAndBlNoStyle.setAlignment(HorizontalAlignment.LEFT);

    CellStyle dateStyle = this.workbook.createCellStyle();
    Font dateFont = this.workbook.createFont();
    dateFont.setBold(false);
    dateFont.setFontHeightInPoints((short) 10);
    dateFont.setFontName("Arial");
    dateStyle.setFont(dateFont);
    dateStyle.setAlignment(HorizontalAlignment.LEFT);


    this.value = 5; // bring value back next to the company name;
    int phoneNumbers = this.getNext();
    this.sheet.getRow(phoneNumbers).createCell(3).setCellValue("TEL NO.: 04-2248795");
    this.sheet.getRow(phoneNumbers).getCell(3).setCellStyle(invoicerInformationStyle);
    phoneNumbers = this.getNext();
    this.sheet.getRow(phoneNumbers).createCell(3).setCellValue("FAX NO.: 04-2248864");
    this.sheet.getRow(phoneNumbers).getCell(3).setCellStyle(invoicerInformationStyle);

    this.getNext();
    int moreData = this.getNext();
    this.sheet.createRow(moreData).createCell(3).setCellValue("EMAIL: gltrd@eim.ae");
    this.sheet.getRow(moreData).getCell(3).setCellStyle(invoicerInformationStyle);
    moreData = this.getNext();
    this.sheet.createRow(moreData).createCell(3)
        .setCellValue("Invoice No: " + this.invoice.getInvoiceNumber());
    this.sheet.getRow(moreData).getCell(3).setCellStyle(invoiceAndBlNoStyle);
    moreData = this.getNext();
    this.sheet.getRow(moreData).createCell(3).setCellValue("Date: " + this.invoice.getDate());
    this.sheet.getRow(moreData).getCell(3).setCellStyle(dateStyle);
    moreData = this.getNext();
    this.sheet.getRow(moreData).createCell(3).setCellValue("BL NO.: " + this.invoice.getBlNumber());
    this.sheet.getRow(moreData).getCell(3).setCellStyle(invoiceAndBlNoStyle);

  }

  private void setFieldNames() {
    CellStyle fieldNamesStyle = this.workbook.createCellStyle();
    Font fieldNamesFont = this.workbook.createFont();
    fieldNamesFont.setBold(true);
    fieldNamesFont.setFontHeightInPoints((short) 12);
    fieldNamesFont.setFontName("Calibri");
    fieldNamesStyle.setFont(fieldNamesFont);
    fieldNamesStyle.setAlignment(HorizontalAlignment.CENTER);
    fieldNamesStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    fieldNamesStyle.setBorderBottom(BorderStyle.THICK);


    this.getNext(); // waste another row.
    String[] fieldNames = {"Sr.No", "Description of Goods", "RATE", "QTY", "Amount"};

    int fieldNamesRow = this.getNext();
    this.sheet.createRow(fieldNamesRow);
    for (int i = 0; i < 5; i++) {
      this.sheet.getRow(fieldNamesRow).createCell(i).setCellValue(fieldNames[i]);
      this.sheet.getRow(fieldNamesRow).getCell(i).setCellStyle(fieldNamesStyle);
    }
  }


  private void setEntryValues() {
    CellStyle entryValuesStyle = this.workbook.createCellStyle();
    Font entryValuesFont = this.workbook.createFont();
    entryValuesFont.setBold(true);
    entryValuesFont.setFontHeightInPoints((short) 11);
    entryValuesFont.setFontName("Calibri");
    entryValuesStyle.setFont(entryValuesFont);
    entryValuesStyle.setAlignment(HorizontalAlignment.CENTER);
    entryValuesStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    entryValuesStyle.setWrapText(true);



    ArrayList<InvoiceEntry> invoiceEntries = this.invoice.getInvoiceEntries();
    this.getNext(); // waste a row.
    for (int i = 0; i < invoiceEntries.size(); i++) {
      int currentRow = this.getNext();
      this.sheet.createRow(currentRow);
      int colNumber = 0;
      while (colNumber < 5) {
        switch (colNumber) {
          case 0:
            this.sheet.getRow(currentRow).createCell(colNumber).setCellValue(i + 1);
            break;
          case 1:
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceEntries.get(i).getProductName());
            break;
          case 2:
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceEntries.get(i).getProductPrice());
            break;
          case 3:
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceEntries.get(i).getProductQuantity());
            break;
          case 4:
            this.finalTotal += invoiceEntries.get(i).getTotal();
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceEntries.get(i).getTotal());
            break;
        }
        this.sheet.getRow(currentRow).getCell(colNumber).setCellStyle(entryValuesStyle);
        colNumber++;
      }
    }
  }

  private void setChargeValues() {
    CellStyle chargeValuesStyle = this.workbook.createCellStyle();
    Font chargeValuesFont = this.workbook.createFont();
    chargeValuesFont.setBold(true);
    chargeValuesFont.setFontHeightInPoints((short) 11);
    chargeValuesFont.setFontName("Calibri");
    chargeValuesStyle.setFont(chargeValuesFont);
    chargeValuesStyle.setAlignment(HorizontalAlignment.CENTER);
    chargeValuesStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    chargeValuesStyle.setWrapText(true);


    this.getNext();
    this.getNext();
    this.getNext();

    ArrayList<InvoiceCharge> invoiceCharges = this.invoice.getInvoiceCharges();
    this.getNext(); // waste a row.
    for (int i = 0; i < invoiceCharges.size(); i++) {
      int currentRow = this.getNext();
      this.sheet.createRow(currentRow);
      int colNumber = 0;
      while (colNumber < 5) {
        switch (colNumber) {
          case 0:
            this.sheet.getRow(currentRow).createCell(colNumber).setCellValue(i + 1);
            break;
          case 1:
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceCharges.get(i).getChargeName());
            break;
          case 2:
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceCharges.get(i).getChargeCost());
            break;
          case 3:
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceCharges.get(i).getChargeQuantity());
            break;
          case 4:
            this.finalTotal += invoiceCharges.get(i).getTotal();
            this.sheet.getRow(currentRow).createCell(colNumber)
                .setCellValue(invoiceCharges.get(i).getTotal());
            break;
        }
        this.sheet.getRow(currentRow).getCell(colNumber).setCellStyle(chargeValuesStyle);
        colNumber++;
      }
    }


  }


  public void setFinalTotal() {
    CellStyle finalTotalStyle = this.workbook.createCellStyle();
    Font finalTotalFont = this.workbook.createFont();
    finalTotalFont.setBold(true);
    finalTotalFont.setFontHeightInPoints((short) 11);
    finalTotalFont.setFontName("Calibri");
    finalTotalStyle.setFont(finalTotalFont);
    finalTotalStyle.setAlignment(HorizontalAlignment.CENTER);
    finalTotalStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    finalTotalStyle.setBorderBottom(BorderStyle.THICK);
    finalTotalStyle.setBorderTop(BorderStyle.THICK);


    this.getNext(); // waste a line.
    this.getNext();


    int finalTotalRow = this.getNext();

    this.sheet.createRow(finalTotalRow);
    this.sheet.getRow(finalTotalRow).createCell(0).setCellValue("TOTAL");
    this.sheet.getRow(finalTotalRow).getCell(0).setCellStyle(finalTotalStyle);
    this.sheet.addMergedRegion(new CellRangeAddress(finalTotalRow, finalTotalRow, 1, 3));
    this.sheet.getRow(finalTotalRow).createCell(1);
    this.sheet.getRow(finalTotalRow).getCell(1).setCellStyle(finalTotalStyle);
    this.sheet.getRow(finalTotalRow).createCell(2);
    this.sheet.getRow(finalTotalRow).createCell(3);

    this.sheet.getRow(finalTotalRow).getCell(1).setCellStyle(finalTotalStyle);
    this.sheet.getRow(finalTotalRow).getCell(2).setCellStyle(finalTotalStyle);
    this.sheet.getRow(finalTotalRow).getCell(3).setCellStyle(finalTotalStyle);

    this.sheet.getRow(finalTotalRow).getCell(1)
        .setCellValue(SpellNumber.mainConvert(this.finalTotal));


    this.sheet.getRow(finalTotalRow).createCell(4).setCellValue(this.finalTotal);
    this.sheet.getRow(finalTotalRow).getCell(4).setCellStyle(finalTotalStyle);
  }

  public void addFooter() {
    CellStyle footerStyle = this.workbook.createCellStyle();
    Font footerFont = this.workbook.createFont();
    footerFont.setBold(true);
    footerFont.setFontHeightInPoints((short) 10);
    footerFont.setFontName("Arial");
    footerStyle.setFont(footerFont);
    footerStyle.setAlignment(HorizontalAlignment.CENTER);
    footerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    footerStyle.setWrapText(true);

    this.getNext();
    this.getNext();
    int footerTextRow = this.getNext();
    this.sheet.createRow(footerTextRow);
    this.sheet.addMergedRegion(new CellRangeAddress(footerTextRow, footerTextRow, 3, 4));
    this.sheet.getRow(footerTextRow).createCell(3).setCellValue("FOR GLOBAL TRANDING FZC");
    this.sheet.getRow(footerTextRow).getCell(3).setCellStyle(footerStyle);

  }

  public void addStamp() throws IOException {

    CellStyle pictureStyle = this.workbook.createCellStyle();
    pictureStyle.setAlignment(HorizontalAlignment.CENTER);

    InputStream is = new FileInputStream("src/main/resources/stamp.png");
    byte[] bytes = IOUtils.toByteArray(is);
    int pictureIdx = this.workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
    is.close();

    CreationHelper helper = this.workbook.getCreationHelper();
    Drawing drawing = this.sheet.createDrawingPatriarch();
    ClientAnchor anchor = helper.createClientAnchor();

    this.getNext();
    int pictureRow = this.getNext();
    this.sheet.addMergedRegion(new CellRangeAddress(pictureRow, pictureRow + 7, 3, 4));
    this.sheet.createRow(pictureRow).createCell(3).setCellStyle(pictureStyle);

    anchor.setCol1(3);
    anchor.setRow1(pictureRow);
    Picture pict = drawing.createPicture(anchor, pictureIdx);
    pict.resize(1.0, Double.MAX_VALUE);


  }

  public void createExcelSheet() throws Exception {
    this.sheet.setMargin((short) 0, 0.15);
    this.sheet.setMargin((short) 1, 0.15);
    this.sheet.setMargin((short) 2, 0.15);
    this.sheet.setMargin((short) 3, 0.15);
    System.out.println("1) All Margins set to 0.15 inches");

    this.setTitle();
    System.out.println("2) Title Done");
    this.setSubTitle();
    System.out.println("3) Sub-title Done.");
    this.setDocumentTypeTitle();
    System.out.println("4) Document Type Written.");
    this.setCompanyAddress();
    System.out.println("5) Invoicee Address Set.");
    this.setDeliveryInformation();
    System.out.println("6) Delivery Information Set.");
    this.setInvoicerInformation();
    System.out.println("7) Invoicer Information Set.");
    this.setFieldNames();
    System.out.println("8) Field Names written.");
    this.setEntryValues();
    System.out.println("9) Added All Invoice Entries.");
    this.setChargeValues();
    System.out.println("10) Added All Charges.");
    this.setFinalTotal();
    System.out.println("11) Final Totaling Done.");
    this.addFooter();
    System.out.println("12) Added Footer Text.");
    this.addStamp();
    System.out.println("13) Added Footer Stamp.");

    this.sheet.autoSizeColumn(0);
    this.sheet.autoSizeColumn(1);
    this.sheet.autoSizeColumn(2);
    this.sheet.autoSizeColumn(3);

    // Finalize xls file.
    File file = new File(this.storageLocation);
    this.workbook.write(file);
    this.workbook.close();
  }

  public void runExcelSheet() throws IOException {
    Runtime.getRuntime()
        .exec("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\EXCEL.EXE "
            + this.storageLocation);
  }


  public void convertExcelToPdf() throws Exception {
    String script = "src/main/resources/ae/globaltrd/Invoices/ExcelToPdf.vbs";
    String executable = "C:\\Windows\\System32\\wscript.exe";

    String cmdArr[] = {executable, script};

    Runtime.getRuntime().exec(cmdArr);
  }
  

}
