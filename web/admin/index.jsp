<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:peruspohja pageTitle="Pizzapalvelu - ylläpito">

<h1>Ylläpito</h1>

<c:if test="${kirjautuminen != null}">
  <div class="virhe">
      <p>${kirjautuminen}</p>
  </div>
</c:if>


<p>Tähän tulee admin-sivun päälinkit.</p>



    
</t:peruspohja>