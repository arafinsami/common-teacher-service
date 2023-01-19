package com.eteacher.service.query;

import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.utils.PaginationParameters;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Component
public class GenericQuery<T> {

    public void sortBy(String[] sortable,
                       String sortBy,
                       CriteriaBuilder cb,
                       CriteriaQuery<?> query,
                       Root<?> root) {

        String[] sortByArr = sortBy.split(",");
        if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
            if (sortByArr[1].equals("desc")) {
                query.orderBy(cb.desc(root.get(sortByArr[0])));
            } else {
                query.orderBy(cb.asc(root.get(sortByArr[0])));
            }
        }
    }

    public void searchParam(
            String[] searchable,
            String search,
            CriteriaBuilder cb,
            CriteriaQuery<?> query,
            Root<?> root) {


        List<Predicate> predicates = new ArrayList<>();
        predicates.clear();
        for (String s : searchable) {
            predicates.add(cb.like(root.get(s), "%" + search + "%"));
        }
        query.where(cb.or(predicates.toArray(new Predicate[]{})));
    }

    public void filterMap(
            Map<String, Object> filterMap,
            CriteriaBuilder cb,
            CriteriaQuery<?> query,
            Root<?> root,
            List<Predicate> predicates) {
        if (Objects.nonNull(filterMap)) {
            for (String key : filterMap.keySet()) {
                if ((filterMap.get(key)) instanceof Integer) {
                   // if (!filterMap.get(key).equals(0))
                        predicates.add(cb.equal(root.get(key), filterMap.get(key)));
                } else if ((filterMap.get(key)) instanceof Double) {
                    if (!filterMap.get(key).equals(0))
                        predicates.add(cb.equal(root.get(key), filterMap.get(key)));
                } else if ((filterMap.get(key)) instanceof Long) {
                    if (!filterMap.get(key).equals(0))
                        predicates.add(cb.equal(root.get(key), filterMap.get(key)));
                } else if ((filterMap.get(key)) instanceof String) {
                    if (!filterMap.get(key).equals(""))
                        predicates.add(cb.equal(root.get(key), String.valueOf(filterMap.get(key))));
                } else if ((filterMap.get(key)) instanceof Enum) {
                    if (!filterMap.get(key).equals(""))
                        predicates.add(cb.equal(root.get(key), filterMap.get(key)));
                }else if ((filterMap.get(key)) instanceof Date) {
                    if (!filterMap.get(key).equals(""))
                        predicates.add(cb.equal(root.get(key), filterMap.get(key)));
                }
            }
            query.where(cb.and(predicates.toArray(new Predicate[]{})));
        }
    }

    public void deleteParam(
            CriteriaBuilder cb,
            CriteriaQuery<?> query,
            Root<?> root, List<Predicate> predicates) {

        predicates.add(cb.notEqual(root.get("recordStatus"), RecordStatus.DELETED));
        query.where(cb.and(predicates.toArray(new Predicate[]{})));

    }

    public CriteriaBuilder builder(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        return cb;
    }

    public Map<String, Object> getQueryMap(Integer page, Integer size, TypedQuery<T> tQuery, Long total) {
        List<T> result = tQuery
                .setFirstResult(Math.max((page - 1), 0) * size)
                .setMaxResults(size)
                .getResultList();
        Map<String, Object> maps = new HashMap<>();
        PaginationParameters.getdata(maps, page, total, size, result);
        return maps;
    }
}
