package sample.Model;

public class People {

    private int id;
    private String name;


    public People(int ID, String pName){
        this.id =ID;
        this.name = pName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
