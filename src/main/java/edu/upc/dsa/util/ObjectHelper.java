package edu.upc.dsa.util;


import java.lang.reflect.*;

//dado un objeto java nos ayuda a hacer operaciones de lectura o escritura
//ayuda a hacer getters y setters con los objetos
public class ObjectHelper {
    //operación getFields la utiliza el QueryHelper
    //
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        //hacemos introspección sobre la clase/objeto/entity
        //descubir atributos clase
        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        //recorrido para ver todos los atributos
        for (Field f: fields) sFields[i++]=f.getName();

        //nos devuelve los atributos de cada clase
        return sFields;

    }

    //abrimos instancia y establecemos con objeto y property y metodo la acción dentro de la clase
    //lanzamos dos excepciones debido al invoke
    public static void setter(Object entity, String property, Object value) throws InvocationTargetException, IllegalAccessException {

        /*
        * Object = User.class
        * property = Name
        * value = "Toni"
        * */

        Object result = null;
        Class theClass = entity.getClass();
        Method[] methods = theClass.getMethods();

        //este tipo de bucle ya lo hemos utilizado es un for each o for mejorado / este tipo evita bucle infinito
        //objeto method recorre toda la colección de methods
        //property=propiedad=atributo y value es el valor que queremos poner a esa propiedad
        for(Method method: methods){
            //por defecto boolean false
            //if(isSetter(method) == true)
            if(isSetter(method)){
                //regionMatches sirve para comparar dos strings equals
                //compara de caracter 3 (setName) de los methods bucle y de la posición 0 del parametro property Name
                //hasta el property.length / el ignoreCase=true elimina sensibilidad de mayusculas y minusculas
                if(method.getName().regionMatches(true, 3, property, 0, property.length())){
                    //invocamos el metodo seleccionado de la entidad con el valor que queramos
                    method.invoke(entity,value);
                }
            }
        }

    }

    //no me ha lanzado excepción NoSuchMethodException
    public static Object getter(Object entity, String property) throws InvocationTargetException, IllegalAccessException {
        //method invoke
        Object result = null;
        Class theClass = entity.getClass();
        Method[] methods = theClass.getMethods();

        for(Method method: methods){
            if(isGetter(method)){
                if(method.getName().regionMatches(true,3,property,0, property.length())){
                    result=method.invoke(entity);
                }
            }
        }
        return result;
    }

    //función getter
    public static boolean isGetter(Method method){
        //si no empieza por get devuelve falso
        if(!method.getName().startsWith("get")) return false;
        //si hay algun parametro inicial diferente a 0 seguro no será un getter
        if(method.getParameterTypes().length!=0) return false;
        //si no devuelve nada (void) seguro será un getter
        //void.class.equals(method.getReturnType())
        if(method.getReturnType().equals(Void.TYPE)) return false;
        return true;
    }

    //función isSetter
    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        //cuidado este if es para un setter que solo depende de un valor, si depende de 2 no se contempla
        //revisión sobre la siguiente línea por si da problemas
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }

}