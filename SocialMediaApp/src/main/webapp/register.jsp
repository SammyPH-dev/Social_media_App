<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages.SocialMedia" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login (Updated)</title>
  <link rel="stylesheet" href="css/login.css">
</head>
<body>

<div class="login-card">

  <h1><fmt:message key="register.title"/></h1>
  <p><fmt:message key="register.subtitle"/></p>

  <form action="RegisterServlet" method="post">

    <div class="form-group">
      <label for="username"><fmt:message key="register.username"/></label>
      <input type="text" id="username" name="username" required>
    </div>

    <div class="form-group">
      <label for="password"><fmt:message key="register.password"/></label>
      <input type="password" id="password" name="password" required>
    </div>

    <button type="submit" class="login-btn"><fmt:message key="register.button"/></button>

  </form>

  <div class="links">
    <a href="${pageContext.request.contextPath}/LanguageServlet"><fmt:message key="register.login"/></a>
  </div>

  <c:if test="${not empty error}">
    <br>
    <div class="error-message">${error}</div>
  </c:if>
</div>
</body>
</html>