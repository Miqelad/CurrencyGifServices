Описание
Создать сервис, который обращается к сервису курсов валют, и отображает gif:
• если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
• если ниже - отсюда https://giphy.com/search/broke
Ссылки
• REST API курсов валют - https://docs.openexchangerates.org/
• REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
• Сборка и запуск Docker контейнера с этим сервисом:

Вводная часть:
  Сервис сравнивает отношение к валюте USD за сегодня и за вчера.
  • если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
  • если ниже - отсюда https://giphy.com/search/broke
  • если равны - то https://media4.giphy.com/media/PjaTjG8ct5cNOtjjZg/giphy.gif?cid=ecf05e47xjgdvce0o8zn1h25y58guxkwx8cvnhsjvzu9smzp&rid=giphy.gif&ct=g

Настройка:
resources/application.yml - В данном файле необходимо настроить ключи из внешних api, по стандарту стоят мои ключи, но при желании можно перестроить:
  feign.exchanger.key - https://developers.giphy.com/docs/api#quick-start-guide
  feign.giphy.key - https://docs.openexchangerates.org/
  feign.exchanger.currency - валюта по которой сравниваем с USD в формате 3 буквы
Основной endpoint:
  com/paata/mikeladze/currency/controller/ControllerCurrency.java
  http://localhost:8080 - выдаст гиф, в зависимости от изменения валюты
  http://localhost:8080/?currency=(валюта по которой сравниваем с USD в формате 3 буквы) - сработает с любой валютой, игнорируя feign.exchanger.currency
Дополнительные endpoint:
  http://localhost:8080/rich - рандомная гиф из rich
  http://localhost:8080/broke - рандомная гиф из broke
  http://localhost:8080/get/now/ - Значение валюты за сегодняшний день день
  http://localhost:8080/get/yesterday/ - Значение валюты за вчерашний день


Запуск через докер:
Dockerfile:Dockerfile in root
DockerHub: https://hub.docker.com/layers/currensy-api/granddfat/currensy-api/v1.0/images/sha256-4acec791a6b3af8d2ef44f2e743a9e0faa632d5628f5616b510d5b11bce268b4?context=repo
DockerCommand: docker run -d --name apis -p 8080:8080 granddfat/currency
