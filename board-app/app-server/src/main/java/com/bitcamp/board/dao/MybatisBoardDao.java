package com.bitcamp.board.dao;

import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

@Repository
// 이 애노테이션을 붙이면 Spring IoC Container가 객체를 자동으로 생성한다.
// 물론 생성자의 파라미터 값을 자동으로 주입한다. 
// 파라미터에 해당하는 객체가 없다면 생성 오류가 발생한다.
public class MybatisBoardDao implements BoardDao {

  @Autowired
  SqlSessionFactory sqlSessionFactory;

  @Autowired
  DataSource ds;

  @Override
  public int insert(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("BoardDao.insert", board);
    }
  }

  @Override
  public Board findByNo1(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      Board board = sqlSession.selectOne("BoardDao.findByNo", no);

      List<AttachedFile> attachedFiles = sqlSession.selectList("BoardDao.findFilesByBoard", no);

      board.setAttachedFiles(attachedFiles);

      return board;
    }
  }

  @Override
  public Board findByNo2(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BoardDao.findByNo", no);
    }
  }

  @Override
  public Board findByNo3(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BoardDao.findByNo3", no);
    }
  }


  @Override
  public int update(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("BoardDao.update", board);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BoardDao.delete", no);
    }
  }

  @Override
  public int deleteByMember(int memberNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BoardDao.deleteByMember", memberNo);
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BoardDao.findAll");
    }
  }


  @Override
  public int insertFiles(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("BoardDao.insertFiles", board);
    }
  }

  @Override
  public AttachedFile findFileByNo(int fileNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BoardDao.findFileByNo", fileNo);
    }
  }

  @Override
  public List<AttachedFile> findFilesByBoard(int boardNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BoardDao.findFilesByBoard", boardNo);
    }
  }

  @Override
  public int deleteFile(int fileNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BoardDao.deleteFile", fileNo);
    }
  }

  @Override
  public int deleteFiles(int boardNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BoardDao.deleteFiles", boardNo);
    }
  }

  @Override
  public int deleteFilesByMemberBoards(int memberNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BoardDao.deleteFilesByMemberBoards", memberNo);
    }
  }
}














