package lib.list;


import java.util.*;

class TestList {
    public static void main(String[] args) {
      List<String> list = new ArrayList<String>();
      list.add("a");
      list.add("b");
      System.out.println(list);
      list.add("c");
      System.out.println(list);
      List<String> list2 = Collections.unmodifiableList(list);
      System.out.println(list2);
      list2.add("d");
      System.out.println(list2);
    }
  }