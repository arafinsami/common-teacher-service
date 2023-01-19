package com.eteacher.service.procedure;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.eteacher.service.utils.JsonUtils.jsonArray;
import static com.eteacher.service.utils.JsonUtils.jsonObject;
import static com.eteacher.service.utils.StringUtils.isEmpty;

@Data
@NoArgsConstructor
public class PaymentMethodProcedure {

    private Long id;

    private String paymentMethodName;

    private String paymentMethodNameBn;

    public static List<PaymentMethodProcedure> toList(Map<String, Object> out) {
        List<PaymentMethodProcedure> views = new ArrayList<>();
        List<Map<String, Object>> lists = (List<Map<String, Object>>) out.get("#result-set-1");
        lists.forEach(l -> {
            PaymentMethodProcedure view = new PaymentMethodProcedure();
            view.setId((Long) l.get("PAYMENT_METHOD_ID"));
            view.setPaymentMethodName((String) l.get("PAYMENT_METHOD_NAME"));
            view.setPaymentMethodNameBn((String) l.get("PAYMENT_METHOD_NAME_BN"));
            views.add(view);
        });
        return views;
    }

    public static Map<String, Object> search(String paymentMethodName,
                                             String sort,
                                             Integer page,
                                             Integer size) {
        Map<String, Object> map = new HashMap<>();
        map.put("SEARCH_ENITITY", "PAYMENT_METHOD");
        if (isEmpty(paymentMethodName)) {
            map.put("SEARCH_CHAR", "");
            map.put("SEARCH_CRITERIA", "");
            map.put("SORT_OPTIONS", sort(sort));
            map.put("PAGE_NO", page);
            map.put("PAGE_PER_ROW", size);
        } else {
            map.put("SEARCH_CHAR", "");
            map.put("SEARCH_CRITERIA", searchCriteria(paymentMethodName));
            map.put("SORT_OPTIONS", sort(sort));
            map.put("PAGE_NO", page);
            map.put("PAGE_PER_ROW", size);
        }
        return map;
    }

    public static JSONArray searchCriteria(String paymentMethodName) {
        JSONArray searchColumnArr = jsonArray();
        JSONObject methodName = jsonObject();
        methodName.put("SEARCH_COLUMN", "PAYMENT_METHOD_NAME");
        methodName.put("SEARCH_CHAR", paymentMethodName);
        searchColumnArr.add(methodName);
        System.out.println(searchColumnArr.toJSONString());
        return searchColumnArr;
    }

    public static JSONArray sort(String sort) {
        JSONArray sortColumnArr = jsonArray();
        JSONObject methodName = jsonObject();
        methodName.put("SORT_COLUMN", "PAYMENT_METHOD_ID");
        methodName.put("SORT_TYPE", sort);
        sortColumnArr.add(methodName);
        System.out.println(sortColumnArr.toJSONString());
        return sortColumnArr;
    }

    public static Map<String, Object> getResponse(
            Integer page,
            Integer size,
            Map<String, Object> map,
            List<?> lists) {
        Map<String, Object> response = new HashMap<>();
        response.put("lists", lists);
        response.put("previousPage", map.get("PREVIOUSPAGE"));
        response.put("nextPage", map.get("NEXTPAGE"));
        response.put("currentPage", map.get("CURRENTPAGE"));
        response.put("total", map.get("TOTAL"));
        response.put("page", page);
        response.put("size", size);
        return response;
    }
}
