package json;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Mapper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Users> readJsonList(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                List.class, Users.class);
        List<Users> usersList = objectMapper.readValue(url, collectionType);
        //logger.info(usersList.toString());
        return usersList;
    }
}