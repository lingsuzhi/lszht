package com.lszpro.soa;

import com.alibaba.fastjson.TypeReference;
import com.lszpro.soa.common.ResponseInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class UrlCache {


    private static List<UrlMappingDTO> list = null;
    private static UrlMappingDTO AllUrl = new UrlMappingDTO("所有接口", "/api/init/findAllUrl");

    public static UrlMappingDTO getAllUrl() {
        return AllUrl;
    }

    public static void setList(List<UrlMappingDTO> list) {
        UrlCache.list = list;
    }

    public static UrlMappingDTO getUrlMapping(String name) {
        if (CollectionUtils.isEmpty(list)) {

            ResponseInfo<List<UrlMappingDTO>> responseInfo = SoaFactory.get(AllUrl, null, new TypeReference<ResponseInfo<List<UrlMappingDTO>>>() {
            });
            if (responseInfo.isSuccess()) {
                list = responseInfo.getData();
            }
            if (CollectionUtils.isEmpty(list)) {
                return null;
            }
        }

        for (UrlMappingDTO urlMappingDTO : list) {
            if (name.equals(urlMappingDTO.getName())) {
                return urlMappingDTO;
            }
        }
        return null;
    }
}
