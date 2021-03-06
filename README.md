# Инструкция по использованию и устройству программы #


Структура:

| Класс/Интерфейс | Описание |
| ------ | ------ |
| Main(class) | запуск проекта |
| Controller<T> | интерфейс, с методам основного функционала приложения, источник парсинга может быть разного типа|
|  ControllerURL | класс, реализующий интерфейс  Controller<T>, парсит по url   |
| Parser | интерфейс, с методом *parse* необходимый для парсинга файла |
| HTMLParser | класс, реализующий интерфейс Parser. *parse* используя библиотеку jsoup|
|StringParser| класс, реализующий интерфейс Parser.  *parse* метод, который парсит строку, используя лист сепараторов|
|  WordsRepository | интерфейс для записи данных |
|WordsRepositoryImpl|класс,реализующий *WordsRepository*. Запись в БД используется **PostgreSQL**|

## Запуск программы ##
1. **Запуск программы** осуществляется в классе Main. Пользователю предлагается ввести URL сайта. 
2. После запускается метод ***countWords(String source)*** класс *ControllerURL*
3. В нем создается *Map*, который хранит в виде *ключа* - строку(слово) и *значение* - число(частота слова)
4. Происходит парсинг HTML страницы по URL при помощи класса **HTMLParser** и исключение символов, используя лист сепараторов у класса *StringParser*
5. В процессе заполнения *Map* если размер привысит maxSizeMap или кол-во операций привысит maxSizeOperation, начнется заполнение слов в БД при помощи класса **WordsRepositoryImpl**.
6. После всего заполнение, производиться еще одна проверка на оставшиеся слова в *Map*. Если осталось - отправляются на заполнение.
7. В конце работы программы вытаскиваются от наибольшей частоты до наименьшей слова из БД.
## Дополнительно ##
1. Было произведена запись в БД.(используется **PostgreSQL**)
2. В классах прописаны логи уровня(INFO,WARN,ERROR) и запись их в файл logs.log
3. Произведены успешно тесты(исполуется JUnit4) на классы HTMLParser и StringParser.
4. Осуществлена возможность расширяемости проекта и его многоуровневая архитектура(dao,services,controller,Main)