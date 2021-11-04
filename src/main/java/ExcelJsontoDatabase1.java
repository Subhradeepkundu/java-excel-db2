import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;

public class ExcelJsontoDatabase1
{

    public static void main(String[] args) throws ParseException
    {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the excel or json id");
        int chk = in.nextInt();
        if(chk==1)
        {
            ExtoDtb1 e1=new ExtoDtb1();
            e1.exceltoDatabase();

        }
        else if(chk==2)
        {
            JsotoDtb1 e2=new JsotoDtb1();
            e2.jsontoDatabase();
        }
        else
        {
            System.out.println("Invalid");
        }

    }


}

class ExtoDtb1
{
    Cell r[]=new Cell[9];
    static int count=1;
    public void exceltoDatabase()
    {


        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studs", "root", "sysmanage");
            Statement statement = connection.createStatement();

            File file = new File("C:\\Jman_assigned_work1\\Student_data_xlsx.xlsx");
            FileInputStream fis = new FileInputStream(file);

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            while (itr.hasNext())
            {
                Row row = itr.next();

                System.out.println(row.getCell(0));
                System.out.println(row.getCell(8));

                r[0]=row.getCell(0);
                r[1]=row.getCell(1);
                r[2]=row.getCell(2);
                r[3]=row.getCell(3);
                r[4]=row.getCell(4);
                r[5]=row.getCell(5);
                r[6]=row.getCell(6);
                r[7]=row.getCell(7);
                r[8]=row.getCell(8);



                if (row.getRowNum() == 0)
                {

                    continue;
                }
                else
                {
                    count++;

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

                    sdf.applyPattern("yyyy-MM-dd");

                    String res = sdf.format(row.getCell(8).getDateCellValue());
                    System.out.println(res);

                    common c=new common();
                    int h=1;
                    c.commonen1(statement,h,count,r,res);

                }

                System.out.println("");
            }
        }
        catch (Exception ee)
        {
            ee.printStackTrace();
        }
    }


}

class JsotoDtb1
{
    public void jsontoDatabase()

    {
        Scanner in = new Scanner(System.in);
        String studentname1 = null;
        String schoolname1= null;
        String address1 = null;
        String city1 = null;
        String country1 = null;
        String postal1 = null;
        String email1 = null;
        long k1 =0;

        String jsd[]=new String[] {null,null,null,null,null,null,null};

        JSONParser jsonParser=new JSONParser();
        try
        {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studs", "root", "sysmanage");
            Statement statement = connection.createStatement();

            FileReader reader = new FileReader(".\\jsonfiles\\Kaoan.json");

            Object obj = jsonParser.parse(reader);
            System.out.println("ddrr"+obj);


            JSONArray employeeList = (JSONArray) obj;
            System.out.println("bas"+employeeList);

            for (Object emp:employeeList)
            {
                //count++;
                JSONObject empObj=(JSONObject) emp;
                if (empObj == null)
                {
                    System.exit(0);
                }
                if(empObj.get("id")!=null)
                {
                    Long k = (Long)empObj.get("id");
                    System.out.println(k);
                    k1=k;
                }

                if(empObj.get("studentname")!=null)
                {
                    String studname = (String) empObj.get("studentname");
                    jsd[0]=studname;
                }
                else
                {
                    System.out.println("student name is null");
                    continue;



                }


                if(empObj.get("schoolname")!=null)
                {
                    String schoolnam = (String) empObj.get("schoolname");
                    jsd[1] = schoolnam;
                }
                else
                {
                    System.out.println("schoolname is null");
                    continue;
                }

                if(empObj.get("address")!=null)
                {
                    String addr = (String)empObj.get("address");
                    jsd[2]=addr;
                }
                else
                {
                    System.out.println("address is null");
                    continue;
                }

                if(empObj.get("city")!=null)
                {
                    String cty = (String) empObj.get("city");
                    jsd[3]=cty;
                }
                else
                {
                    System.out.println("city is null");
                    continue;
                }

                if(empObj.get("country")!=null)
                {
                    String cuntry = (String) empObj.get("country");
                    jsd[4]=cuntry;
                }
                else
                {
                    System.out.println("country is null");
                    continue;
                }

                if(empObj.get("postal")!=null)
                {
                    String post = (String) empObj.get("postal");
                    jsd[5]=post;
                }
                else
                {
                    System.out.println("postal is null");
                    continue;
                }

                if(empObj.get("email")!=null)
                {
                    String eml = (String) empObj.get("email");
                    jsd[6]=eml;
                }
                else
                {
                    System.out.println("email is null");
                    continue;
                }

                String phn = (String) empObj.get("phone");
                String joiningdate = (String) empObj.get("doj");

                common c=new common();
                int m=2;
                c.commonen2(statement,m,k1,jsd,phn,joiningdate);

            }

        }

        catch(FileNotFoundException e)
        {

        }
        catch(IOException e)
        {

        }
        catch(org.json.simple.parser.ParseException e)
        {

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}

class common
{
    public void commonen1(Statement statement, int h, int count, Cell[] r, String res)
    {
        try
        {


            if(h==1)
            {

                String sql = "INSERT INTO students VALUES(" + count + ",'" + r[0] + "','" + r[1] + "','" + r[2] + "','" + r[3] + "','" + r[4] + "','" + r[5] + "','" + r[6] + "','" + r[7] + "','" + res + "')";
                System.out.println(sql);
                statement.executeUpdate(sql);

            }

        }
        catch (Exception ee)
        {
            ee.printStackTrace();
        }
    }


    public void commonen2(Statement statement, int c1,long k1, String[] jsd, String phn, String joiningdate)
    {

        try
        {


            if(c1==2)
            {
                String sql = "INSERT INTO students VALUES(" + k1 + ",'" + jsd[0] + "','" + jsd[1] + "','" + jsd[2] + "','" + jsd[3] + "','" + jsd[4] + "','" + jsd[5] + "','" + phn + "','" + jsd[6] + "','" + joiningdate + "')";
                System.out.println(sql);
                statement.executeUpdate(sql);

            }

        }
        catch (Exception ee)
        {
            ee.printStackTrace();
        }
    }


}






