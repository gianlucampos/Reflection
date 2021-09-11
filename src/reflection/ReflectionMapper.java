package reflection;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionMapper {

    /**
     * @param obj
     * @return Map containing the attributes' names and it's values
     */
    public static Map<String, Object> getAttributesMap(Object obj) {
        Map<String, Object> attributesMap = new HashMap<>();
        Class<?> objClass = obj.getClass();
        Method[] methods = objClass.getMethods();

        for (Method method : methods) {
            if (method.getName().startsWith("get") && method.getReturnType() != void.class) {
                String attributeName = getAttributeName(method.getName());
                try {
                    Object value = method.invoke(obj);
                    attributesMap.put(attributeName, value);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(ReflectionMapper.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }

        return attributesMap;
    }

    private static String getAttributeName(String name) {
        return name.substring(3);
    }
}
