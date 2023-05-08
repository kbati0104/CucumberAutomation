package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtilities;
import utilities.TempStorage;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_steps {

    Connection connection;
    Statement statement;

    ResultSet resultSet;

    Map <String, Object> dataSent=new HashMap<>();

    @Given("I have access to the database")
    public void i_have_access_to_the_database() {

        connection=DBUtilities.getConnection();

    }
    @When("I create a new employee with {int} as employee_id, {string} as a first_name, {string}, {string}, {string},{string}, {int}, {int}, {int}, {int}")
    public void i_create_a_new_employee_with_as_employee_id_as_a_first_name
            (int employee_id, String first_name, String last_name, String email, String phone_number,
             String hire_date, int salary, int job_id, int manager_id, int department_id) throws SQLException {

        dataSent.put("employee_id",employee_id );
        dataSent.put("first_name", first_name);
        dataSent.put("last_name", last_name);
        dataSent.put("email",email);
        dataSent.put("phone_number", phone_number);
        dataSent.put("hire_date", hire_date);
        dataSent.put("salary",salary);
        dataSent.put("job_id", job_id);
        dataSent.put("manager_id",manager_id);
        dataSent.put("department_id", department_id);

       String query="Insert into employees values (" + employee_id+
               "," +first_name+
               ","+last_name+
               ","+email+
               ","+ phone_number+
               ","+ hire_date+
               ","+ salary+
               ","+ job_id+
               ","+ manager_id+
               ","+ department_id+
               ")";

       statement=connection.createStatement();
       resultSet=statement.executeQuery(query);
        TempStorage.addData("employee_id",""+ employee_id);
    }




    @Then("the record should be successfully inserted")
    public void the_record_should_be_successfully_inserted(){

        String query="select * from employees where " +TempStorage.getKey()+"="+TempStorage.getData(TempStorage.getKey());
        Map<String, Object> results = DBUtilities.getQueryResultAsList(query).get(0);

        Assert.assertEquals("The number of columns sent and received did not match", dataSent.size(), results.size());

        for (String key : dataSent.keySet()){
            Assert.assertEquals("Data did not match: ", dataSent.get(key), results.get(key));
        }



    }
    @Then("when I query for the record with employee_id {int}, I should receive the correct details")
    public void when_i_query_for_the_record_with_employee_id_i_should_receive_the_correct_details(int employee_id) {

    }

    @Given("User connects to the DB")
    public void user_connects_to_the_db() throws ClassNotFoundException, SQLException {

        //tries to find object of the given  class
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //start the object
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        //create connection with Oracle database
        connection = DriverManager.getConnection("jdbc:oracle:thin:@3.123.40.26:1521:CODEWISE",
                "SYSTEM", "Codewise_123");

        //create statement to build SQL query
        statement = connection.createStatement();

    }

    @When("{string} query")
    public void query(String query) throws SQLException {
        resultSet = statement.executeQuery(query);
        DBUtilities.printResult(query);

    }

    @Then("verify if data is returned")
    public void verify_if_data_is_returned() {
        try {
            while (resultSet.next()) {
                if (resultSet.getString("first_name").equalsIgnoreCase("Steven")) {


                    System.out.println(resultSet.getString("first_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error, SQL result set was not returned");
            e.printStackTrace();
        }


    }



    SQLException exception;

    @Given("User try to connect to the DB with incorrect password")
    public void user_try_to_connect_to_the_db_with_incorrect_password() throws ClassNotFoundException, SQLException {
        //tries to find object of the given  class
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //start the object
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        try {
            //create connection with Oracle database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@3.123.40.26:1521:CODEWISE",
                    "SYSTEM", "Codewise_3");
        } catch (SQLException e) {
            exception = e;
        }
    }

    @Then("verify user is not able to connect")
    public void verify_user_is_not_able_to_connect() {

        Assert.assertNotNull("The connection didn't throw exception", exception);


    }


}



