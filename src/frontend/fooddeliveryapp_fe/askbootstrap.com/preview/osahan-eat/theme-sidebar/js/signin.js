$(document).ready(function(){

    $("#btn-signin").click(function(){
        var data = {
            username: $("#inputEmail").val(),
            password: $("#inputPassword").val()
        }
        $.ajax({  
            method: "POST",
            url: "http://localhost:8080/api/signin",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function( result ) {
            if (result.success) {
                window.location.href = "index.html";
            } else {
                alert("Sign-in failed")
            }
        });
    })
    
    
    
})