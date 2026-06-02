<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages.SocialMedia" />
<aside class="sidebar">
    <div class="logo">SocialApp</div>

    <nav class="menu">
        <a href="${pageContext.request.contextPath}/HomeServlet" class="${activePage == 'home' ? 'active' : ''}"><fmt:message key="home.home"/></a>
        <a href="${pageContext.request.contextPath}/MessageServlet" class="${activePage == 'messages' ? 'active' : ''}"><fmt:message key="home.messages"/></a>
        <a href="${pageContext.request.contextPath}/ProfileServlet" class="${activePage == 'profile' ? 'active' : ''}"><fmt:message key="home.profile"/></a>
        <a href="${pageContext.request.contextPath}/SearchServlet" class="${activePage == 'search' ? 'active' : ''}"><fmt:message key="home.search"/></a>
        <a href="${pageContext.request.contextPath}/LogoutServlet" class="logout"><fmt:message key="home.logout"/></a>
    </nav>
</aside>
