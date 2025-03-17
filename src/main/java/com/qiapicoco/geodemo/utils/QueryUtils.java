package com.qiapicoco.geodemo.utils;

import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    public static <T> List<T> filterList(List<T> list, QueryPredicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @FunctionalInterface
    public interface QueryPredicate<T> {
        boolean test(T item);
    }
}