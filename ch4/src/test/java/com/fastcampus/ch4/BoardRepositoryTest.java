//package com.fastcampus.ch4;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 이거 있어야 @Order 동작
//class BoardRepositoryTest {
// @Autowired
//    private BoardRepository boardRepo;
//
//
// @Test
// public void deleteTest(){
//     boardRepo.deleteById(1L);
//     Board board = boardRepo.findById(1L).orElse(null);
//     assertTrue(board == null);
// }
//@Test
//@Order(3)
//public void updateTest(){
//  Board board = boardRepo.findById(1L).orElse(null);
//  assertTrue(board!=null);
//  board.setTitle("modified Title");
//  boardRepo.save(board);
//  Board board2 = boardRepo.findById(1L).orElse(new Board());
//  assertTrue(board.getTitle().equals(board2.getTitle()));
//    System.out.println("board = " + board);
//    System.out.println("board2 = " + board2);
//}
//
//
// @Test
// @Order(2) // application.properties에서 ddl-auto를 create로 해서 매번 테이블 지워짐, 따라서 insertTest먼저 수행해야 확인가능
// public void selectTest(){
//     Board board = boardRepo.findById(1L).orElse(null); //findById의 리턴이 optional이라
//     assertTrue(board!=null);
//
//
// }
// @Test
// @Order(1)
// public void insertTest(){
//   Board board = new Board();
//   board.setBno(1L);
//   board.setTitle("Test title");
//   board.setContent("This is Test");
//   board.setWriter("aaa");
//   board.setInDate(new Date());
//   board.setUpDate(new Date());
//   boardRepo.save(board);
// }
//}