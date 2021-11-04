# Конвертер в текстовую графику

Вас пригласили поучаствовать в разработке приложения, умеющего скачивать картинки по URL и конвертировать изображения в текстовую графику (т. е. текст из разных символов, которые в совокупности выглядят как изображение). Вот пример его работы (картинка на нём — это текст из мелких символов):

![](pics/preview.png)

## Скачиваем проект
Часть приложения уже написана и вашей задачей будет доработка его основной логической части в соответствии с требованиями. Поэтому для начала работы нам нужно будет скачать заготовку проекта. Для этого откройте идею, выберите в меню `File -> New -> Project from Version Control`. В открывшемся окне в поле URL введите `https://github.com/netology-code/java-diplom` как показано на картинке и нажмите Clone:

![](pics/idea-import.jpg)

После того как идея скачает проект, надо убедиться, что для него выбрана версия джавы. Откройте `File -> Project structure` и убедитесь, что это так. Версия джавые должна быть не меньше 11й.

![](pics/idea-import-java.png)

После чего откройте класс `Main` и запустите метод `main`. Запуск должен завершиться следующей ошибкой:
```
Exception in thread "main" java.lang.IllegalArgumentException: Серверу нужно передать в конструктор объект-конвертер, а было передано null.
	at ru.netology.graphics.server.GServer.<init>(GServer.java:24)
	at ru.netology.graphics.Main.main(Main.java:13)
```

Если вы видите эту ошибку, то значит проект настроен верно. Если нет - напишите своему руководителю по курсовой, он подскажет с настройкой.

## Структура проекта

## Ваша роль

## Требования к конвертеру

## Тестирование

## Отладка и решение проблем

## Как сдавать?
