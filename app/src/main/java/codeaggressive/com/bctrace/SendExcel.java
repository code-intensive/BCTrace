package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SendExcel extends AppCompatActivity {
    EditText batchNo, Mxs, keg, bin;
    Button sendExcel, ViewExcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_excel);
        batchNo = findViewById(R.id.btchNo);
        Mxs = findViewById(R.id.mxs);
        keg =findViewById(R.id.keg);
        bin = findViewById(R.id.bin);
        sendExcel = findViewById(R.id.sendExcel);
        ViewExcel = findViewById(R.id.ViewExcel);

        File generateFile = new File(Environment.getExternalStorageDirectory() + "/ExcelData.xlsx");

        if (!generateFile.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Generate");
            String[] columnHeader = {"BATCH NO", "MIXER", "KEG NO", "BIN NO"};

            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.BLACK.index);

            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

            XSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < columnHeader.length; i++) {
                XSSFCell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(columnHeader[i]);
                headerCell.setCellStyle(headerStyle);
            }
            sheet.createFreezePane(0, 1);

            Toast.makeText(getApplicationContext(), "ExcelData.xlsx Created successfully", Toast.LENGTH_LONG).show();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(generateFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                workbook.write(fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sendExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertToExcel();
            }
        });

        ViewExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendExcel.this, RetriveExcel.class);
                startActivity(intent);
            }
        });
    }

    private void InsertToExcel() {
        File sources = new File(Environment.getExternalStorageDirectory() + "/ExcelData.xlsx");
        try {
            FileInputStream existing = new FileInputStream(sources);
            XSSFWorkbook workbook = new XSSFWorkbook(existing);
            XSSFSheet worksheet = workbook.getSheetAt(0);
            int lastRow = worksheet.getLastRowNum();
            XSSFRow row = worksheet.createRow(++lastRow);
            row.createCell(0).setCellValue(batchNo.getText().toString());
            row.createCell(1).setCellValue(Mxs.getText().toString());
            row.createCell(2).setCellValue(keg.getText().toString());
            row.createCell(3).setCellValue(bin.getText().toString());
            existing.close();
            FileOutputStream newinput = new FileOutputStream(sources);
            workbook.write(newinput);
            newinput.close();
            Toast.makeText(this, "Sent Successful", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}