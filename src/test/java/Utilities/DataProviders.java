package Utilities;



import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //DataProvider 1

    @DataProvider(name = "LoginData")
    public String[][] getData()throws IOException
    {
        String path ="C:\\Users\\shiva\\IdeaProjects\\Opencart121\\src\\testData\\opencartlogin.xlsx"; //taking xl file from test data
        ExcelUtility xlutil=new ExcelUtility(path);// create an object for XLutility

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1",1);

        String logindata [][]=new String[totalrows][totalcols]; //created two dimensional array which can store data

        for(int i=1;i<totalrows;i++)
        {
            for (int j=0;j<totalcols;j++)
            {
                logindata[i-1][j]=xlutil.getCellData("Sheet1",i,j);  //1,0

            }
        }
        return logindata;  //returning in to two dimensional array
    }
}
