package cn.marcus.json.api.support;

import cn.marcus.json.api.AbstractJsonFacade;
import cn.marcus.json.api.TypeReference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;

import java.io.OutputStream;
import java.util.List;

/**
 * fastjson接口
 * @author 北城微雨
 * @date 2022/5/25
 */
public class FastJsonHandler extends AbstractJsonFacade {

    @Override
    public String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public <T> T parseObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr,clazz);
    }

    @Override
    public <T> T parseObject(String jsonStr, TypeReference<T> valueTypeRef) {
        return JSON.parseObject(jsonStr,valueTypeRef.getType());
    }

    @Override
    public <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        return JSONArray.parseArray(jsonStr,clazz);
    }

    @Override
    @SneakyThrows
    public void writeJson(OutputStream os, Object object){
        JSONObject.writeJSONString(os,object);
    }
}
