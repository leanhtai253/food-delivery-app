$(document).ready(function(){
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://localhost:8481/settings/showUserInfo",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        headers: {"Authorization": "Bearer " + accessToken},
        success: function(data) {
            console.log(data);
            let avatar = data.avatar;
            let fullName = data.fullName;
            let email = data.email;
            let phoneNumber =  data.phoneNumber;
            $("#avatar").text(avatar);
            $("#fullName").text(fullName);
            $("#email").text(email);
            $("#phoneNumber").text(phoneNumber);
        },
    })
    $.ajax({
        method: "POST",
        url: "http://localhost:8481/settings/updateUserInfo",
        headers: {"Authorization": "Bearer " + accessToken},
        success: function(response) {

        }

    });
})
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
