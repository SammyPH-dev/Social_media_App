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

  <c:set var="activePage" value="search" scope="request"/>
  <jsp:include page="partials/sidebar.jsp"/>

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