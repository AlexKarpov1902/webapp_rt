<%--

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>Список</title>
    <script>
        function validate_form_find()
        {
            valid = true;
            if (document.form_find.lastname.value=="" && document.form_find.firstname.value=="" && document.form_find.model.value=="" && document.form_find.city.value=="")
                {
                     alert ( "Введите данные для поиска в одно из полей.");
                     valid = false;
                }
              return valid;
        }
      </script>
      <style>
          th {border: 1px solid black;}
          td {text-align: center;}
      </style>
  </head>
  <body>


  <p> Вход произведен под логином ${sessionScope.login}
  <a href="logoutServlet">Logout</a> </p>
  <h3>Поиск по базе данных</h3>
  <h4>Введите данные в одно из полей</h4>

    <form action="/findServlet" method="post" name="form_find" onsubmit="return validate_form_find()">
      <label>Фамилия</label>
      <input type="text" name="lastname"  pattern="[а-яА-Яё]+">
      <label>Имя</label>
      <input type="text" name="firstname"  pattern="[а-яА-Яё]+">
      <label>Марка машины</label>
      <input type="text" name="model"  pattern="[a-zA-Zа-яА-Яё]+">
      <label>Город</label>
      <input type="text" name="city"  pattern="[а-яА-Яё]+">
      <label></label>
      <input type="submit" value="поиск" >
    </form>
  <table class="tabl_persons" style="width: 100%">
  	<tbody>
  		<tr>
  			<th>Фамилия</th>
  			<th>Имя</th>
  			<th>Отчество</th>
  			<th>Марка авто</th>
  			<th>Город</th>
  		</tr>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
                <td>${person.middleName}</td>
                <td>
                    <c:forEach var="auto" items="${person.autos}">
                        ${auto.model}
                    </c:forEach>
               </td>
                <td>${person.city.name}</td>
            </tr>
        </c:forEach>
  	</tbody>
  </table>
  </body>
</html>
