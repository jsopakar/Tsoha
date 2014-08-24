<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:peruspohja pageTitle="Pizzapalvelu - ylläpito">

<h1>Ylläpito</h1>

<c:if test="${virheviesti != null}">
  <div class="virhe">
      <p>${virheviesti}</p>
  </div>
</c:if>


<c:if test="${kirjautunut != null}">
<div>
    <p>Olet kirjautunut tunnuksella ${kirjautunut}</p>
</div>

<p>Tähän tulee admin-sivun päälinkit.</p>

<h2>Omistajan toiminnot</h2>

<ul>
    <li><a href="taytteet">Täytteiden hallinta</a></li>
    <li>Tuotteiden hallinta</li>
</ul>

<p><a href="./?logout=1">Kirjaudu ulos</a></p>

</c:if>



<c:if test="${kirjautunut == null}">
    
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
    
</c:if>



</t:peruspohja>