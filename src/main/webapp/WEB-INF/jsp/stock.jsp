<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }

    </style>
</head>

<body>
<h2>Spring's form tags example</h2>

<form:form method="POST" commandName="stockForm">
    <table>
        <tr>
            <td>Symbol :</td>
            <td><form:input path="symbol"/></td>
            <td><form:errors path="symbol" cssClass="error"/></td>
        </tr>
        <tr>
            <input type="submit" />
        </tr>
    </table>
</form:form>
</body>
</html>