# Итоговая контрольная работа по блоку специализации "Программист"

## Информация о проекте 

Необходимо организовать систему учета для питомника в котором живут домашние и 
вьючные животные.

## Техническое задание:

1. Используя команду cat в терминале операционной системы Linux, создать два 
файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные 
животными (заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их.
Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя
(Друзья человека).

!["команды"](Linux/Screenshot_1.png)

2. Создать директорию, переместить файл туда. 

!["команды"](Linux/Screenshot_2.png)

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого
репозитория. 

!["команды"](Linux/Screenshot_3_1.png)
!["команды"](Linux/Screenshot_3_2.png)

4. Установить и удалить deb-пакет с помощью dpkg. 

!["команды"](Linux/Screenshot_4.png)

5. Выложить историю команд в терминале ubuntu

!["команды"](Linux/Screenshot_5.png)

6. Нарисовать диаграмму,в которой есть родительский класс, домашние животные 
и вьючные животные, в составы которых в случае домашних животных войдут классы: 
собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади,верблюды и ослы).

!["команды"](Diagramm_PetsFriends.jpg)

7. В подключенном MySQL репозитории создать базу данных “Друзья человека” 
8. Создать таблицы с иерархией из диаграммы в БД 
9. Заполнить низкоуровневые таблицы именами (животных), командами которые они 
выполняют и датами рождения 
10. Удалив из таблицы верблюдов, т.к.верблюдов решили перевезти в другой 
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу. 
11. Создать новую таблицу “молодые животные” в которую попадут все животные 
старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца 
подсчитать возраст животных в новой таблице 
12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую 
принадлежность к старым таблицам. 

[Ссылка на SQL-файл](https://github.com/MoraKsu/Final_Control_Project/blob/master/friends_of_man.sql)

13. Создать классс Инкапсуляцией методов и наследованием по диаграмме.
14. Написать программу, имитирующую работу реестра домашних животных. 

- В программе должен быть реализован следующий функционал: 
- 14.1 Завести новое животное 
- 14.2 определять животное в правильный класс 
- 14.3 увидеть список команд, которое выполняет животное 
- 14.4 обучить животное новым командам 
- 14.5 Реализовать навигацию по меню 

15. Создайте класс Счетчик, у которого есть метод add(), увеличивающий 
значение внутренней int переменной на 1 при нажатие “Завести новое животное”
Сделайте так, чтобы с объектом такого типа можно было работать в блоке try-with-resources. 
Нужно бросить исключение, если работа с объектом типа счетчик была не в ресурсном 
try и/или ресурс остался открыт. Значение считать в ресурсе try,если при 
заведении животного заполнены все поля.