//package com.fastcampus.ch4;
//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.Tuple;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.Date;
//import java.util.List;
//
//import static com.fastcampus.ch4.QBoard.board;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 이거 있어야 @Order 동작
//class BoardRepositoryTest4 {
//    @Autowired
//    public EntityManager em;
//    @Autowired
//    private BoardRepository boardRepo;
//
//    @BeforeEach
//    public void testData(){
//        for(int i=1; i<=100; i++){
//            Board board = new Board();
//            board.setBno((long)i);
//            board.setTitle("title" + i);
//            board.setContent("content" + i);
//            board.setWriter("writer" + (i%5));
//            board.setViewCnt((long)(Math.random()*100));
//            board.setInDate(new Date());
//            board.setUpDate(new Date());
//            boardRepo.save(board);
//        }
//    }
//
//    @Test
//    @DisplayName("querydsl로 쿼리 작성 테스트3 - 동적 쿼리작성")
//    public void querydslTest3() {
//        String searchBy = "TC"; //title 과 작성 내용에서 검색
//        String keyword = "1";
//        keyword = "%" + keyword + "%";
//        BooleanBuilder builder = new BooleanBuilder();
//
//        if(searchBy.equalsIgnoreCase("T"))
//            builder.and(board.title.like(keyword));
//        else if(searchBy.equalsIgnoreCase("C"))
//            builder.and(board.content.like(keyword));
//        else if(searchBy.equalsIgnoreCase("TC"))
//            builder.and(board.title.like(keyword).or(board.content.like(keyword)));
//
//        JPAQueryFactory qf = new JPAQueryFactory(em);
//        JPAQuery query = qf.selectFrom(board)
//                .where(builder)
//                .orderBy(board.upDate.desc());
//
//        List<Board> list = query.fetch();
//        list.forEach(System.out::println);
//
//    }
//    @Test
//    @DisplayName("querydsl로 쿼리 작성 테스트2 - 복잡한 쿼리작성")
//    public void querydslTest2() {
//        JPAQueryFactory qf = new JPAQueryFactory(em);
//        JPAQuery<Tuple> query = qf.select(board.writer, board.viewCnt.sum()).from(board)
//                .where(board.title.notLike("title1%"))
//                .where(board.writer.eq("writer1"))
//                .where(board.content.contains("content"))
//                .where(board.content.isNotNull())
//                .groupBy(board.writer)
//                .having(board.viewCnt.sum().gt(100)) //조회수가 1000이 넘어!!
//                .orderBy(board.writer.asc())
//                .orderBy(board.viewCnt.sum().desc());
//
//        List<Tuple> list = query.fetch();
//        list.forEach(System.out::println);
//    }
//    @Test
//    @DisplayName("querydsl로 쿼리 작성 테스트1 - 간단한 쿼리작성")
//    public void querydslTest1() {
//        //QBoard board = QBoard.board;
//        //1. JPAQueryFactory를 생성
//        JPAQueryFactory qf = new JPAQueryFactory(em);
//        //2.쿼리 작성
//        JPAQuery<Board> query = qf.selectFrom(board)
//                .where(board.title.eq("title1"));
//        //3.쿼리 실행
//        List<Board> list = query.fetch();
//        list.forEach(System.out::println);
//
//    }
//
//
//}