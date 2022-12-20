package ru.khrip.laba5;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
@Component
@ComponentScan
public class Injector {
@Autowired
    public Injector(){}

    public <T> T inject(T obj, String str) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Properties properties=new Properties();

        properties.load(Injector.class.getClassLoader().getResourceAsStream("application.properties"));

        Class clazz= Class.forName(properties.getProperty(str));
        Class clazz1= Class.forName(properties.getProperty("SomeOtherInterface"));

        Field[] fields=obj.getClass().getDeclaredFields();

        for(Field field : fields)
        {
            field.setAccessible(true);
            if(field.isAnnotationPresent(AutoInjectable.class) && field.getType() == SomeInterface.class)
                field.set(obj,clazz.newInstance());
            if(field.isAnnotationPresent(AutoInjectable.class) && field.getType() == SomeOtherInterface.class)
                field.set(obj,clazz1.newInstance());
        }
        return obj;
    }
}
