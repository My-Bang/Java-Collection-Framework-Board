package board3;

import board3.controller.BoardController;
import board3.service.BoardService;
import board3.service.BoardServiceDAO;

public class Main {
    public static void main(String[] args) {
        BoardService boardService = new BoardServiceDAO();
        BoardController boardController = new BoardController(boardService);
        boardController.listBoards();
    }
}
