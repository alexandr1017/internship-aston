package org.alexandr1017;


public class Main {
    public static void main(String[] args) {
        MyArrayList<String> stringMyArrayList = new MyArrayList<>();

        for (int i = 0; i < 20; i++) {
            if (stringMyArrayList.getCapacity() >= 10) System.out.println("Ёмкость внутреннего массива в списке: " + stringMyArrayList.getCapacity());
            stringMyArrayList.add("Лошадь");
            stringMyArrayList.add("Бык");
            stringMyArrayList.add("Коза");
        }
        stringMyArrayList.sort(String::compareTo);


        System.out.println(stringMyArrayList);


        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(8);
        list.add(9);
        list.add(3);
        list.add(7);
        list.add(6);
        list.add(7);
        list.add(1);
        System.out.println("Сортируем список: ");
        list.sort(Integer::compare);
        System.out.println(list);

        System.out.println("Ищем число 9 в списке. Его индекс: " + list.search(9,Integer::compare));

    }
}