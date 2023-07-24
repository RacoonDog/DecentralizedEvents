package io.github.racoondog.decentralizedevents;

public class ArrayUtils {
    public static int search(Object[] array, int arraySize, Object object) {
        int index = -1;
        for (int i = 0; i < arraySize; i++) {
            if (array[i] == object) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int search(Object[] array, Object object) {
        return search(array, array.length, object);
    }
}
