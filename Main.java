import java.util.*;
public class Main
{
    static Scanner keyboard = new Scanner(System.in);
    public static int selectGameMode = 0;
    public static void main(String[] args)
    {
        //int selectGameMode = 0;
        int x = 0; int y = 0;
        int compX = 0; int compY = 0;
        int x2 = 0; int y2 = 0;
        String[][] board = new String[3][3];
        fillArrayDot(board);
        System.out.println("WARNING! Top lefts x and y = 1");
        System.out.println("Select your game mode:\n1-Player vs. Computer\n2-Player vs. Player");
        selectGameMode = keyboard.nextInt();
        if(selectGameMode==1)
        {
            while(isGameFinished(board) == false)
            {
                System.out.println("Enter your x(row) and y(column) to fill:");
                x = keyboard.nextInt();
                y = keyboard.nextInt();
                if (isInputEmpty(board, x, y))
                {
                    fillArrayWithX(board, x, y);
                    if (isGameFinished(board) == true)
                    {
                        arrayPrinter(board);
                        System.out.println("Player won!");
                        System.exit(0);
                    }
                    else
                    {
                        compX = (int) ((Math.random() * 3) + 1);
                        compY = (int) ((Math.random() * 3) + 1);
                        while (isInputEmpty(board, compX, compY) == false)
                        {
                            compX = (int) ((Math.random() * 3) + 1);
                            compY = (int) ((Math.random() * 3) + 1);
                        }
                        fillArrayWithO(board, compX, compY);
                        arrayPrinter(board);
                    }
                }
            }

        }
        else if(selectGameMode==2)
        {
            while(isGameFinished(board) == false)
            {
                System.out.println("For first player, enter your x(row) and y(column) to fill:");
                x = keyboard.nextInt();
                y = keyboard.nextInt();
                if (isInputEmpty(board, x, y))
                {
                    fillArrayWithX(board, x, y);
                    arrayPrinter(board);
                    if (isGameFinished(board) == true)
                    {
                        System.out.println("Player won!");
                        System.exit(0);
                    }
                    else if(isGameFinishedAuto(board) == true)
                    {
                        System.out.println("Game ended, no winner!");
                        System.exit(0);
                    }
                    else
                    {
                        System.out.println("For second player, enter your x(row) and y(column) to fill:");
                        x2 = keyboard.nextInt();
                        y2 = keyboard.nextInt();
                        while(!(isInputEmpty(board, x2, y2)))
                        {
                            System.out.println("Slot is not empty, please try again: ");
                            x2 = keyboard.nextInt();
                            y2 = keyboard.nextInt();
                        }
                        if(isInputEmpty(board, x2, y2))
                        {
                            fillArrayWithO(board, x2, y2);
                            arrayPrinter(board);
                        }
                    }
                }
            }
        }
        else
        {
            System.out.println("Wrong input! Program shutting down...");
            System.exit(0);
        }
    }
    public static boolean isGameFinishedAuto(String[][] arr)
    {
        boolean b = false;
        int moveCounter = 0;
        for(int i=0; i<arr.length; i++)//kazanan yok, her yer dolu
        {
            for(int j=0; j<arr.length; j++)
            {
                if(arr[i][j].equals("O") || arr[i][j].equals("X"))
                    moveCounter++;
            }
        }
        if(moveCounter == 9 && b == false)
            b = true;
        return b;
    }
    public static boolean isGameFinished(String[][] arr)
    {
        boolean b = false;
        for(int i=0; i<arr.length; i++) // satır satır bakıyor
        {
            if(arr[i][0].equals("X") && arr[i][1].equals("X") && arr[i][2].equals("X"))
            {
                System.out.println("Player won!");
                b = true;
                System.exit(0);
            }
            else if(arr[i][0].equals("O") && arr[i][1].equals("O") && arr[i][2].equals("O"))
            {
                if(selectGameMode == 1)
                {
                    System.out.println("Computer won!");
                    b = true;
                    System.exit(0);
                }
                else
                {
                    System.out.println("Player 2 won!");
                    b = true;
                    System.exit(0);
                }

            }
        }
        for(int i=0; i<arr.length; i++) // sütun sütun bakıyor
        {
            if(arr[0][i].equals("X") && arr[1][i].equals("X") && arr[2][i].equals("X"))
            {
                System.out.println("Player won");
                b = true;
                System.exit(0);
            }
            else if(arr[0][i].equals("O") && arr[1][i].equals("O") && arr[2][i].equals("O"))
            {
                if(selectGameMode == 1)
                {
                    System.out.println("Computer won!");
                    b = true;
                    System.exit(0);
                }
                else
                {
                    System.out.println("Player 2 won!");
                    b = true;
                    System.exit(0);
                }
            }
        }
        if(arr[0][0].equals("X") && arr[1][1].equals("X") && arr[2][2].equals("X"))//sol üstten sağa alta çapraz
        {
            System.out.println("Player won");
            b = true;
            System.exit(0);
        }
        else if(arr[0][0].equals("O") && arr[1][1].equals("O") && arr[2][2].equals("O"))
        {
            if(selectGameMode == 1)
            {
                System.out.println("Computer won!");
                b = true;
                System.exit(0);
            }
            else
            {
                System.out.println("Player 2 won!");
                b = true;
                System.exit(0);
            }
        }
        if(arr[0][2].equals("X") && arr[1][1].equals("X") && arr[2][0].equals("X"))//sağ üstten sol alta çapraz
        {
            System.out.println("Player won");
            b = true;
            System.exit(0);
        }
        else if(arr[0][2].equals("O") && arr[1][1].equals("O") && arr[2][0].equals("O"))
        {
            if(selectGameMode == 1)
            {
                System.out.println("Computer won!");
                b = true;
                System.exit(0);
            }
            else
            {
                System.out.println("Player 2 won!");
                b = true;
                System.exit(0);
            }
        }
        return b;
    }
    public static void fillArrayWithO(String[][] arr, int x, int y)
    {
        arr[x-1][y-1] = "O";
    }
    public static void fillArrayWithX(String[][] arr, int x, int y)
    {
        arr[x-1][y-1] = "X";
    }
    public static boolean isInputEmpty(String[][] arr, int x, int y)
    {
        if(arr[x-1][y-1].equals(" "))
            return true;
        else
            return false;
    }
    public static String[][] fillArrayDot(String[][] arr)
    {
        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[0].length; j++)
            {
                arr[i][j] = " ";
            }
        }
        return arr;
    }
    public static void arrayPrinter(String[][] arr)
    {
        System.out.println(arr[0][0] +  " | " + arr[0][1] +  " | " + arr[0][2]);
        System.out.println("——+———+——");
        System.out.println(arr[1][0] +  " | " + arr[1][1] +  " | " + arr[1][2]);
        System.out.println("——+———+——");
        System.out.println(arr[2][0] +  " | " + arr[2][1] +  " | " + arr[2][2]);
    }
}