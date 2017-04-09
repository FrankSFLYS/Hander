package top.mkswqi.hander.base_class;

/**
 * Created by FrankFLY on 2017/4/7.
 */

public enum SupportedTypes {

    String("String is one of supported types", java.lang.String.class),
    Integer("Integer is one of supported types", java.lang.Integer.class),
    Float("Float is one of supported types", java.lang.Float.class),
    Boolean("Boolean is one of supported types", java.lang.Boolean.class),
    Long("Long is one of supported types", java.lang.Long.class);

    private String description;
    private Class myClass;

    private <T> SupportedTypes(String description, Class<T> myClass) {
        this.description = description;
        this.myClass = myClass;
    }

    public String getDescription() {
        return description;
    }

    public Class getMyClass() {
        return myClass;
    }

}
