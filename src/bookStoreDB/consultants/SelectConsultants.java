package bookStoreDB.consultants;

import java.sql.*;

public class SelectConsultants {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String username = "postgres";
        String password = "Tempzp22062002.";

        try(Connection connection = DriverManager.getConnection(url,username, password)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM consultants");
            System.out.printf("%-5s %-20s %-20s %-30s %-30s %s\n",
                    "ID", "|Name", "|Phone Number", "|Email","|Address","|Note"
            );
            System.out.println("------------------------------------------------------------------------------------------------------");

            while(resultSet.next()){
            System.out.printf("%-5s %-20s %-20s %-30s %-30s %s\n",
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("email"),
                    resultSet.getString("address"),
                    resultSet.getString("note")
                    );}
            resultSet.close();
            statement.close();


        }catch(SQLException e){
            System.out.println("SQLstate: " + e.getSQLState());
            System.out.println("Error Code: " + e.getMessage());
            System.out.println("Message: " + e.getErrorCode());
            e.printStackTrace();
        }


    }
}
