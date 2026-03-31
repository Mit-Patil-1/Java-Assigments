<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ass-4_P02 Currency Converter</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>Currency Converter (EJB + JPA)</h2>
<form method="post" action="convert">
    <label>From:</label>
    <input type="text" name="from" required />
    <label>To:</label>
    <input type="text" name="to" required />
    <label>Amount:</label>
    <input type="number" step="0.01" name="amount" required />
    <button type="submit">Convert</button>
</form>

<%
    Object message = request.getAttribute("message");
    if (message != null) {
%>
<p><b><%= message %></b></p>
<%
    }
%>
</body>
</html>

