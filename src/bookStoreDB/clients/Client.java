package bookStoreDB.clients;

public class Client {
    private int id;
    private int consultantId;  // Changed to camelCase for naming conventions
    private String name;
    private String phoneNumber; // Changed to camelCase
    private String email;
    private String address;
    private String note;

    //Constructors
    public Client() {
    }

    public Client(int consultantId, String name, String phoneNumber, String email, String address, String note) {
        this.consultantId = consultantId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.note = note;
    }


    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getConsultantId() {
        return consultantId;
    }
    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
