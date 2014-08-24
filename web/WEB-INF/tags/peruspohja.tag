<%-- 
    Document   : peruspohja
    Created on : 12-Aug-2014, 15:38:31
    Author     : jsopakar
--%>

<%@tag description="Julkisen puolen peruspohja" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="pageTitle"%>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
  
  <head>
    <title>${pageTitle}</title>
    <meta charset="UTF-8">
    <!--<meta name="viewport" content="width=device-width">-->
    
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    
  </head>
  
  <body>
      <div id="content">
          
        <c:if test="${virheet != null}">
            <c:forEach var="vir" items="${virheet}">
            <div class="virhe">
                <p>${vir}</p>
            </div>

            </c:forEach>

        </c:if>

        <c:if test="${tiedotteet != null}">
            <c:forEach var="tied" items="${tiedotteet}">
            <div class="tiedote">
                <p>${tied}</p>
            </div>

            </c:forEach>

        </c:if>
          
          
          <jsp:doBody/>
          
      </div>
  </body>
</html>
