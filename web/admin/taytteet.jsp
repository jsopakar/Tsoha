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

<c:if test="${poistovahvistus != null}">
    <p class="vahvistus">
        Vahvista täytteen poisto, ID ${poistovahvistus}
        ${poistovahvistus}<br>
        [ <a href="taytteet?poista=${poistovahvistus}&vahvistus=1">Vahvista</a> ]
    </p>
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
            <td> [ <a href="taytteet?edit=${tayte.id}">Muokkaa</a> ]&nbsp;&nbsp[ <a href="taytteet?poista=${tayte.id}">Poista</a> ]</td>
        </tr>
        
    </c:forEach>
    
</table>


    <h3>Täytteen 
        
        <c:choose>
            <c:when test="${muokkaustila == null}">
                lisäys
            </c:when>
            <c:otherwise>
                muokkaus
            </c:otherwise>
        </c:choose>
    </h3>
<p>
<form method="post" action="taytteet">
    <c:if test="${muokkaustila != null}">ID: ${muokattava.id} <br></c:if>
    <label for="nimi">Nimi: </label>
    <input type="text" name="nimi" value="${muokattava.nimi}"><br>
    <label for="kuvaus">Kuvaus: </label>
    <input type="text" name="kuvaus" value="${muokattava.kuvaus}"><br>
    <label for="hinta">Hinta: </label>
    <input type="text" name="hinta" value="${muokattava.hinta}" size="5"><br>
    <label for="lisatayte">Lisätäytteenä: </label>
    <input type="checkbox" name="lisatayte"<c:if test="${muokattava.onkoLisatayte}"> value=checked</c:if>>
    (käsittely ei toimi vielä, ei vaikutusta tällä...)
    <br>
    
    <c:if test="${muokkaustila != null}">
    <input type="hidden" name="id" value="${muokattava.id}">
    <input type="hidden" name="nimiVanha" value="${muokattava.nimi}">
    <input type="hidden" name="kuvausVanha" value="${muokattava.kuvaus}">
    <input type="hidden" name="hintaVanha" value="${muokattava.hinta}">
    <input type="hidden" name="lisatayteVanha" value="${muokattava.onkoLisatayte}">
    <input type="hidden" name="muokkaus" value="true">
    <br>MUOKKAUSTILA<br>
    </c:if>

    <c:choose>
        <c:when test="${muokkaustila == null}">
        <input type="submit" value=" Tallenna " name="tallennaLisays">
            
        </c:when>
        <c:otherwise>
        <input type="submit" value=" Tallenna " name="tallennaMuokkaus">
            
        </c:otherwise>
            
    </c:choose>
        
    
</form>
</p>
    
</t:peruspohja>
