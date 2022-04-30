package codeaggressive.com.bctrace;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class RetriveExcel extends AppCompatActivity {
    TextView batchtextView, mixertextview, kegtextview, bintextview;
    EditText batchNoText;
    Button fetchfromexcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_excel);
        batchtextView = findViewById(R.id.batchTextview);
        mixertextview = findViewById(R.id.mixerTextview);
        kegtextview = findViewById(R.id.kegTextview);
        bintextview = findViewById(R.id.binTextview);
        batchNoText = findViewById(R.id.batchNo_text);
        fetchfromexcel = findViewById(R.id.fetchFromExcel);

        fetchfromexcel.setOnClickListener(v -> {
            if (batchNoText.getText().toString().isEmpty()) {
                Toast.makeText(RetriveExcel.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
            } else {
                readExcelFileFromAssets();
            }
        });
    }

    public void readExcelFileFromAssets() {
        try {
            String batchText = batchNoText.getText().toString();
            File sources = new File(Environment.getExternalStorageDirectory() + "/ExcelData.xlsx");
            FileInputStream fis = new FileInputStream(sources);
            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            // Return first sheet from the XLSX workbook
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // We now need something to iterate through the cells.
            Iterator<Row> rowIter = mySheet.rowIterator();
            int rowno = 0;
            while (rowIter.hasNext()) {
                XSSFRow myRow = (XSSFRow) rowIter.next();
                if (rowno != 0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno = 0;
                    String batch = "", mixer = "", keg = "", bin = "";
                    while (cellIter.hasNext()) {
                        XSSFCell myCell = (XSSFCell) cellIter.next();
                        if (colno == 0) {
                            batch = myCell.toString();
                        } else if (colno == 1) {
                            mixer = myCell.toString();
                        } else if (colno == 2) {
                            keg = myCell.toString();
                        } else if (colno == 3) {
                            bin = myCell.toString();
                        }
                        colno++;
                    }
                    if (batch.equals(batchText)) {
//                        textView.setText("");
//                        textView.append(batch + " -- " + mixer + "  -- " + keg + " -- " + bin + "\n");
                        batchtextView.setText(batch);
                        mixertextview.setText(mixer);
                        kegtextview.setText(keg);
                        bintextview.setText(bin);
                    }
                }
                rowno++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}