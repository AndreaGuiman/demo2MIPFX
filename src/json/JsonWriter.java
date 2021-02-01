package json;

import model.Users;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JsonWriter {
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        ObjectMapper objectMapper = new ObjectMapper();

        public String writeJson(Users users){
            String jsonInString  = null;
            try{
                jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
                logger.info("User JSON is " + jsonInString);
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(users.getIdUser() + "_users.json"), users);

            }catch (JsonGenerationException e){
                e.printStackTrace();
            }catch (JsonMappingException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return jsonInString;
        }
    }
