<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:peruspohja pageTitle="Pizzapalvelu - ylläpidon kirjautuminen">



<h1>Ylläpidon kirjautuminen</h1>

<c:if test="${virheviesti != null}">
  <div class="virhe">
      <p>${virheviesti}</p>
  </div>
</c:if>

<p>
  Syötä tunnus ja salasana:
</p>

<form action="./" method="post">

  <label for="tunnus">Tunnus</label>

  <input type="text" name="tunnus" value="${tunnus}">

  <br>

  <label for="salasana">Salasana</label>

  <input type="text" name="salasana">

  <br>
  <input type="submit" value=" Kirjaudu ">

</form>




</t:peruspohja>
