<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
      <a href="${pageContext.request.contextPath}/home" class="active">Home</a>
      <a href="${pageContext.request.contextPath}/messages">Messages</a>
      <a href="${pageContext.request.contextPath}/profile">Profile</a>
      <a href="${pageContext.request.contextPath}/search">Search</a>
      <a href="${pageContext.request.contextPath}/LogoutServlet" class="logout">Logout</a>
    </nav>
  </aside>

  <main class="main-content">

    <div class="feed-wrapper">

      <section class="feed-header">
        <h1>Home</h1>
        <p>See what your friends are sharing.</p>
      </section>

      <!-- Create post box -->
      <section class="create-post-card">
        <form action="${pageContext.request.contextPath}/createPost"
              method="post">

                    <textarea
                            name="content"
                            placeholder="What's on your mind?"
                            required></textarea>

          <div class="create-post-actions">
            <button type="submit">Post</button>
          </div>

        </form>
      </section>

      <section class="feed">

        <c:if test="${empty posts}">
          <div class="empty-feed">
            <h2>No posts yet</h2>
            <p>Add friends or create a post to start seeing content.</p>
          </div>
        </c:if>

        <c:forEach var="post" items="${posts}">
          <article class="post-card">

            <div class="post-header">
              <div class="avatar">
                  ${post.username.substring(0, 1)}
              </div>

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