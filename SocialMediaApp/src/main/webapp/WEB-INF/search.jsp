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

  <aside class="sidebar">
    <div class="logo">SocialApp</div>

    <nav class="menu">
      <a href="${pageContext.request.contextPath}/HomeServlet"><fmt:message key="home.home"/></a>
      <a href="${pageContext.request.contextPath}/messages"><fmt:message key="home.messages"/></a>
      <a href="${pageContext.request.contextPath}/ProfileServlet"><fmt:message key="home.profile"/></a>
      <a href="${pageContext.request.contextPath}/SearchServlet" class="active"><fmt:message key="home.search"/></a>
      <a href="${pageContext.request.contextPath}/LogoutServlet" class="logout"><fmt:message key="home.logout"/></a>
    </nav>
  </aside>

  <main class="main-content">

    <div class="feed-wrapper">

      <section class="feed-header">
        <h1><fmt:message key="search.title"/></h1>
        <p><fmt:message key="search.subtitle"/></p>
      </section>

      <section class="create-post-card">

        <form action="${pageContext.request.contextPath}/SearchServlet"
              method="post">

          <input
                  type="text"
                  id="username"
                  name="username"
                  class="search-input"
                  placeholder="<fmt:message key='search.placeholder'/>">

          <div class="create-post-actions">
            <button type="submit">
              <fmt:message key="search.button"/>
            </button>
          </div>

        </form>

      </section>

      <section class="feed">
         <!-- For the ajax response !-->
        <div id="searchResults"></div>
        <!-- for the non ajax response !-->
        <c:if test="${not empty users}">

          <c:forEach var="searchedUser" items="${users}">

            <article class="post-card">

              <div class="post-header">

                <div class="profile-picture-small">

                  <c:choose>

                    <c:when test="${not empty searchedUser.profilePicture}">
                      <img
                              src="${pageContext.request.contextPath}/uploads/${searchedUser.profilePicture}"
                              alt="Profile Picture">
                    </c:when>

                    <c:otherwise>
                      ${searchedUser.username.substring(0,1)}
                    </c:otherwise>

                  </c:choose>

                </div>

                <div>
                  <h3>${searchedUser.username}</h3>
                </div>

              </div>

              <form action="${pageContext.request.contextPath}/FriendServlet"
                    method="post">

                <input
                        type="hidden"
                        name="receiverId"
                        value="${searchedUser.userID}">

                <button type="submit">
                  <fmt:message key="search.addFriend"/>
                </button>

              </form>

            </article>

          </c:forEach>

        </c:if>

        <c:if test="${empty users && not empty param.username}">
          <div class="empty-feed">
            <h2><fmt:message key="search.noResultTitle"/></h2>
            <p><fmt:message key="search.noResultSubtitle"/></p>
          </div>
        </c:if>
        <!-- end of non ajax response -->
      </section>

    </div>

  </main>

</div>

</body>
<script>const ContextPath="${pageContext.request.contextPath}"</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/search.js" defer></script>
</html>