package com.eteacher.service.helper;

import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.service.BaseService;
import com.eteacher.service.utils.PaginationParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static com.eteacher.service.utils.StringUtils.isNotEmpty;

public class GetListHelper<T> extends BaseService {

    private final EntityManager em;

    final Class<T> typeParameterClass;

    public GetListHelper(EntityManager em, Class<T> typeParameterClass) {
        this.em = em;
        this.typeParameterClass = typeParameterClass;
    }

    /**
     * Sort with attribute
     *
     * @param sortBy sort by which attribute of entity
     * @param page   traverse in which page
     * @param size   number of items per request
     * @return corresponding pageable, or null
     */
    public Pageable getPageable(String sortBy, Integer page, Integer size) {
        Pageable pageable;
        if (sortBy.isEmpty()) pageable = PageRequest.of(page - 1, size);
        else {
            String[] parts = sortBy.split(",");
            pageable = PageRequest.of(page - 1, size, Sort.by(parts[0]));
            if (parts[1].equals("desc")) pageable = PageRequest.of(page - 1, size, Sort.by(parts[0]).descending());
        }
        return pageable;
    }

    public List<T> getList(String[] sortable, String[] searchable, String sortBy, String search) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        if (isNotEmpty(search)) {
            List<Predicate> predicates = new ArrayList<>();
            for (String s : searchable) {
                predicates.add(cb.like(root.get(s), "%" + search + "%"));
            }
            query.where(cb.or(predicates.toArray(new Predicate[]{})));
        }

        if (isNotEmpty(sortBy)) {
            String[] sortByArr = sortBy.split(",");
            logger.info(String.valueOf(sortByArr[1].equals("desc")));
            if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
                if (sortByArr[1].equals("desc")) {
                    query.orderBy(cb.desc(root.get(sortByArr[0])));
                } else {
                    query.orderBy(cb.asc(root.get(sortByArr[0])));
                }
            }
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<T> result = tQuery.getResultList();

        return result;
    }

    public Map<String, Object> getList(String[] sortable, String[] searchable, String sortBy, String search, Integer page, Integer size) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        if (isNotEmpty(search)) {
            List<Predicate> predicates = new ArrayList<>();
            for (String s : searchable) {
                predicates.add(cb.like(root.get(s), "%" + search + "%"));
            }
            query.where(cb.or(predicates.toArray(new Predicate[]{})));
        }

        if (isNotEmpty(sortBy)) {
            String[] sortByArr = sortBy.split(",");
            logger.info(String.valueOf(sortByArr[1].equals("desc")));
            if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
                if (sortByArr[1].equals("desc")) {
                    query.orderBy(cb.desc(root.get(sortByArr[0])));
                } else {
                    query.orderBy(cb.asc(root.get(sortByArr[0])));
                }
            }
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<T> result = tQuery.setFirstResult(Math.max((page - 1), 0) * size)
                .setMaxResults(size)
                .getResultList();

        Map<String, Object> maps = new HashMap<>();
        PaginationParameters.getdata(maps, page, total, size, result);

        return maps;
    }

    public List<T> getList(String[] sortable, String[] searchable, String sortBy, String search, Map<String, Object> filterMap) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);


        if (isNotEmpty(search)) {
            List<Predicate> predicates = new ArrayList<>();
            for (String s : searchable) {
                predicates.add(cb.like(root.get(s), "%" + search + "%"));
            }
            query.where(cb.or(predicates.toArray(new Predicate[]{})));

            System.out.println(cb);
        }
