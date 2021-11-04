


//import java.io.*;
//import java.sql.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.util.*;
//import java.util.Date;
//
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;
//import org.apache.xmlbeans.impl.jam.JParameter;
//
//import static java.lang.Integer.parseInt;


//public class ExceltoDatabase
//{
//
//    public static void main(String[] args)
//    {
//        String jdbcURL = "jdbc:mysql://localhost:3306/studs?autoReconnect=true&useSSL=false";
//        //?autoReconnect=true&useSSL=false"
//        String username = "root";
//        String password = "sysmanage";
//
//        String excelFilePath = "C:\\Jman_assigned_work1\\Student_data_xlsx.xlsx";
//        File myFile = new File(excelFilePath);
//        System.out.println("File exists: " + myFile.exists());
//        int batchSize = 20;
//
//        Connection connection = null;
//
//        try
//        {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            connection = DriverManager.getConnection(jdbcURL, username, password);
//            connection.setAutoCommit(false);
//
//            String sql = "insert into students ( name, schoolname, address, city,country,postal,phonenum,email,doj) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            BufferedReader lineReader = new BufferedReader(new FileReader(excelFilePath));
//
//            String lineText = null;
//            int count = 0;
//
//            lineReader.readLine();
//            while ((lineText = lineReader.readLine()) != null)
//            {
//                String data[] = lineText.split("/t");
//                System.out.println("kkk:"+data);
//
//                String id = data[0];
//                System.out.println(id);
//                String name = data[1];
//                System.out.println(name);
//                String schoolname = data[2];
//                System.out.println(schoolname);
//                String address = data[3];
//                String city = data[4];
//                String country = data[5];
//                String postal = data[6];
//                String phonenum = data[7];
//                String email = data[8];
//                String doj = data[9];
//
//
//                statement.setInt(1, parseInt(id));
//                statement.setString(2, name);
//                statement.setString(3, schoolname);
//                statement.setString(4, address);
//                statement.setString(5, city);
//                statement.setString(6, country);
//                statement.setString(7, postal);
//                statement.setString(8, phonenum);
//                statement.setString(9, email);
//                statement.setString(10, doj);
//                statement.addBatch();
//                if (count % batchSize == 0)
//                {
//                    statement.executeBatch();
//                }
//
//            }
//            lineReader.close();
//            statement.executeBatch();
//            connection.commit();
//            connection.close();
//            System.out.print("Data has been inserted successfully");
//        }
//
//        catch (Exception ex)
//        {
//            System.out.println("SQLException: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//
//    }
//}









//
//        try
//        {
//            long start = System.currentTimeMillis();
//
//            FileInputStream inputStream = new FileInputStream(excelFilePath);
//            System.out.println(inputStream);
//
//            Workbook workbook = new XSSFWorkbook(inputStream);
//
//            Sheet firstSheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = firstSheet.iterator();
//
//            connection = DriverManager.getConnection(jdbcURL, username, password);
//            connection.setAutoCommit(false);
//
//            String sql = "insert into students (name, schoolname, address, city,country,postal,phonenum,email,doj) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            int count = 0;
//
//            rowIterator.next(); // skip the header row
//
//            while (rowIterator.hasNext())
//            {
//                Row nextRow = rowIterator.next();
//                Iterator<Cell> cellIterator = nextRow.cellIterator();
//
//                while (cellIterator.hasNext())
//                {
//                    Cell nextCell = cellIterator.next();
//
//                    int columnIndex = nextCell.getColumnIndex();
//
//                    switch (columnIndex)
//                    {
//                        case 0:
//                            String name = nextCell.getStringCellValue();
//                            statement.setString(1, name);
//
//                        case 1:
//
//                            String schoolname = nextCell.getStringCellValue();
//                            statement.setString(2, schoolname);
//                        case 2:
//                            String address =  nextCell.getStringCellValue();
//                            statement.setString(3, address);
//                        case 3:
//                            String city =  nextCell.getStringCellValue();
//                            statement.setString(4, city);
//                        case 4:
//                            String country =  nextCell.getStringCellValue();
//                            statement.setString(5, country);
//                        case 5:
//                            String postal =  nextCell.getStringCellValue();
//                            statement.setString(6, postal);
//                        case 6:
//                            String phonenum =  nextCell.getStringCellValue();
//                            statement.setString(7, phonenum);
//                        case 7:
//                            String email =  nextCell.getStringCellValue();
//                            statement.setString(8, email);
//                        case 8:
//                            Date doj =  nextCell.getDateCellValue();
//                            statement.setDate(9,(java.sql.Date)doj);
//                    }
//
//                  System.out.println(statement);
//
//                }
//
//                statement.addBatch();
//
//                if (count % batchSize == 0)
//                {
//                    statement.executeBatch();
//                }
//
//            }
//
//            workbook.close();
//
//            // execute the remaining queries
//            statement.executeBatch();
//
//            connection.commit();
//            connection.close();
//
//            long end = System.currentTimeMillis();
//            System.out.printf("Import done in %d ms\n", (end - start));
//
//        }
//        catch (IOException ex1)
//        {
//            System.out.println("Error reading file");
//            ex1.printStackTrace();
//        }
//        catch (SQLException ex2)
//        {
//            System.out.println("Database error");
//            ex2.printStackTrace();
//        }
//
//    }
// }

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExceltoDatabase
{


    public static void main(String[] args) throws ParseException
    {
         //throws IOException, ParseException, org.json.simple.parser.ParseException
        int count=1;

//        java.util.Date date = new Date( row.getCell(8));
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String format = formatter.format(date);
//        System.out.println(format);




        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studs", "root", "sysmanage");
            Statement statement = connection.createStatement();

            File file = new File("C:\\Jman_assigned_work1\\Student_data_xlsx.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
     //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext())
            {
                Row row = itr.next();

               System.out.println(row.getCell(0));
                System.out.println(row.getCell(8));

            if(row.getRowNum()==0)
            {

                continue;
            }
            else
             {
                count++;
//                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                System.out.println( "adf:"+dateFormat);
//                Date date = new Date(21/10/2021);
//                System.out.println( "ss"+date);
//                System.out.println("gdfd:"+dateFormat.format(date));

//                String sDate1="21/10/2021";
//                Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
//                System.out.println("jwsdjsod:"+sDate1+"\t"+date1);

//                DataFormatter dataFormatter = new DataFormatter();
//                String cellStringValue = dataFormatter.formatCellValue(row.getCell(8));
//                System.out.println("gdfd:"+(cellStringValue));

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
              // System.out.println(row.getCell(8).getDateCellValue());
//                Date dt = sdf.parse( ""+ row.getCell(8) );// in this string concats with a cell and explicity it changes to string type
//                System.out.println("sss"+dt);
                sdf.applyPattern("yyyy-MM-dd");
//                String res = sdf.format(dt);
                String res = sdf.format(row.getCell(8).getDateCellValue());
                System.out.println(res);

                String sql = "INSERT INTO students VALUES("+count+",'" + row.getCell(0) + "','" + row.getCell(1) + "','" + row.getCell(2) + "','" + row.getCell(3) + "','" + row.getCell(4) + "','" + row.getCell(5) + "','" + row.getCell(6) + "','" + row.getCell(7) + "','" + res +  "')";
                System.out.println(sql);
                statement.executeUpdate(sql);

             }

                System.out.println("");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }




    }
}