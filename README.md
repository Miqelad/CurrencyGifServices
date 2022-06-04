<ul>Описание
<li>Создать сервис, который обращается к сервису курсов валют, и отображает gif:</li>
<li> если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich</li>
<li> если ниже - отсюда https://giphy.com/search/broke</li></ul>
</ul>
<ul>Ссылки
<li> REST API курсов валют - https://docs.openexchangerates.org/</li>
<li> REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide</li>
<li> Сборка и запуск Docker контейнера с этим сервисом: чуть ниже</li>
</ul>

<ul>Вводная часть:
  <li>Сервис сравнивает отношение к валюте USD за сегодня и за вчера.</li>
  <li> если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich</li>
  <li> если ниже - отсюда https://giphy.com/search/broke
  <li> если равны - то https://media4.giphy.com/media/PjaTjG8ct5cNOtjjZg/giphy.gif?cid=ecf05e47xjgdvce0o8zn1h25y58guxkwx8cvnhsjvzu9smzp&rid=giphy.gif&ct=g</li>
  </ul>

<ul>Настройка:
<li>resources/application.yml - В данном файле необходимо настроить ключи из внешних api, по стандарту стоят мои ключи, но при желании можно перестроить:</li>
  <li>feign.exchanger.key - https://developers.giphy.com/docs/api#quick-start-guide</li>
  <li>feign.giphy.key - https://docs.openexchangerates.org/</li>
  <li>feign.exchanger.currency - валюта по которой сравниваем с USD в формате 3 буквы</li>
  </ul>
<ul>Основной endpoint:
  <li>com/paata/mikeladze/currency/controller/ControllerCurrency.java</li>
  <li>http://localhost:8080 - выдаст гиф, в зависимости от изменения валюты</li>
  <li>http://localhost:8080/?currency=(валюта по которой сравниваем с USD в формате 3 буквы) - сработает с любой валютой, игнорируя feign.exchanger.currency</li>
  </ul>
<ul>Дополнительные endpoint:
  <li>http://localhost:8080/rich - рандомная гиф из rich</li>
  <li>http://localhost:8080/broke - рандомная гиф из broke</li>
  <li>http://localhost:8080/get/now/ - Значение валюты за сегодняшний день день</li>
  <li>http://localhost:8080/get/yesterday/ - Значение валюты за вчерашний день</li>
  </ul>


<ul>Запуск через докер:
<li>Dockerfile:Dockerfile in root</li>
<li>DockerHub: https://hub.docker.com/layers/currensy-api/granddfat/currensy-api/v1.0/images/sha256-4acec791a6b3af8d2ef44f2e743a9e0faa632d5628f5616b510d5b11bce268b4?context=repo</li>
<li>DockerCommand: docker run -d --name apis -p 8080:8080 granddfat/currency</li>
</ul>
