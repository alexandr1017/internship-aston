package org.alexandr1017;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс MyArrayList представляет собой реализацию динамического массива.
 * Поддерживает операции добавления, удаления, получения элемента по индексу,
 * установки элемента по индексу, очистки массива, получения размера и ёмкости массива,
 * сортировки элементов и итерации по элементам.
 *
 * @param <E> тип элементов в массиве
 */

public class MyArrayList<E> implements Iterable<E> {

    // поля класса
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elements;
    private int size;

    /**
     * Конструктор класса. Создает массив с начальной ёмкостью DEFAULT_CAPACITY и нулевым размером.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Добавляет элемент в конец массива.
     *
     * @param element элемент для добавления
     */
    public void add(E element) {
        increaseCapacityAndCopyArray(size + 1);
        elements[size++] = element;
    }

    /**
     * Добавляет элемент в указанную позицию массива.
     *
     * @param index   позиция для добавления
     * @param element элемент для добавления
     */
    public void add(int index, E element) {
        validIndex(index);
        increaseCapacityAndCopyArray(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по указанной позиции в массиве.
     *
     * @param index позиция элемента
     * @return элемент по указанной позиции
     */
    public E get(int index) {
        validIndex(index);
        return (E) elements[index];
    }

    /**
     * Удаляет элемент по указанной позиции из массива.
     *
     * @param index позиция элемента для удаления
     */
    public void remove(int index) {
        validIndex(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
    }

    /**
     * Очищает массив от всех элементов.
     */
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Устанавливает новое значение для элемента по указанной позиции в массиве.
     *
     * @param index   позиция элемента
     * @param element новое значение элемента
     */
    public void set(int index, E element) {
        validIndex(index);
        elements[index] = element;
    }

    /**
     * Возвращает размер массива.
     *
     * @return размер массива
     */
    public int getSize() {
        return size;
    }

    /**
     * Возвращает ёмкость массива.
     *
     * @return ёмкость массива
     */
    public int getCapacity() {
        return elements.length;
    }


    /**
     * Уменьшает ёмкость массива до его размера.
     */
    public void trimToSize() {
        if (size < elements.length) {
            elements = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elements, size);
        }
    }

    /**
     * Сортирует элементы массива с использованием заданного компаратора методом быстрой сортировки.
     *
     * @param comparator компаратор для сравнения элементов
     */
    public void sort(Comparator<? super E> comparator) {
        quickSort((E[]) elements, 0, size - 1, comparator);
    }

    /**
     * Увеличивает ёмкость массива, если необходимо, и копирует элементы в новый массив.
     *
     * @param minCapacity минимальная ёмкость для добавления нового элемента
     */
    private void increaseCapacityAndCopyArray(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = minCapacity * 3 / 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Проверяет, что указанный индекс находится в допустимых границах массива.
     *
     * @param index индекс для проверки
     */
    private void validIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

    }

    /**
     * Возвращает строковое представление массива в виде [элемент1, элемент2, ..., элементN].
     *
     * @return строковое представление массива
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Возвращает итератор для перебора элементов массива.
     *
     * @return итератор для перебора элементов массива
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * Внутренний класс-итератор для перебора элементов массива.
     */
    private class MyIterator implements Iterator<E> {
        private int iteratorIndex = 0;

        /**
         * Проверяет, есть ли следующий элемент в массиве.
         *
         * @return true, если есть следующий элемент, иначе false
         */
        @Override
        public boolean hasNext() {
            return iteratorIndex < size;
        }

        /**
         * Возвращает следующий элемент массива и перемещает указатель на следующую позицию.
         *
         * @return следующий элемент массива
         * @throws NoSuchElementException если достигнут конец массива
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(iteratorIndex++);
        }
    }

    /**
     * Находит индекс искомого элемента в отсортированном массиве с использованием заданного компаратора методом binary search.
     *
     * @param item       искомый элемент
     * @param comparator компаратор для сравнения элементов
     * @return индекс найденного элемента массива, либо -1, если поиск не дал результата
     */
    public int search(E item, Comparator<? super E> comparator) {
        trimToSize();
        return binarySearch((E[]) elements, item, comparator);
    }

    private int binarySearch(E[] array, E item, Comparator<? super E> comparator) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            E guess = array[mid];
            int compare = comparator.compare(guess, item);
            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Быстрая сортировка элементов массива с использованием заданного компаратора.
     *
     * @param array      массив для сортировки
     * @param left       левая граница для сортировки
     * @param right      правая граница для сортировки
     * @param comparator компаратор для сравнения элементов
     */
    private void quickSort(E[] array, int left, int right, Comparator<? super E> comparator) {
        int index = 0;
        if (array.length > 1) {
            index = partition(array, left, right, comparator);
            if (left < index - 1) {
                quickSort(array, left, index - 1, comparator);
            }
            if (index < right) {
                quickSort(array, index, right, comparator);
            }
        }
    }

    /**
     * Разбивает массив на две части вокруг опорного элемента и возвращает индекс опорного элемента.
     *
     * @param array      массив для разбиения
     * @param left       левая граница для разбиения
     * @param right      правая граница для разбиения
     * @param comparator компаратор для сравнения элементов
     * @return индекс опорного элемента
     */
    private int partition(E[] array, int left, int right, Comparator<? super E> comparator) {
        E half = array[(left + right) / 2];
        while (left <= right) {
            while (comparator.compare(array[left], half) < 0) {
                left++;
            }
            while (comparator.compare(array[right], half) > 0) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;

    }

    /**
     * Меняет местами два элемента массива.
     *
     * @param array       массив для замены элементов
     * @param firstIndex  индекс первого элемента
     * @param secondIndex индекс второго элемента
     */
    private void swap(E[] array, int firstIndex, int secondIndex) {
        E temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
