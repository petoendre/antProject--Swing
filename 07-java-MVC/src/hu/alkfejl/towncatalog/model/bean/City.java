package hu.alkfejl.towncatalog.model.bean;

/**
 * Az osztály egy vásárlót/ügyfelet ír le a JavaBean konvenciók betartásával:
 * Jobb klikk+source+generate getters/setters.
 * Meghívasa az összes nem statikus, nem transient attribútumra.
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
