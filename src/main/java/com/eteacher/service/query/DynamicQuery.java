package com.eteacher.service.query;


import com.eteacher.service.enums.RecordStatus;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.eteacher.service.utils.StringUtils.isNotEmpty;

public class DynamicQuery<T> extends GenericQuery {

    public final Class<T> typeParameterClass;

    private final EntityManager em;

    public DynamicQuery(EntityManager em, Class<T> typeParameterClass) {
        this.em = em;
        this.typeParameterClass = typeParameterClass;
    }

    public List<T> getList(
            String[] sortable,
            String[] searchable,
            String sortBy,
            String search) {

        CriteriaBuilder cb = builder(em);
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        if (isNotEmpty(search)) {
            searchParam(searchable, search, cb, query, root);
        }

        if (isNotEmpty(sortBy)) {
            sortBy(sortable, sortBy, cb, query, root);
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<T> result = tQuery.getResultList();
        return result;
    }

    public Map<String, Object> getList(
            String[] sortable,
            String[] searchable,
            String sortBy,
            String search,
            Integer page,
            Integer size) {

        CriteriaBuilder cb = builder(em);
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        if (isNotEmpty(search)) {
            searchParam(searchable, search, cb, query, root);
        }

        if (isNotEmpty(sortBy)) {
            sortBy(sortable, sortBy, cb, query, root);
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        Map<String, Object> maps = getQueryMap(page, size, tQuery, total);
        return maps;
    }

    public List<T> getList(
            String[] sortable,
            String[] searchable,
            String sortBy,
            String search,
            Map<String, Object> filterMap) {

        CriteriaBuilder cb = builder(em);
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (isNotEmpty(search)) {
            searchParam(searchable, search, cb, query, root);
        }

        filterMap(filterMap, cb, query, root, predicates);



        if (isNotEmpty(sortBy)) {
            sortBy(sortable, sortBy, cb, query, root);
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<T> result = tQuery
                .getResultList();
        return result;
    }

    public Map<String, Object> getList(
            String[] sortable,
            String[] searchable,
            String sortBy,
            String search,
            Map<String, Object> filterMap,
            Integer page, Integer size) {

        CriteriaBuilder cb = builder(em);
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (isNotEmpty(search)) {
          // searchParam(searchable, search, cb, query, root);

            predicates.clear();
            List<String> searchList = Arrays.asList(searchable);
            searchList.forEach(s-> predicates.add(cb.like( root.get(s), "%" + search + "%")));
/*            for (String s : searchable) {

                Expression<String> filterKeyExp = root.get(s).as(String.class);


               // Predicate predicate = criteriaBuilder.like(filterKeyExp ,"%" + filterValue.trim().toLowerCase() + "%");

                predicates.add(cb.like(filterKeyExp, "%"+search+"%"));

                //predicates.add(cb.like(root.get(s.toString()), "%" + search + "%"));
            }*/
            query.where(cb.or(predicates.toArray(new Predicate[]{})));
        }

        deleteParam(cb, query, root,predicates);

        filterMap(filterMap, cb, query, root, predicates);

        if (isNotEmpty(sortBy)) {
            sortBy(sortable, sortBy, cb, query, root);
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        Map<String, Object> maps = getQueryMap(page, size, tQuery, total);
        return maps;
    }

    public List<T> findAll(String[] sortable, String sortBy) {
        CriteriaBuilder cb = builder(em);
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        if (isNotEmpty(sortBy)) {
            sortBy(sortable, sortBy, cb, query, root);
        }
        TypedQuery<T> tQuery = em.createQuery(query);
        return tQuery.getResultList();
    }
}
