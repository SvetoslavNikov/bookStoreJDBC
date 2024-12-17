package bookStoreDB.clients;

public class Main {
    public static void main(String[] args) {
        Client a = new Client(1,"Georgi Georgiev", "0897863435", "georgi.georgiev@email.com","Graf Ignatiev 68, Sofia, Bulgaria", "Young, ambitious, curious, student at Technical Uneversity Sofia");
        ClientDAO b = new ClientDAO();
        //b.addClient(a);

        b.ClientsTablePrint();
    }
}
