package org.klaptech.limechat.client.utils;

import static java.util.logging.Logger.getLogger;






import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

/**
 * Used for interacting with config file
 *
 * @author rlapin
 */
public enum PropertyManager {
    INSTANCE;
    private Logger LOGGER;
    private Properties properties;

    PropertyManager() {
        LOGGER = getLogger(PropertyManager.class.getName());
        readProperties();
    }

    private void readProperties() {
        String appDirPath = System.getProperty("user.home") + File.separator + ".laptech" + File.separator;
        File appDir = new File(appDirPath);
        if (!appDir.exists()) {
            LOGGER.info("App directory not found");
            if (appDir.mkdirs()) {
                LOGGER.info("Config directory successfully created : " + appDirPath);
            } else {
                LOGGER.severe("Problem with creating app directory : " + appDirPath);
            }
        }
        File configFile = new File(appDirPath + File.separator + "limechat.config");

        if (configFile.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(configFile))) {
                properties = (Properties) objectInputStream.readObject();
            } catch (IOException e) {
                LOGGER.severe("Problem while reading from file: " + configFile + ". Get exception " + e.getMessage());
            } catch (ClassNotFoundException e) {
                LOGGER.severe("Problem while deserialize data. Get exception " + e.getMessage());
            }
        } else {
            properties = new Properties();
        }

    }

    public Properties getProperties() {
        return properties;
    }
}
