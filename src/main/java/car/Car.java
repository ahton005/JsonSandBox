package car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Car {
    public static final String JSON_TXT = "json.txt";
    private String color;
    private String type;
    private List<Wheel> wheels;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public Car(String color, String type, List<Wheel> wheels) {
        this.color = color;
        this.type = type;
        this.wheels = wheels;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "car.Car{" +
                "CarColor='" + color + '\'' +
                ", type='" + type + '\'' +
                ", wheels=" + wheels +
                '}';
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

//      Список колес.
        ArrayList<Wheel> wheels = new ArrayList<>();

//      Создание и заполнение колес.
        Wheel one = new Wheel("One");
        Wheel two = new Wheel("Two");
        wheels.add(one);
        wheels.add(two);

//      Создание машины, печать машины, печать после сериализация в виде строки, запись в файл.
        Car car1Ser = new Car("red", "lada", wheels);
        System.out.println(car1Ser);
        String car1 = objectMapper.writeValueAsString(car1Ser);
        System.out.println(car1 + "\n");
        objectMapper.writeValue(new File(JSON_TXT), car1Ser);

//      Печать в виде объекта Java после десериализации.
        Car car1Deser = objectMapper.readValue(new File(JSON_TXT), Car.class);
        System.out.println(car1Deser + "\n");

//      Получение конкретного поля wheels, десериализация в список wheels, печать в виде объектов Java.
        JsonNode jsonNode = objectMapper.readTree(new File(JSON_TXT));
        System.out.println(jsonNode.get("wheels"));
        List<Wheel> wheels1 = objectMapper.convertValue(jsonNode.get("wheels"), new TypeReference<List<Wheel>>() {
        });
        System.out.println(wheels1 + "\n");

//      Получение полей объекта в виде мапы.
        Map<String, Object> map = objectMapper.readValue(car1, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map + "\n");
        System.out.println(map.get("wheels").getClass());

//      Сериализация и десериализация нескольких объектов в виде списка мап.
        List<Car> cars = new ArrayList<>();
        cars.add(car1Ser);
        cars.add(car1Ser);
        String carsString = objectMapper.writeValueAsString(cars);
        System.out.println(carsString);
        List<Map<String, Object>> map1 = objectMapper.readValue(carsString, new TypeReference<List<Map<String, Object>>>() {
        });
        System.out.println(map1 + "\n");
    }
}