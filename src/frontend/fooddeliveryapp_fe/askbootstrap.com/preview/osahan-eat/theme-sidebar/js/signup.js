$(document).ready(function () {

    $("#btn-signup").click(function(){
        var data = {
            fullName: $("#fullname").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }
        $.ajax({
            method: "POST",
            url: "http://foodappapi-env.eba-7inbxjki.ap-northeast-1.elasticbeanstalk.com/authen/sign-up",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
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
        }).done(function (result) {
            if(result.success){
                //chuyen page signin
                window.location.href = "signin.html";
            }else{
                //thong bao that bai
                alert("sign-up failed")
            }
        });
    })
    $(document).on('click','.notify',function() {
        $(this).slideUp('fast',function(){$(this).remove();});
    });
})
function notify(msg) {
    $('<p/>').appendTo('#notify').addClass('notify').addClass('text-center').html(msg).slideDown();
}