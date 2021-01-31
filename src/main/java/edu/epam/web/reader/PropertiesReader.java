package edu.epam.web.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final Logger logger = LogManager.getLogger(PropertiesReader.class);

    public Properties readPropertiesFile(String path) {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(path)) {
            properties.load(reader);
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return properties;
    }
}
