package com.warehouse.util.tools;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 比较类
 */
public class ClassAttrValCompare<T> {
    /**
     * 新对象里的不为空的属性值与旧对象比较
     *
     * @param oldObj 旧对象
     * @param newObj 新对象
     * @return 返回差异列表
     */
    public static <T> List<Map<String, Object>> newCompareToOld(T oldObj, T newObj) {
        List<Map<String, Object>> list = new ArrayList<>();
        //获取对象的class
        Class<?> oldClazz = oldObj.getClass();
        Class<?> newClazz = newObj.getClass();

        //获取包路径
        String oldName = oldClazz.getName();
        String[] arrayStr = oldName.split("\\.");
        List<String> objArray = new ArrayList<>(Arrays.asList(arrayStr));
        objArray.remove(objArray.size() - 1);//删掉最后一个元素
        String packPath = String.join(".", objArray);


        //不是同一类型对象
        if (!oldClazz.getName().equals(newClazz.getName())) {
            return list;
        }

        //获取对象的属性列表
        Field[] field1 = oldClazz.getDeclaredFields();
        //遍历属性列表field1
        try {
            for (int i = 0; i < field1.length; i++) {
                Field field = field1[i];
                String fieldName = field.getName();

                field.setAccessible(true);
                Object oldValue = field.get(oldObj);
                Object newValue = field.get(newObj);

                if (newValue == null) {
                    continue;
                }
                if (!(newValue instanceof List)) {
                    if (!oldValue.equals(newValue)) {
                        Map<String, Object> map2 = new HashMap<String, Object>();

                        String className = fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1, fieldName.length());
                        String filedClass = packPath + "." + className;

                        //判断类是否存在
                        if (judeClassExist(filedClass)) {
                            //获取对象的属性列表
                            Class<?> newInnerClazz = newValue.getClass();
                            Field[] newInnerFields = newInnerClazz.getDeclaredFields();
                            for (int j = 0; j < newInnerFields.length; j++) {
                                Field newInnerField = newInnerFields[j];
                                newInnerField.setAccessible(true);

                                if (newInnerField.getName() != null
                                        && newInnerField.getName().equals("id")) {
                                    String strNewId = newInnerField.get(newValue) != null ? newInnerField.get(newValue).toString():null;
                                    String strOldId = newInnerField.get(oldValue) != null ? newInnerField.get(oldValue).toString():null;
                                    if (strNewId !=null
                                            && strOldId!=null
                                            && !strNewId.equals(strOldId)) {
                                        map2.put("name", fieldName);
                                        map2.put("old", strOldId);
                                        map2.put("new", strNewId);
                                        list.add(map2);
                                        break;//找到id且不相等就退出
                                    }
                                }
                            }
                        } else {
                            map2.put("fieldName", fieldName);
                            map2.put("oldValue", oldValue);
                            map2.put("newValue", newValue);
                            list.add(map2);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断类是否存在
     *
     * @param modeName
     * @return
     */
    private static boolean judeClassExist(String modeName) {
        try {
            Class<?> innerClazz = Class.forName(modeName);
            return true;
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            //System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 修改字段，组装成String返回
     * @return
     */
    public static String updateFieldToStr(List<Map<String,Object>> mapList){
        //适用于单线程下在字符缓冲区进行大量操作的情况
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<mapList.size();i++){
            for(Map.Entry<String,Object> entry:mapList.get(i).entrySet()){
                stringBuilder.append(entry.getKey());
                stringBuilder.append(":");
                stringBuilder.append(entry.getValue());
                stringBuilder.append(",");
            }
            if(stringBuilder.lastIndexOf(",",stringBuilder.length()-1)>0){
                stringBuilder.deleteCharAt(stringBuilder.length()-1);//删掉最后一个逗号
            }
            stringBuilder.append("\\\\");
        }
        //删掉最后一个"\\"
        int i = stringBuilder.lastIndexOf("\\\\", stringBuilder.length() - 1);
        System.out.println(i);
        if(i>=0){
            stringBuilder.replace(stringBuilder.length()-2,stringBuilder.length(),"");
        }
        return stringBuilder.toString();
    }

    /*public static void main(String[] args) {
        ClassAttrValCompare attrCompare = new ClassAttrValCompare();

        EntrustOrder e1 = new EntrustOrder();
        e1.setCreateManId("1");
        e1.setCreateMan("张费费");
        e1.setFee(BigDecimal.valueOf(21.00));
        Warehouse w1 = new Warehouse();
        w1.setId("10032");
        e1.setWarehouse(w1);

        EntrustOrder e2 = new EntrustOrder();
        e2.setCreateManId("2");
        e2.setCreateMan("张费费2");
        e2.setFee(BigDecimal.valueOf(21.01));
        Warehouse w2 = new Warehouse();
        w2.setId("10032");
        e2.setWarehouse(w2);

        List<Map<String, Object>> maps = ClassAttrValCompare.newCompareToOld(e1, e2);

        System.out.println(ClassAttrValCompare.updateFieldToStr(maps));

    }*/
}