/*

        if (filterMap.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (String key : filterMap.keySet()) {
                if ((filterMap.get(key)) instanceof Integer) {
                    if (!filterMap.get(key).equals(0))
                        predicates.add(cb.equal(root.get(key), (Integer) filterMap.get(key)));
                } else if ((filterMap.get(key)) instanceof Double) {
                    if (!filterMap.get(key).equals(0))
                        predicates.add(cb.equal(root.get(key), (Double) filterMap.get(key)));
                } else if ((filterMap.get(key)) instanceof Long) {
                    if (!filterMap.get(key).equals(0))
                        predicates.add(cb.equal(root.get(key), (Long) filterMap.get(key)));
                } else if ((filterMap.get(key)) instanceof String) {
                    if (!filterMap.get(key).equals(""))
                        predicates.add(cb.equal(root.get(key), String.valueOf(filterMap.get(key))));
                } else if ((filterMap.get(key)) instanceof Enum) {
                    if (!filterMap.get(key).equals(""))
                        predicates.add(cb.equal(root.get(key), filterMap.get(key)));
                }
            }
            query.where(cb.and(predicates.toArray(new Predicate[]{})));

            System.out.println(cb.toString());
        }
*/


        if (isNotEmpty(sortBy)) {
            String[] sortByArr = sortBy.split(",");
            logger.info(String.valueOf(sortByArr[1].equals("desc")));
            if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
                if (sortByArr[1].equals("desc")) {
                    query.orderBy(cb.desc(root.get(sortByArr[0])));
                } else {
                    query.orderBy(cb.asc(root.get(sortByArr[0])));
                }
            }
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<T> result = tQuery
                .getResultList();

        return result;
    }

    public Map<String, Object> getList(String[] sortable, String[] searchable, String sortBy, String search, Map<String, Object> filterMap, Integer page, Integer size) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        //List<Predicate> predicates = new ArrayList<>();

        if (filterMap.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (String key : filterMap.keySet()) {
                if ((filterMap.get(key)) instanceof Integer) {
                    if (!filterMap.get(key).equals(0))
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
                } else if ((filterMap.get(key)) instanceof Date) {
                    if (!filterMap.get(key).equals(""))
                        predicates.add(cb.equal(root.get(key), filterMap.get(key)));
                }
            }
            query.where(cb.and(predicates.toArray(new Predicate[]{})));
        }


        if (isNotEmpty(search)) {
            List<Predicate> predicates = new ArrayList<>();
            for (String s : searchable) {
                predicates.add(cb.like(root.get(s), "%" + search + "%"));
            }
            query.where(cb.or(predicates.toArray(new Predicate[]{})));
        }

        if (isNotEmpty(sortBy)) {
            String[] sortByArr = sortBy.split(",");
            logger.info(String.valueOf(sortByArr[1].equals("desc")));
            if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
                if (sortByArr[1].equals("desc")) {
                    query.orderBy(cb.desc(root.get(sortByArr[0])));
                } else {
                    query.orderBy(cb.asc(root.get(sortByArr[0])));
                }
            }
        }

        TypedQuery<T> tQuery = em.createQuery(query);
        Long total = (long) tQuery.getResultList().size();
        List<T> result = tQuery.setFirstResult(Math.max((page - 1), 0) * size)
                .setMaxResults(size)
                .getResultList();

        Map<String, Object> maps = new HashMap<>();
        PaginationParameters.getdata(maps, page, total, size, result);

        return maps;
    }

    public List<T> findAll(String[] sortable, String sortBy) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(typeParameterClass);
        Root<T> root = query.from(typeParameterClass);
        query.select(root);

        if (isNotEmpty(sortBy)) {
            String[] sortByArr = sortBy.split(",");
            logger.info(String.valueOf(sortByArr[1].equals("desc")));
            if (sortByArr.length <= 2 && Arrays.asList(sortable).contains(sortByArr[0])) {
                if (sortByArr[1].equals("desc")) {
                    query.orderBy(cb.desc(root.get(sortByArr[0])));
                } else {
                    query.orderBy(cb.asc(root.get(sortByArr[0])));
                }
            }
        }
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.notEqual(root.get("recordStatus"), RecordStatus.DELETED));
        query.where(cb.and(predicates.toArray(new Predicate[]{})));

        TypedQuery<T> tQuery = em.createQuery(query);

        return tQuery.getResultList();
    }

    /**
     * Page data converted to meta data
     *
     * @param result items of page
     * @param page   traverse in which page
     * @param size   number of items per request
     * @return paginated data
     */
    public Map<String, Object> getList(Page<T> result, Integer page, Integer size) {
        Long total = result.getTotalElements();
        Map<String, Object> maps = new HashMap<>();
        PaginationParameters.getdata(maps, page, total, size, result.getContent());
        return maps;
    }
}
