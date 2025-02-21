package board1;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardExample {
    private List<Board> boards;
    private int nextId;

    public BoardExample() {
        this.boards = new ArrayList<>();
        this.nextId = 1;
    }

    public void addBoard(String title, String content, String writer) {
        Board newBoard = new Board(nextId++, title, content, writer);
        boards.add(newBoard);
    }

    public void delete(int boardId) {
        boards.removeIf(board -> board.getId() == boardId);
    }

    public void updateBoard(int boardId, String newTitle, String newContent, String newWriter) {
        for (Board board : boards) {
            if (board.getId() == boardId) {
                board.setTitle(newTitle);
                board.setContent(newContent);
                board.setWriter(newWriter);
                break;
            }
        }
    }

    public Board getBoardById(int boardId) {
        for (Board board : boards) {
            if (board.getId() == boardId) {
                return board;
            }
        }
        return null;
    }

    public void clearBoards() {
        boards.clear();
        nextId = 1;
    }
}