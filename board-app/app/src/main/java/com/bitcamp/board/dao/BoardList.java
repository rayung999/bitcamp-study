package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardList extends ObjectList {

  private int no = 0;


  // 수퍼 클래스의 get() 메서드를 BoardList에 맞게 재정의 한다.
  // => 파라미터는 인덱스가 아닌 게시글 번호가 되게 한다.
  // => Overriding 이라 부른다.
  @Override
  public Board get(int boardNo) {
    for (int i = 0; i < this.length; i++) {
      // Object 배열에 실제 들어 있는 것은 Board라고 컴파일러에게 알린다.
      Board board = (Board) this.list[i]; 

      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  // Super클래스의 add() 를 BoardList에 맞게끔 재정의한다.
  // => 파라미터로 받은 BOard 인스턴스의 no 변수 값을 설정한 다음 배열에 추가한다.
  // => Overriding 이라 부른다.
  @Override
  public void add(Object obj) {
    Board board = (Board) obj;
    board.no = nextNo();

    // 재정의하기 전의 Super클래스의 add()를 사용하여 처리한다.
    super.add(board);

  }

  @Override
  // Super클래스의 remove()를 BoardList클래스의 역할에 맞춰 재정의한다.
  public boolean remove(int boardNo) {
    int boardIndex = -1;
    for (int i = 0; i < this.length; i++) {
      Board board = (Board) this.list[i];
      if (board.no == boardNo) {
        boardIndex = i;
        break;
      }
    }

    return super.remove(boardIndex); // 재정의 하기 전에 Super클래스의 메서드를 호출한다.
  }

  private int nextNo() {  // Handler에서 호출하지 않기 때문에 오히려 공개 제한
    return ++no;
  }
}














