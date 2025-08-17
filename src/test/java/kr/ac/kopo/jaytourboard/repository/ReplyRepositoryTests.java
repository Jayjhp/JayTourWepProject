package kr.ac.kopo.jaytourboard.repository;

import kr.ac.kopo.jaytourboard.entity.Board;
import kr.ac.kopo.jaytourboard.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;
import java.util.List;

@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testListByBoard(){
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder()
                .bno(100L)
                .build());
        replyList.forEach(reply -> System.out.println(reply));
    }

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1, 300).forEach(i ->{
//            long bno = (long) (Math.random() * 100 + 1);//1~100 임의의 long 형의 정수 값
            long bno = (long) (Math.random() * 22 + 26);//26 ~ 47 임의의 long 형의 정수 값

            Board board = Board.builder()
                    .bno(bno)
                    .build();

            Reply reply = Reply.builder()
                    .text("Reply " + i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }
}
