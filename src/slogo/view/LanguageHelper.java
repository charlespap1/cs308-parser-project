package slogo.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LanguageHelper {
  private ResourceBundle myResources;
  private static final String BASE_RESOURCES_PATH = "resources.labels.";
  private StringProperty commonCommandText = new SimpleStringProperty();
  private HashMap<String, StringProperty> labelMap = new HashMap<>();

      public LanguageHelper(StringProperty language)
      {
        myResources = ResourceBundle.getBundle(BASE_RESOURCES_PATH + language.getValue());
        setupMap();
        language.addListener((o, oldVal, newVal) -> changeLanguage(newVal));
      }

      public StringProperty getStringProperty(String key){
        return labelMap.get(key);
      }

      public String getLanguageBasedString(String key)
      {
        return labelMap.get(key).getValue();
      }

      private void setupMap(){
        for (String key : Collections.list(myResources.getKeys())) {
          StringProperty sp = new SimpleStringProperty();
          sp.setValue(myResources.getString(key));
          labelMap.put(key, sp);
        }
      }

      private void changeLanguage(String language){
        myResources = ResourceBundle.getBundle(BASE_RESOURCES_PATH + language);
        for (String key: labelMap.keySet()) {
          StringProperty sp = labelMap.get(key);
          sp.setValue(myResources.getString(key));
        }
      }



}
