package hu.alkfejl.towncatalog.model.bean;

/**
 * Az oszt�ly egy v�s�rl�t/�gyfelet �r le a JavaBean konvenci�k betart�s�val:
 * Jobb klikk+source+generate getters/setters.
 * Megh�vasa az �sszes nem statikus, nem transient attrib�tumra.
 */
public class City {

    private int id;
    private String city_name;
    private int city_created_at;

    // id

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // name

    public String getName() {
        return city_name;
    }

    public void setCityName(String name) {
        this.city_name = name;
    }

    // age

    public int getAge() {
        return city_created_at;
    }

    public void setCreatedAt(int age) {
        this.city_created_at = age;
    }

    @Override
    public String toString() {
        return
                id + "-" + city_name + "-" + city_created_at;
    }

}
