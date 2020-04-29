package ditask.container.marks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// предполагает создание объекта
// установка значений для полей (свойств) осуществляется
// на основе конфиг файла
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigClass {
    String file() default "config.properties";
    String prefix();
}
