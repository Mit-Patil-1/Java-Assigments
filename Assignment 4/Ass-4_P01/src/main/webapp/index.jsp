<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ass-4_P01 Book Queue</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>Book Lookup using JMS Queue + EJB + JPA</h2>
<form method="post" action="book-request">
    <label>Book ID:</label>
    <input type="number" name="bookId" required />
    <button type="submit">Send Request</button>
</form>

<%
    String requestId = request.getParameter("requestId");
    if (requestId != null) {
%>
<p>Request ID: <b><%= requestId %></b></p>
<button onclick="loadXml()">Fetch XML Response</button>
<pre id="raw"></pre>
<div id="parsed"></div>
<script>
async function loadXml() {
    const res = await fetch('book-response?requestId=<%= requestId %>');
    const text = await res.text();
    document.getElementById('raw').textContent = text;

    const xml = new DOMParser().parseFromString(text, 'application/xml');
    const status = xml.getElementsByTagName('status')[0]?.textContent;
    if (status !== 'OK') {
        document.getElementById('parsed').textContent = 'Status: ' + status;
        return;
    }
    const id = xml.getElementsByTagName('id')[0]?.textContent;
    const title = xml.getElementsByTagName('title')[0]?.textContent;
    const author = xml.getElementsByTagName('author')[0]?.textContent;
    const price = xml.getElementsByTagName('price')[0]?.textContent;
    document.getElementById('parsed').innerHTML =
        '<h3>Parsed Book Data</h3>' +
        '<p>ID: ' + id + '</p>' +
        '<p>Title: ' + title + '</p>' +
        '<p>Author: ' + author + '</p>' +
        '<p>Price: ' + price + '</p>';
}
</script>
<%
    }
%>
</body>
</html>

