<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages.SocialMedia" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home</title>

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>

<div class="app-layout">

  <c:set var="activePage" value="home" scope="request"/>
  <jsp:include page="partials/sidebar.jsp"/>

  <main class="main-content">

    <div class="feed-wrapper">

      <section class="feed-header">
        <h1><fmt:message key="home.title"/></h1>
        <p><fmt:message key="home.subtitle"/></p>
      </section>

      <!-- Create post box -->
      <section class="create-post-card">
        <form action="${pageContext.request.contextPath}/PostServlet" method="post">
          <textarea name="content" placeholder="<fmt:message key="home.placeholder"/>" required></textarea>
          <div class="create-post-actions">
            <button type="submit"><fmt:message key="home.postButton"/></button>
          </div>
        </form>
      </section>

      <section class="feed">

        <c:if test="${empty posts}">
          <div class="empty-feed">
            <h2><fmt:message key="home.noPostTitle"/></h2>
            <p><fmt:message key="home.noPostSubtitle"/></p>
          </div>
        </c:if>

        <c:forEach var="post" items="${posts}">
          <article class="post-card">

            <div class="post-header">
              <div>
                <h3>${post.username}</h3>
                <span>${post.createdAt}</span>
              </div>
            </div>

            <c:if test="${not empty post.content}">
              <p class="caption">
                  ${post.content}
              </p>
            </c:if>

            <c:if test="${not empty post.fileName}">
              <img class="post-image"
                   src="${pageContext.request.contextPath}/uploads/${post.fileName}"
                   alt="User uploaded picture">
            </c:if>

          </article>
        </c:forEach>

      </section>

    </div>

  </main>

</div>

</body>
</html>