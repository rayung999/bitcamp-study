package com.eomcs.oop.ex02.test;

public class Score {

  public String name; // 필드
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float aver;

  public Score(String n, int k, int e, int m) {
    this.name = n;
    this.kor = k;
    this.eng = e;
    this.math = m;

    this.compute();
  }

  public void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;

  }
}

