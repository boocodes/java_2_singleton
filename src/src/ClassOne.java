package src;

import java.util.*;
import java.util.stream.Stream;

public class ClassOne{

    // синглтон функционал
    private static ClassOne classOne;
    public static synchronized ClassOne getInstance(){
        if(classOne == null){
            classOne = new ClassOne();
        }
        return classOne;
    }

    private int N;  // текущее количество слов

    public void setN(int N){ // метод для установки количества слов
        this.N = N; // установить количество
    }
    public int getN(int N){ // метод для получения текущего количества слов
        return N; // вернуть количество
    }

    private static int substringCount(String s, String pattern) { // подсчет количества повторений слова в тексте
        int result = 0; // начальное количество повторений
        for (int i = 0; i < s.length(); i++) { // цикл по длине строки
            if (s.substring(i, i + pattern.length()).equalsIgnoreCase(pattern)) { // если при обрезании строки встречается искомое слово без учета регистра
                result++; // увеличиваем счетчик количества повторений
                i += pattern.length(); // увеличиваем счетчик цикла на длину слова
            }
        }
        return result; // возвращаем количество повторений
    }
    private long howManyTimesWordAtTheText(String text, String word) {  // подсчет количества повторений слова в тексте
        long count =  Arrays
                .stream(text.split("\\s+"))
                .filter(word::equals)
                .count();
        return count;
    }
    private ArrayList<String> deleteNonUniqueElements(ArrayList<String> wordsList){
        Set<String> buffSet = new HashSet<String>(wordsList); // создание временного сет и передача ему списка слов для удаления повторений
        return new ArrayList<String>(buffSet); // возвращение списка слов без повторяющихся элементов
    }
    private static String deleteWordsFromText(String text, String word){ // удалить слово/слова из текста
        return text.replaceAll(word, ""); // вернуть строку с удаленным/удаленными словами
    }
    private static String sliceText(String s, int posStart, int posEnd) { // убрать что то из текста по позиции
        return s.substring(posStart, posEnd);
    }

    private static ArrayList<String> fillWordsArrayFromTextWords(String text){ // метод для создания списка всех слов из текста
        ArrayList<String> wordsList = new ArrayList(); // создание списка слов
        int startPosition = 0; // инициализация переменной по первому положения для обрезания строки
        for(int i = 0; i < text.length(); ++i) { // цикл по тексту
            if (text.charAt(i) == ' ' || i == text.length() - 1) { // если символ на позиции является пробелом или позииция является размером текста
                String slicedWord = sliceText(text, startPosition, i + 1); // создание строки и присваение ей образаной строки
                startPosition = i + 1; // увеличение начальной позиции по обрезке текста
                wordsList.add(slicedWord.trim()); // добавление строки (слова) в список слов
            }
        }
        return wordsList; // возвращение списка слов
    }
    public String deleteNCountWords(String string){ // метод для удаления слов, которые повторяются N раз.
        String buffString = string; // создание строки которая будет изменяться
        ArrayList<String> wordsList = new ArrayList<String>(); // создание списка всех слов
        wordsList = fillWordsArrayFromTextWords(buffString); // присваение списку значение из функции по заполнению списка слов из всех слов текста
        wordsList = deleteNonUniqueElements(wordsList);
        if (N == 0) return buffString; // если не задано максимальное количество повторения слова, то возвращаем нередаткируемое слово
        for(int i = 0; i < wordsList.size(); i++){
            int wordReapeatsCount = (int) howManyTimesWordAtTheText(buffString, wordsList.get(i));
            if(wordReapeatsCount >= this.N){
                buffString = deleteWordsFromText(buffString, wordsList.get(i));

            }
        }
        return buffString; // вывести измененную строку
    }
}
