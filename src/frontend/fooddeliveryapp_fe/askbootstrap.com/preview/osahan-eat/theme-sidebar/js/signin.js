$(document).ready(function(){

    $("#btn-signin").click(function(){
        var data = {
            email: $("#inputEmail").val(),
            password: $("#inputPassword").val()
        }
        $.ajax({  
            method: "POST",
            url: "http://localhost:8481/authen/sign-in",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            error: function(response) {
                error = response.responseJSON
                console.log(error.message)
                if (Array.isArray(error.message)) {
                    error.message.forEach(element => {
                        notify(element);
                    });
                } else {
                    notify(error.message);
                }
            }
        }).done(function( result ) {
            if (result.success) {
                window.location.href = "index.html";
            } else {
                alert("Sign-in failed")
            }
        });
    })
    $(document).on('click','.notify',function() {
        $(this).slideUp('fast',function(){$(this).remove();});
    });
    
})
function notify(msg) {
    $('<p/>').appendTo('#notify').addClass('notify').addClass('text-center').html(msg).slideDown();
    // $("#notify").append(`
    //     <p class="text-center", class="notify">
    //         <a class="text-dark">${msg}</a>
    //     </p>
    // `);
}