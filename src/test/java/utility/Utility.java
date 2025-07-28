package utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static Object getTestDataFromJSON(String sectionName, String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File("src/test/java/resources/testData.json");
            JsonNode root = mapper.readTree(file);

            JsonNode valueNode = root.path(sectionName).path(0).path(key);
            if (valueNode.isMissingNode()) {
                throw new RuntimeException("Key '" + key + "' not found in section '" + sectionName + "'");
            }

            // Return based on type
            if (valueNode.isTextual()) {
                return valueNode.asText();
            } else if (valueNode.isInt()) {
                return valueNode.asInt();
            } else if (valueNode.isLong()) {
                return valueNode.asLong();
            } else if (valueNode.isDouble()) {
                return valueNode.asDouble();
            } else if (valueNode.isBoolean()) {
                return valueNode.asBoolean();
            } else {
                // Fallback: return raw JsonNode or as String
                return valueNode.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON data");
        }
    }

    public static String getCurrentDateTime() {
        return java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
