package com.vam.task.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BeanMapper {

    private static Mapper mapper = new DozerBeanMapper();

    /**
     * 简单的复制出新类型对象.
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     * 简单的复制出新对象ArrayList
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        List<D> destionationList = new ArrayList<D>();
        for (S source : sourceList) {
            if (source != null) {
                destionationList.add(mapper.map(source, destinationClass));
            }
        }
        return destionationList;
    }

    /**
     * 简单复制出新对象数组
     */
    public static <S, D> D[] mapArray(final S[] sourceArray, final Class<D> destinationClass) {

        D[] destinationArray = (D[]) Array.newInstance(destinationClass, sourceArray.length);

        int i = 0;
        for (S source : sourceArray) {
            if (source != null) {
                destinationArray[i] = mapper.map(sourceArray[i], destinationClass);
                i++;
            }
        }

        return destinationArray;
    }
}
