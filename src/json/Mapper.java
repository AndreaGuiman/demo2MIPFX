package json;

import model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Mapper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Users readJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Users users = objectMapper.readValue(new File("0_users.json"), Users.class);
        logger.info(users.toString());
        return users;
    }
}