package study;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class BoardService implements BoardService1 {
    List<Board> list = new ArrayList<>();

    @Override
    public void insertBoard(Board board) {
        list.add(board);
    }

    @Override
    public void getBoards() {
        List<Board> reversedList = new ArrayList<>(list);
        Collections.reverse(reversedList);
        for (Board board : reversedList) {
            System.out.println(board);
        }
    }

    @Override
    public void getBoardById(int id) {
        for (Board board1 : list) {
            if (board1.getBno() == id) {
                System.out.println("#########");
                System.out.println("번호: " + board1.getBno());
                System.out.println("제목: " + board1.getTitle());
                System.out.println("내용: " + board1.getContent());
                System.out.println("작성자: " + board1.getAuthor());
                System.out.println("날짜: " + board1.getFormattedCreatedAt());
                System.out.println("#########");

            }
        }
    }

    @Override
    public void clearBoards() {
        list.clear();
    }

    @Override
    public void updateBoard(int id, String title, String content, String author) {
        for (Board board1 : list) {
            if (board1.getBno() == id) {
                board1.setTitle(title);
                board1.setContent(content);
                board1.setAuthor(author);
            }
        }
    }


    @Override
    public void deleteBoard(int id) {
        list.removeIf(board1 -> board1.getBno() == id);
    }
}
