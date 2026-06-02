let selectedFriendID = null;

function selectFriend(FriendID, username){
    selectedFriendID = FriendID;
    $("#receiverId").val(FriendID);
    $("#selectedFriendName").text(username);
    loadMessages();
}

function loadMessages(){
    console.log("loadMessages called", selectedFriendID);
    if(selectedFriendID == null){
        return;
    }
    $.getJSON(ContextPath +"/MessageJSONServlet",
        {
            friendID: selectedFriendID,
        }, function(data){
                let html = "";
                $.each(data, function(index, message){
                    html += `
                    <div class="message">
                        <strong>${message.senderUsername}</strong>
                        <p>${message.content}</p>
                        <span>${message.sentAt}</span>
                    </div>`;
                });
            console.log("html generated:", html);
                $("#conversation").html(html);
                $("#conversation").scrollTop($("#conversation")[0].scrollHeight);
        }).fail(function(textStatus, error) {
        console.log("AJAX FAILED");
        console.log("Status:", textStatus);
        console.log("Error:", error);})
}

$(document).ready(function(){
    $("#messageForm").submit(function(e){
        e.preventDefault();
        let content = $("#messageInput").val();
        if(selectedFriendID == null){
            return;
        }
        $.post(
          ContextPath +"/MessageServlet", {
              receiverId: selectedFriendID,
                content: content
            }, function(data){
                $("#messageInput").val("");
                loadMessages();
            }
        );
    });
    setInterval(loadMessages,2000);
});