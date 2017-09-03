# Пример веб тестов

- junit 4
- HtmlElements
- Allure 2

# Модули:

<table>
 <thead>
  <tr>
   <th>Модуль
   <th>Описание
  </tr>
 </thead>

 <tr>
  <td><code>core</code>
  <td>основные методы для selenium
 </tr>

 <tr>
   <td><code>models</code>
   <td>общие модели обхектов
 </tr>
 
 <tr>
    <td><code>web.tests</code>
    <td>веб тесты
 </tr> 
</table>

# Как запускать

```bash
mvn [lifecycle] -pl [module] -am [must be] -Dtest=AllTestsSuite [test suite, one test or one method (-Dtest=LoginTest#logIn)] -D[params]=[value]
```

### Params
 1) -Ddriver.type=[CHROME, REMOTE_CHROME] - каким драйвером запускаем
 2) -Dthread.count=3 - количество потоков