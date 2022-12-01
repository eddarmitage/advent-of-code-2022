package com.eddarmitage.caloriecounting;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public class FixedCapacityBiggestValuesBuffer<E extends Comparable<E>> {

    private final int capacity;
    private final SortedSet<E> set = new TreeSet<>(Comparator.naturalOrder());

    public FixedCapacityBiggestValuesBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void offer(E newElement) {
        if (set.size() < capacity) {
            set.add(newElement);
        } else {
            E currentThreshold = set.first();
            if (newElement.compareTo(currentThreshold) > 0) {
                set.remove(currentThreshold);
                set.add(newElement);
            }
        }
    }

    public Stream<E> stream() {
        return set.stream();
    }
}
