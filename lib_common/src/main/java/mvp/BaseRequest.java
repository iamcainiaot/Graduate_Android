package mvp;

import java.lang.reflect.Field;
import java.util.HashMap;

import lib_utils.MyLogUtil;

/**
 * @author taozhu5
 * @date 2019/3/13 10:24
 * @description 请求基础类
 */
public class BaseRequest {

    private HashMap<String, String> params = new HashMap<String, String>();

    public HashMap getParams() {
        if (params == null) {
            params = new HashMap<>();
        } else {
            params.clear();
        }
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                /**
                 * 获取此类中的所有字段
                 * Field[] fields = User.class.getDeclaredFields();
                 *
                 * 获取字段的名称
                 * String fieldName = field.getName();
                 */
                String key = field.getName();
                field.setAccessible(true);
                String value = null;
                Object valueObj = field.get(this);
                if (valueObj != null) {
                    // 将获取到的字段值转成String类型
                    value = String.valueOf(valueObj);
                }
                // put到参数map集合中
                params.put(key, value);
            } catch (Exception e) {
                MyLogUtil.d("BaseRequest异常：" + e);
            }
        }
        return params;
    }
}
