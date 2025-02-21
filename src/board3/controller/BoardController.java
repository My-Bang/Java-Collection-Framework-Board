package board3.controller;

import board3.dto.BoardDTO;
import board3.service.BoardService;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private BoardService boardService;
    private Scanner scanner;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
        this.scanner = new Scanner(System.in);
    }

    public void createBoard() {
        while (true) {
            try {
                System.out.println("\n[새 게시물 입력]");
                System.out.print("제목: ");
                String title = scanner.nextLine();
                if (title.isEmpty()) {
                    throw new IllegalArgumentException("제목을 입력하세요.");
                }

                System.out.print("내용: ");
                String content = scanner.nextLine();
                if (content.isEmpty()) {
                    throw new IllegalArgumentException("내용을 입력하세요.");
                }

                System.out.print("작성자: ");
                String writer = scanner.nextLine();
                if (writer.isEmpty()) {
                    throw new IllegalArgumentException("작성자를 입력하세요.");
                }
                System.out.println("\n보조 메뉴: 1. Ok | 2. Cancel");
                System.out.print("메뉴 선택: ");
                int submenu = Integer.parseInt(scanner.nextLine());

                if (submenu == 1) {
                    int nextId = boardService.getAllBoards().size() + 1;
                    BoardDTO newBoard = new BoardDTO(nextId, title, content, writer);
                    boardService.insertBoard(newBoard);
                    listBoards();
                    break;
                } else {
                    System.out.println("게시물 추가가 취소되었습니다.");
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void readBoard() {
        while (true) {
            try {
                System.out.println("\n[게시물 읽기]");
                System.out.print("bno: ");
                int boardId = Integer.parseInt(scanner.nextLine());

                BoardDTO board = boardService.getBoardById(boardId);
                if (board != null) {
                    System.out.println("\n###########");
                    System.out.println("번호: " + board.getId());
                    System.out.println("제목: " + board.getTitle());
                    System.out.println("내용: " + board.getContent());
                    System.out.println("작성자: " + board.getWriter());
                    System.out.println("날짜: " + board.getFormattedCreatedAt());
                    System.out.println("###########");

                    System.out.println("\n보조 메뉴: 1. Update | 2. Delete | 3. List");
                    System.out.print("메뉴 선택: ");
                    int submenu = Integer.parseInt(scanner.nextLine());

                    switch (submenu) {
                        case 1:
                            updateBoard(boardId);
                            break;
                        case 2:
                            deleteBoard(boardId);
                            listBoards();
                            break;
                        case 3:
                            listBoards();
                            break;
                        default:
                            System.out.println("잘못된 선택입니다.");
                    }
                    break;
                } else {
                    System.out.println("해당 ID의 게시물이 없습니다.");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 ID입니다. 숫자만 입력하세요.");
            }
        }
    }

    public void updateBoard(int boardId) {
        while (true) {
            try {
                System.out.println("\n[수정 내용 입력]");
                System.out.print("제목: ");
                String newTitle = scanner.nextLine();
                if (newTitle.isEmpty()) {
                    throw new IllegalArgumentException("제목을 입력하세요.");
                }

                System.out.print("내용: ");
                String newContent = scanner.nextLine();
                if (newContent.isEmpty()) {
                    throw new IllegalArgumentException("내용을 입력하세요.");
                }

                System.out.print("작성자: ");
                String newWriter = scanner.nextLine();
                if (newWriter.isEmpty()) {
                    throw new IllegalArgumentException("작성자를 입력하세요.");
                }

                System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
                System.out.print("메뉴 선택: ");
                int submenu = Integer.parseInt(scanner.nextLine());

                if (submenu == 1) {
                    BoardDTO updatedBoard = new BoardDTO(boardId, newTitle, newContent, newWriter);
                    boardService.updateBoard(updatedBoard);
                    listBoards();
                    break;
                } else {
                    System.out.println("게시물 수정이 취소되었습니다.");
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteBoard(int boardId) {
        BoardDTO boardToDelete = boardService.getBoardById(boardId);
        if (boardToDelete != null) {
            boardService.deleteBoard(boardToDelete);
            System.out.println("게시물이 삭제되었습니다.");
        } else {
            System.out.println("해당 ID의 게시물이 없습니다.");
        }
    }


    public void listBoards() {
        List<BoardDTO> boards = boardService.getAllBoards();
        System.out.println("\n[게시물 목록]");
        System.out.println("---------------------------------------------");
        System.out.println("no \t writer\t date  \t\t title");
        System.out.println("---------------------------------------------");

        for (BoardDTO board : boards) {
            System.out.println(board.getId() + "\t" + board.getWriter() + "\t" + board.getFormattedCreatedAt() + "\t" + board.getTitle());
        }
        System.out.println("---------------------------------------------");

        mainMenu();

    }

    public void mainMenu() {
        while (true) {
            try {
                System.out.println("\n메인 메뉴: 1. Create | 2. Read | 3. Clear | 4. Exit");
                System.out.print("메뉴 선택: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        createBoard();
                        break;
                    case 2:
                        readBoard();
                        break;
                    case 3:
                        boardService.clearBoard();
                        listBoards();
                        break;
                    case 4:
                        System.out.println("** 게시판 종료 **");
                        System.exit(0);
                    default:
                        System.out.println("잘못된 선택입니다. 1 ~ 4를 선택해주세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
            }
        }
    }
}
