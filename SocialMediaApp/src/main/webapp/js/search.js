$(document).ready(function () {
   $("#username").on("input", function () {
      $.getJSON(ContextPath+"/SearchJSON",{
         username: $("#username").val(),
      },function (data) {
         let html=""
         $.each(data, function (index, user) {
            html+=`
            <article class="post-card">
               <div class="post-header">
                    <div class="profile-picture-small">
                   ${
                   user.profilePicture
                       ? `<img src="${ContextPath}/uploads/${user.profilePicture}"
                                   alt="Profile Picture">`
                       : user.username.charAt(0).toUpperCase()}
                    </div>
                  <h3>${user.username}</h3>
               </div>
               <form action="${ContextPath}/FriendServlet" method="post">
                  <input type="hidden" name="receiverId" value="${user.userID}">
                     <button type="submit">Add Friend</button>
               </form>
            </article>`;
         })
         $("#searchResults").html(html);
      })
   });
});