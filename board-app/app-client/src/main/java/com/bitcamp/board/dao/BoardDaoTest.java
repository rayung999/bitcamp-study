package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Board;

public class BoardDaoTest {

  public static void main(String[] args) throws Exception {
    MariaDBBoardDao dao = new MariaDBBoardDao();
    List<Board> list = dao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }
    System.out.println("-----------------------------");

    //    Board board = new Board();
    //    board.title = "aaaaa";
    //    board.content = "bbbbb";
    //    board.memberNo = 2;
    //    dao.insert(board);

    //    dao.delete(21);

    Board board = new Board();
    board.no = 15;
    board.title = "gaga";
    board.content = "okok";
    dao.update(board);

    Board board2 = dao.findByNo(15);
    System.out.println(board2);

    //    Board member = dao.findByNo(2);
    //    System.out.println(member);

    list = dao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }
    System.out.println("-----------------------------");
  }   
}
