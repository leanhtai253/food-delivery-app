$(document).ready(function () {
    let accessToken = getCookie("access-token");
    $("#fav-food-btn").click(function(){
        $.ajax({
            method: "GET",
            url:"http://localhost:8481/favourites/food/show",
            data: "",
            contentType: "application/json; charset=utf-8",
            error: function(response){
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
        }).done(function(result){
            if(result.success){
                window.location.href = "favourites.html"
            }
        });
    })
    
    $("#fav-res-btn").click(function(){
        $.ajax({
            method: "GET",
            url:"http://localhost:8481/favourites/restaurant/show",
            data: "",
            contentType: "application/json; charset=utf-8",
            error: function(response){
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
        }).done(function(result){
           let da = result.data;
           let index = 1;
           $.get("templateSearchList.html",function(content){
            let showList = $(content)
            da.forEach(function(el){
                showList.append(`
                    <div class="bg-light p-3 rounded">
                        <img src="img/restaurant/${el.imageRes}" class="img-fluid">
                    </div>
                    <div class="mx-3 py-2 w-100">
                        <p class="mb-2 text-black">${el.nameRes}</p>
                        <p class="small mb-2">
                            <i class="mdi mdi-star text-warning mr-1"></i> <span
                                class="font-weight-bold text-dark">${el.ratingRes}</span> (${da.length})
                            <i class="mdi mdi-silverware-fork-knife ml-3 mr-1"></i> ${el.cateRes}
                            <i class="mdi mdi-currency-inr ml-3"></i> 340/-
                        </p>
                        <p class="mb-0 text-muted d-flex align-items-center"><span
                                class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i>
                                Free delivery</span>
                            <span class="small ml-auto"><i class="mdi mdi-map-marker"></i> 1.5
                                km</span>
                        </p>
                    </div>
                    `)
                $("#showItem").html(showList)
                })
                index += 1;
            });
        })
    })

    $("#fav-food-icon").click(function(){
        var data = {
            idUser: $("#inputIdUser").val(),
            idFood: $("#inputIdFood").val(),
        }
        $.ajax({
            method: "POST",
            url:"http://localhost:8481/favourites/food/modify",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            error: function(response){
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
        }).done(function(result){
            if(result.success){
                window.location.href = "favourites.html"
            }
        });
    })


    $("#fav-res-icon").click(function(){
        var data = {
            idUser: $("#inputIdUser").val(),
            idRestaurant: $("#inputIdRestaurant").val(),
        }
        $.ajax({
            method: "POST",
            url:"http://localhost:8481/favourites/restaurant/modify",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            error: function(response){
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
        }).done(function(result){
            if(result.success){
                window.location.href = "favourites.html"
            }
        });
    })








})

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
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