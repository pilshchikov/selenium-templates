# UI tests templates

- junit 4
- HtmlElements
- Allure 2

## Structure:

<table>
 <thead>
  <tr>
   <th>Module
   <th>Description
  </tr>
 </thead>

 <tr>
  <td><code>core</code>
  <td>selenium commons 
 </tr>

 <tr>
   <td><code>models</code>
   <td>data models
 </tr>
 
 <tr>
    <td><code>web.tests</code>
    <td>web tests implementation
 </tr> 
</table>

## How to run:

```bash
mvn [lifecycle] -pl [module] -am [must be] -Dtest=AllTestsSuite [test suite, one test or one method (-Dtest=LoginTest#logIn)] -D[params]=[value]
```

### Params
 1) -Ddriver.type=[CHROME, REMOTE_CHROME] - driver type for run
 2) -Dthread.count=3 - thread count simultaneously