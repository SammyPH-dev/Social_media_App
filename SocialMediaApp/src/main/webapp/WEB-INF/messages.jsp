<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages.SocialMedia" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Messages</title>
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="app-layout">
            <!--side bar -->
            <c:set var="activePage" value="messages" scope="request"/>
            <jsp:include page="partials/sidebar.jsp"/>
            <!-- Contenu principal -->
            <main class="main-content">
                <div class="message-wrapper">
                    <!-- titre et sous titre -->
                    <section class="feed-header">
                        <h1><fmt:message key="messages.title"/> </h1>
                        <p><fmt:message key="messages.subtitle"/> </p>
                    </section>
                    <!-- layout de la liste d'amis et des messages -->
                    <div class="message-layout">
                        <!--List d'amis -->
                        <aside class="friend-list">
                            <!--si pas d'amis -->
                            <h2><fmt:message key="messages.friends"/></h2>
                            <c:if test="${empty friends}">
                                <p class="muted-text"><fmt:message key="messages.noFriends"/> </p>
                            </c:if>
                            <!--Si a amis -->
                            <c:forEach var="friend" items="${friends}">
                                <button class="friend-button" type="button" onclick="selectFriend(${friend.userID},'${friend.username}')">
                                    <span class="friend-avatar">${friend.username.substring(0, 1)}</span>
                                    <span>${friend.username}</span>
                                </button>
                            </c:forEach>
                        </aside>
                        <!-- Parti de conversation -->
                        <section class="chat-panel">
                            <div class="chat-header">
                                <h2 id="selectedFriendName"><fmt:message key="messages.selectFriend"/> </h2>
                            </div>
                            <!-- La ou le ajax va inserer les conversation -->
                            <div class="conversation" id="conversation"></div>
                            <!-- boutton pour envoyer un message -->
                                <form id="messageForm" class="message-form">
                                    <input type="hidden" id="receiverId" name="receiverId">
                                    <input type="text"
                                           id="messageInput"
                                           name="content"
                                           placeholder="<fmt:message key='messages.placeholder'/>"
                                           autocomplete="off">
                                    <button type="submit">
                                        <fmt:message key="messages.send"/>
                                    </button>
                                </form>
                        </section>
                    </div>
                </div>
            </main>
        </div>
        <script>const ContextPath = "${pageContext.request.contextPath}";</script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/messages.js"></script>
    </body>
</html>
