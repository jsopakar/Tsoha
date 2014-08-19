<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:peruspohja pageTitle="Pizzapalvelu - ylläpito">
    
<h1>Täytteiden ylläpito</h1>

<c:if test="${virhe != null}">
    <div class="virhe">
        <p>${virhe}</p>
    </div>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>Nimi</th>
        <th>Kuvaus</th>
        <th>Hinta</th>
        <th>Lisätäytteenä</th>
        <th></th>
    </tr>
    
    <c:forEach var="tayte" items="${taytteet}">
        <tr>
            <td>${tayte.id}</td>
            <td><c:out value="${tayte.nimi}"/></td>
            <td><c:out value="${tayte.kuvaus}"/></td>
            <td>${tayte.hinta}</td>
            <td>${tayte.onkoLisatayte}</td>
            <td> [ <a href="./?edit=${tayte.id}">Muokkaa</a> ]</td>
        </tr>
        
    </c:forEach>
    
</table>

<c:if test="${muokattava != null}">

    <h3>Täytteen muokkaus:</h3>
<p>
<form method="post" action="taytteet.jsp">
    ID: ${muokattava.id} <br/>
    <label for="nimi">Nimi: </label>
    <input type="text" name="nimi" value="${muokattava.nimi}"><br>
    <label for="kuvaus">Kuvaus: </label>
    <input type="text" name="kuvaus" value="${muokattava.kuvaus}"><br>
    <label for="hinta">Hinta: </label>
    <input type="text" name="hinta" value="${muokattava.hinta}" size="5"><br>
    <label for="lisatayte">Lisätäytteenä: </label>
    <input type="checkbox" name="lisatayte"<c:if test="${muokattava.onkoLisatayte}"> checked</c:if>>
    <br>
    <input type="submit" value=" Tallenna ">
</form>
</p>
</c:if>
    
</t:peruspohja>
