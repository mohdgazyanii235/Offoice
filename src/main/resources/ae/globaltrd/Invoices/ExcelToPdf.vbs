Option Explicit
Dim objExcel, strExcelPath, objSheet

strExcelPath = "D:/eclipse-workspace/Offoice/src/main/resources/test.xls"

Set objExcel = CreateObject("Excel.Application")
objExcel.WorkBooks.Open strExcelPath
Set objSheet = objExcel.ActiveWorkbook.Worksheets(1)
objSheet.ExportAsFixedFormat 0, "D:/eclipse-workspace/Offoice/src/main/resources/test.pdf",0, 1, 0, , , 0

objExcel.ActiveWorkbook.Close
objExcel.Application.Quit