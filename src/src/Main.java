package src;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       System.out.println("Введите максимальное количество повторений слова");
       int choice = Integer.parseInt(input.nextLine());
       System.out.println("Введите строку");
       String text = input.nextLine();
       ClassOne ex1 = new ClassOne();
       ClassOne ex2 = new ClassOne();
       ex1.getInstance().setN(choice);
       String result = ex2.getInstance().deleteNCountWords(text);
       System.out.println(result);
    }
}
