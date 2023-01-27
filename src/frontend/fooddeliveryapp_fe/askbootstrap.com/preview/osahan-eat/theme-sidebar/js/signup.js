$(document).ready(function () {
    //qua trang signup.html sửa href ="#" và thêm id="btn-signup"
    $("#btn-signup").click(function(){
        var data = {
            //qua trang signup.html thêm id vào cho 2 ô input email và password
            fullName: $("#fullname").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }
        console.log(data)
        //call API raw json
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/authen/sign-up",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function (result) {
            if(result.success){
                //chuyen page signin
                window.location.href = "signin.html";
            }else{
                //thong bao that bai
                alert(result.description)
            }
        });
    })
})