package generic.test;

interface Money<E>{

   E get(int index);
   boolean add(E e);

}