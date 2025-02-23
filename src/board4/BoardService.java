package board4;

public interface BoardService {
    void insertBoard(Board board);

    void getBoards();

    void getBoardById(int id);

    void clearBoards();

    void updateBoard(int id, String title, String content, String author);

    void deleteBoard(int id);
}
