package org.klaptech.limechat.client.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Used for interacting with config file
 *
 * @author rlapin
 */
public enum PropertyManager {
    INSTANCE;
    /**
     * Use non static logger , because of enum
     */
    private Logger LOGGER;
    private Properties properties;

    PropertyManager() {
        LOGGER = getLogger(PropertyManager.class.getName());
        readProperties();
    }

    private void readProperties() {
        File configFile = getPropertyFile();

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

    /**
     * Get properies file and create directories to that file if it isn't created
     *
     * @return properties File
     */
    private File getPropertyFile() {
        String appDirPath = System.getProperty("user.home") + File.separator + ".laptech" + File.separator + "limechat" + File.separator;
        File appDir = new File(appDirPath);
        if (!appDir.exists()) {
            LOGGER.info("App directory not found");
            if (appDir.mkdirs()) {
                LOGGER.info("Config directory successfully created : " + appDirPath);
            } else {
                LOGGER.severe("Problem with creating app directory : " + appDirPath);
            }
        }
        return new File(appDirPath + File.separator + "limechat.config");
    }

    public Properties getProperties() {
        return properties;
    }

    public void writeProperties() {
        File configFile = getPropertyFile();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(configFile))) {
            objectOutputStream.writeObject(properties);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.severe("Problem while writing to file: " + configFile + ". Get exception " + e.getMessage());
        }
    }
}
