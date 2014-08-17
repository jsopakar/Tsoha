<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:peruspohja pageTitle="Pizzapalvelu - menu">

<h1>Pizzapalvelu - menu </h1>

<c:if test="${virhe != null}">
  <div class="virhe">
      <p>${virhe}</p>
  </div>
</c:if>

<c:if test="${temp != null}">
  <div class="virhe">
      <p>${temp}</p>
  </div>
</c:if>
    
<table>
    <caption>Tuotekategoria 1</caption>
    <tr>
        <th>
            Tuotenumero
        </th>
        <th>
            Nimi
        </th>
        <th>
            Kuvaus
        </th>
        <th>
            Hinta
        </th>
    </tr>

<c:forEach var="tuote" items="${tuotteet}">
    <tr>
        <td>
            ${tuote.tuotenumero}
        </td>
        <td>
            ${tuote.nimi}
        </td>
        <td>
            ${tuote.kuvaus}
        </td>
        <td>
            ${tuote.hinta}
        </td>
    </tr>
        
</c:forEach>
    
</table>

</t:peruspohja>
