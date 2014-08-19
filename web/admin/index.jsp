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
</c:if>
              

<p>Tähän tulee admin-sivun päälinkit.</p>

<h2>Omistajan toiminnot</h2>

<ul>
    <li><a href="taytteet.jsp">Täytteiden hallinta</a></li>
    <li>Tuotteiden hallinta</li>
</ul>


<p><a href="./?logout=1">Kirjaudu ulos</a></p>
    
</t:peruspohja>