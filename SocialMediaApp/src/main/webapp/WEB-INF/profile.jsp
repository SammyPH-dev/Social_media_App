<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages.SocialMedia" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home</title>

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>

<div class="app-layout">

  <c:set var="activePage" value="profile" scope="request"/>
  <jsp:include page="partials/sidebar.jsp"/>

  <main class="main-content">

    <div class="feed-wrapper">

      <section class="profile-card">

        <div class="profile-cover"></div>

        <div class="profile-info">

          <div class="profile-picture">
            <c:choose>
              <c:when test="${not empty user.profilePicture}">
                <img src="${pageContext.request.contextPath}/ImageServlet?file=${user.profilePicture}"
                     alt="Profile picture">
              </c:when>

              <c:otherwise>
                <span>${user.username.substring(0, 1)}</span>
              </c:otherwise>
            </c:choose>
          </div>

          <div class="profile-details">
            <h1>${user.username}</h1>
            <p><fmt:message key="profile.subtitle"/></p>
          </div>

        </div>

        <form action="${pageContext.request.contextPath}/ProfilePictureServlet"
              method="post"
              class="profile-picture-form"
              enctype="multipart/form-data">

          <input type="file" name="profilePicture" accept="image/*">

          <button class="profile-picture-button" type="submit"><fmt:message key="profile.pictureButton"/></button>
        </form>

      </section>

      <section class="feed-header">
        <h2><fmt:message key="profile.posts"/></h2>
        <p><fmt:message key="profile.postsSubtitle"/></p>
      </section>

      <section class="feed">

        <c:if test="${empty posts}">
          <div class="empty-feed">
            <h2><fmt:message key="profile.noPostTitle"/></h2>
            <p><fmt:message key="profile.noPostSubtitle"/></p>
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