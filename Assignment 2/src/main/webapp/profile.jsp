<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>My Profile</title>
            <link rel="stylesheet" href="css/style.css">
        </head>

        <body>
            <header>
                <div class="container">
                    <div id="branding">
                        <h1><span class="highlight">Fashion</span> Store</h1>
                    </div>
                    <nav>
                        <ul>
                            <li><a href="index.jsp">Home</a></li>
                            <li><a href="shop.jsp">Shop</a></li>
                            <li class="current"><a href="profile.jsp">Profile</a></li>
                            <li><a href="cart.jsp">Cart</a></li>
                            <li><a href="auth?action=logout">Logout</a></li>
                        </ul>
                    </nav>
                </div>
            </header>

            <section class="container form-wrap">
                <h1>Edit Profile</h1>
                <p style="color:green">${param.msg}</p>
                <p style="color:red">${param.error}</p>
                <form action="profile" method="post">
                    <input type="hidden" name="action" value="update">
                    <label>Name</label>
                    <input type="text" name="username" value="${sessionScope.user.username}" required>
                    <label>Email</label>
                    <input type="email" name="email" value="${sessionScope.user.email}" required>
                    <label>Phone</label>
                    <input type="text" name="phone" value="${sessionScope.user.phone}">
                    <label>Address</label>
                    <textarea name="address">${sessionScope.user.address}</textarea>
                    <label>City</label>
                    <input type="text" name="city" value="${sessionScope.user.city}">
                    <label>State</label>
                    <input type="text" name="state" value="${sessionScope.user.state}">
                    <label>Country</label>
                    <input type="text" name="country" value="${sessionScope.user.country}">
                    <label>PIN</label>
                    <input type="text" name="pin" value="${sessionScope.user.pin}">

                    <button type="submit">Update Profile</button>
                </form>
            </section>
        </body>

        </html>