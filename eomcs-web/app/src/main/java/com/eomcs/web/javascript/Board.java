package com.eomcs.web.javascript;

import java.sql.Date;

public class Board {

  int no;
  String title;
  int viewCount;
  Date createdDate;

  public Board() {
    // TODO Auto-generated constructor stub
  }

  public Board(int no, String title,Date createdDate, int viewCount) {
    this.no = no;
    this.title = title;
    this.createdDate = createdDate;
    this.viewCount = viewCount;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }



}
