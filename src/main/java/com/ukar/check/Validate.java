package com.ukar.check;

import com.ukar.annotation.ValidateAnno;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author jia.you
 * @date 2019/02/26
 */
public class Validate {

    public void validate(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Class<?> superclass = clazz.getSuperclass();
        //获取成员变量上所有注解
        Field[] sonFields = clazz.getDeclaredFields();
        Field[] superFields = superclass.getDeclaredFields();
        Field[] fields = new Field[sonFields.length + superFields.length];
        System.arraycopy(sonFields, 0, fields, 0, sonFields.length);
        System.arraycopy(superFields, 0, fields, sonFields.length, superFields.length);

        for (Field field : fields) {
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            boolean boo = field.isAnnotationPresent(ValidateAnno.class);
            if (!boo) {
                continue;
            }
            ValidateAnno annotation = field.getAnnotation(ValidateAnno.class);
            int maxLength = annotation.maxLength();
            boolean nullAble = annotation.nullAble();
            String name = field.getName();
            String value = (String) field.get(object);
            if (!nullAble && StringUtils.isBlank(value)) {
                System.out.println(String.format("字段%s不允许为空", name));
                return;
            }

            if (StringUtils.isNotBlank(value) && value.length() > maxLength) {
                System.out.println(String.format("字段%s不能超过%d位", name, maxLength));
                return;
            }
        }
        System.out.println("校验通过，全部符合要求");
    }
}
