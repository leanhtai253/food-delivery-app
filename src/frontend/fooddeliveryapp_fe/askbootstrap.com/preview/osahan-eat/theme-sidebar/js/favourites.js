$(document).ready(function() {
    let hostFav = "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com"
    showRestaurantFav();
    

    $(document).on("click", "#fav-food-btn", function() {
        $(this).attr("aria-selected","true");
        $("#fav-res-btn").attr("aria-selected","false");
        $(this).removeClass("bg-light text-dark").addClass("bg-primary text-white");
        $("#fav-res-btn").removeClass("bg-primary text-white").addClass("bg-light text-dark");
        showRestaurantFav();
    });

    $(document).on("click", "#fav-res-btn", function() {
        $(this).attr("aria-selected","true");
        $("#fav-food-btn").attr("aria-selected","false");
        $(this).removeClass("bg-light text-dark").addClass("bg-primary text-white");
        $("#fav-food-btn").removeClass("bg-primary text-white").addClass("bg-light text-dark");
        showDishesFav();
    });

    $(document).on("click", ".restaurantItem", function() {
        // console.log($(this).itemId)
        setCookie("viewRestaurantId",$(this).attr("restaurantId"))
        setCookie("viewResCatId",1)
    })

    $(document).on("click",".foodItem", function(){
        console.log($(this).attr("foodItem"))
        foodItemId = parseInt($(this).attr("foodItem"))
        $.ajax({
            method: "GET",
            url: hostFav + `/food/${foodItemId}`,
            headers: {"Authorization": "Bearer " + getCookie("access-token")},
        success: function(resp){
            let data = resp.data
            $("#addOnList").empty()
            data.forEach((item)=>{
                $("#foodDetailName").text(item.name)
                $("#foodDetailDesc").text(item.description)
                $("#foodDetailImg").attr("src",`img/food/${item.image}`)
                $("#addToCartBtn").attr("foodItem",item.id)
                $("#addToCartBtn").text(`Add (${item.price} 000 VND)`)
                $.get("templateAddOn.html", function(content){
                    item.foodAddOn.forEach((addon) => {
                        templateAddOn = $(content)
                        templateAddOn.html(`
                        <input type="checkbox" class="custom-control-input" id="addOn${addon.id}">
                        <label class="custom-control-label font-weight-bold text-dark" for="addOn${addon.id}">${addon.name}</label>`)
                        $("#addOnList").append(templateAddOn)
                    })
                    
                })
            })
        }
        })
    })
})

function showRestaurantFav() {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com/favourites/restaurant/show",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            $("#contentFav").empty();
            data.forEach(item => {
                var restaurant = item.restaurantDTO
                $("#contentFav").append(`
                    <a restaurantId=${item.idRestaurant} href="detail.html" class="restaurantItem text-dark text-decoration-none col-xl-4 col-lg-12 col-md-12">
                        <div class="bg-white shadow-sm rounded d-flex align-items-center p-1 mb-4 osahan-list">
                            <div class="bg-light p-3 rounded">
                                <img src="img/restaurant/${restaurant.image}" class="img-fluid" />
                            </div>
                            <div class="mx-3 py-2 w-100">
                                <p class="mb-2 text-black">${restaurant.name}</p>
                                <p class="small mb-2">
                                    <i class="mdi mdi-star text-warning mr-1"></i>
                                    <span class="font-weight-bold text-dark">${restaurant.rating}</span>
                                </p>
                                <p class="mb-0 text-muted d-flex align-items-center">
                                    <span class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i> Free
                                        delivery</span>
                                    <span class="small ml-auto">${restaurant.nationality}</span>
                                </p>
                            </div>
                        </div>
                    </a>
                `)
            })
        }
    })
}

function showDishesFav() {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com/favourites/food/show",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            $("#contentFav").empty();
            data.forEach(item => {
                var food = item.foodDTO;
                $("#contentFav").append(`
                    <a foodItem=${item.idFood} href="#" class="foodItem text-decoration-none col-xl-4 col-md-4 mb-4" data-toggle="modal"
                    data-target="#foodDetailModal">
                        <img src="img/food/${food.image}" class="img-fluid rounded" />
                        <div class="d-flex align-items-center mt-3">
                            <p class="text-black h6 m-0">${food.name}</p>
                            <span class="badge badge-light ml-auto"><i class="mdi mdi-truck-fast-outline"></i> Free
                                delivery</span>
                        </div>
                    </a>
                `)
            })
        }
    })
}

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