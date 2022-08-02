package car;

public class Wheel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wheel(String name) {
        this.name = name;
    }

    public Wheel() {
    }

    @Override
    public String toString() {
        return name;
    }
}
