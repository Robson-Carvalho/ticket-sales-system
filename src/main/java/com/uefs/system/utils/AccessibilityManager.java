package com.uefs.system.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AccessibilityManager {
    private final Properties properties = new Properties();
    private static final String accessibility_properties_path = PathsFile.getAccessibilityProperties();

    public Boolean getAccessibilityPropertiesCurrent() {
        try (FileInputStream fis = new FileInputStream(accessibility_properties_path)) {
            properties.load(fis);
            String accessibility = properties.getProperty("accessibility");

            if (accessibility != null) {
                return Boolean.parseBoolean(accessibility);
            }
        } catch (IOException e) {
            System.out.println("Error loading accessibility properties: " + e.getMessage());
        }

        return false;
    }

    public void toggleAccessibilityProperties() {
        Boolean accessibility = this.getAccessibilityPropertiesCurrent();

        properties.setProperty("accessibility", String.valueOf(!accessibility));

        try (FileOutputStream fos = new FileOutputStream(accessibility_properties_path)) {
            properties.store(fos, null);
        } catch (IOException e) {
            System.out.println("Error saving accessibility properties: " + e.getMessage());
        }
    }
}
