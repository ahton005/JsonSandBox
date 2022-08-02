package experiments;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect()
public class First {
    @JsonProperty("idd")
    public int id;
    private String name;

    public First(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public First(){}

    public int getId() {
        return id;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        First object = new First(1, "word");
        System.out.println(objectMapper.writeValueAsString(object));
    }
}
