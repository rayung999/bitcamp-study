package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.bitcamp.board.domain.Member;
import com.google.gson.Gson;

public class MariaDBMemberDao {

  public int insert(Member member) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "insert into app_member(name,email,pwd) values(?,?,sha2(?,256))")) { // 값을 넣을 자리를 인-파라미터로 표시

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);

      return pstmt.executeUpdate();
    }
  }

  public Member findByNo(int no) throws Exception {

    // try (java.lang.AutoCloseable 타입의 변수만 가능) {}
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "select mno,name,email,cdt from app_member where mno=?")) { // 값을 넣을 자리를 인-파라미터로 표시

      pstmt.setInt(1, no); // 밑의 try문을 위의 try문이랑 합치면 안된다. 변수 선언이 아니라 그냥 메서드 호출이기 때문에.

      try (ResultSet rs = pstmt.executeQuery()) {
        // DDL 명령어를 사용하려면 executeUpdate()!!

        if (!rs.next()) {
          return null;
        }

        Member member = new Member();
        member.no = rs.getInt("mno");
        member.name = rs.getString("name");
        member.email = rs.getString("email");
        member.createdDate = rs.getDate("cdt");

        return member;
      }
    }
  }

  public int update(Member member) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "update app_member set name=?, email=?,pwd=sha2(?,256) where mno=?")) { // 값을 넣을 자리를 인-파라미터로 표시

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);
      pstmt.setInt(4,  member.no);

      return pstmt.executeUpdate();
    }
  }

  public boolean delete(String email) throws Exception {
    try (Socket socket = new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());) {
      out.writeUTF(dataName);
      out.writeUTF("delete");
      out.writeUTF(email);

      return in.readUTF().equals("success");
    }
  }

  public Member[] findAll() throws Exception {
    try (Socket socket = new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());) {
      out.writeUTF(dataName);
      out.writeUTF("findAll");

      if (in.readUTF().equals("fail")) {
        return null;
      }
      return new Gson().fromJson(in.readUTF(), Member[].class);
    }
  }
}














