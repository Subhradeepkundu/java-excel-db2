import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JsontoDatabase
{

    public static void main(String[] args)
    {
        String studentname1 = null;
        String schoolname1= null;
        String address1 = null;
        String city1 = null;
        String country1 = null;
        String postal1 = null;
        String email1 = null;
        long k1 =0;

        JSONParser jsonParser=new JSONParser();
        try
        {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studs", "root", "sysmanage");
            Statement statement = connection.createStatement();

            FileReader reader = new FileReader(".\\jsonfiles\\Kaoan.json");

            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;


            for (Object emp:employeeList)
            {

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
                    studentname1=studname;
                }
                else
                {
                    System.out.println("student name is null");
                    continue;



                }


                if(empObj.get("schoolname")!=null)
                {
                    String schoolnam = (String) empObj.get("schoolname");
                    schoolname1 = schoolnam;
                }
                else
                {
                    System.out.println("schoolname is null");
                    continue;
                }

                if(empObj.get("address")!=null)
                {
                    String addr = (String)empObj.get("address");
                    address1=addr;
                }
                else
                {
                    System.out.println("address is null");
                    continue;
                }

                if(empObj.get("city")!=null)
                {
                    String cty = (String) empObj.get("city");
                    city1=cty;
                }
                else
                {
                    System.out.println("city is null");
                    continue;
                }

                if(empObj.get("country")!=null)
                {
                    String cuntry = (String) empObj.get("country");
                    country1=cuntry;
                }
                else
                {
                    System.out.println("country is null");
                    continue;
                }

                if(empObj.get("postal")!=null)
                {
                    String post = (String) empObj.get("postal");
                    postal1=post;
                }
                else
                {
                    System.out.println("postal is null");
                    continue;
                }

                if(empObj.get("email")!=null)
                {
                    String eml = (String) empObj.get("email");
                    email1=eml;
                }
                else
                {
                    System.out.println("email is null");
                    continue;
                }

                String phn = (String) empObj.get("phone");
                String joiningdate = (String) empObj.get("doj");


                String sql = "INSERT INTO students VALUES(" + k1 + ",'" + studentname1 + "','" + schoolname1 + "','" + address1 + "','" + city1 + "','" + country1 + "','" + postal1 + "','" + phn + "','" + email1 + "','" + joiningdate + "')";
                System.out.println(sql);
                statement.executeUpdate(sql);
            }


        }

        catch(FileNotFoundException e)
        {

        }
        catch(IOException e)
        {

        }
        catch(ParseException e)
        {

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

}
