package board1;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BoardExample boardExample = new BoardExample();

    public static void main(String[] args) {
        list();

    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n메인 메뉴: 1. Create | 2. Read | 3. Clear | 4. Exit");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    clear();
                    break;
                case 4:
                    System.out.println("** 게시판 종료 **");
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }


    private static void create() {
        System.out.println("\n[새 게시물 입력]");
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("내용: ");
        String content = scanner.nextLine();
        System.out.print("작성자: ");
        String author = scanner.nextLine();

        System.out.println("\n보조 메뉴: 1. Ok | 2. Cancel");
        System.out.print("메뉴 선택: ");
        int submenu = scanner.nextInt();
        scanner.nextLine();

        if (submenu == 1) {
            boardExample.addBoard(title, content, author);
            reversList();
        } else if(submenu == 2){
            System.out.println("게시물 추가가 취소되었습니다.");
        }else{
            System.out.println("잘못된 선택입니다. 다시 시도하세요.");
        }
    }

    private static void read() {
        System.out.println("\n[게시물 읽기]");
        System.out.print("bno: ");
        int boardId = scanner.nextInt();
        scanner.nextLine();

        Board board = boardExample.getBoardById(boardId);
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
            int submenu = scanner.nextInt();
            scanner.nextLine();

            switch (submenu) {
                case 1:
                    update(boardId);
                    break;
                case 2:
                    boardExample.delete(boardId);
                    reversList();
                    break;
                case 3:
                    reversList();
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        } else {
            System.out.println("해당 ID의 게시물이 없습니다.");
        }
    }

    private static void update(int boardId) {
        System.out.println("\n[수정 내용 입력]");
        System.out.print("제목: ");
        String newTitle = scanner.nextLine();
        System.out.print("내용: ");
        String newContent = scanner.nextLine();
        System.out.print("작성자: ");
        String newWriter = scanner.nextLine();
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        int submenu = scanner.nextInt();
        scanner.nextLine();

        if (submenu == 1) {
            boardExample.updateBoard(boardId, newTitle, newContent, newWriter);
            reversList();
        } else {
            System.out.println("게시물 수정이 취소되었습니다.");

        }
    }

    private static void clear() {
        System.out.println("\n[게시물 전체 삭제]");
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        int submenu = scanner.nextInt();
        scanner.nextLine();

        if (submenu == 1) {
            boardExample.clearBoards();
            reversList();
        } else {
            System.out.println("게시물 전체 삭제가 취소되었습니다.");
        }
    }

    private static void reversList() {
        System.out.println("\n[게시물 목록]");
        System.out.println("---------------------------------------------");
        System.out.println("no \t writer\t date  \t\t title");
        System.out.println("---------------------------------------------");
        List<Board> boards = boardExample.getBoards();
        Collections.reverse(boards);
        for (Board board : boardExample.getBoards()) {
            System.out.println(board);
        }
        System.out.println("---------------------------------------------");
    }

    private static void list() {
        boardExample.addBoard("게시판에 오신 것을 환영합니다.", "첫 번째 게시물.", "winter");
        boardExample.addBoard("올 겨울은 많이 춥습니다.", "두 번째 게시물.", "winter");
        System.out.println("\n[게시물 목록]");
        System.out.println("---------------------------------------------");
        System.out.println("no \t writer\t date  \t\t title");
        System.out.println("---------------------------------------------");
        for (Board board : boardExample.getBoards()) {
            System.out.println(board);
        }
        System.out.println("---------------------------------------------");
        mainMenu();

    }

}
