import java.util.Objects;

public abstract class Ingredient implements Priceable {

    static int count = 0;
    private int id;
    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
        id = count++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase().trim();
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name.toLowerCase(), that.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Ingredient -" + name ;
    }
}

