package com.bitcamp.board;

public class BoardTest {
  public static void main(String[] args) {
    Board b1;
    Board b2;

    b1 = new Board();
    b1.no = 1;

    b2 = b1;
    b2.no = 100;

    b1 = new Board();


    System.out.println(b1.no);


    /*
     * public class BoardTest {
          public static void main(String[] args) {
            Board[] arr = new Board[100];

            for (int i=0; i< arr.length; i++)
            arr[i] = new Board();
     */
  }
}

