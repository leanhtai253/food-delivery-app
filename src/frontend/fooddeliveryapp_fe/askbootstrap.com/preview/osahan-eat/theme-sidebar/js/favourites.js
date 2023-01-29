$(document).ready(function() {

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
})

function showRestaurantFav() {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://localhost:8481/favourites/restaurant/show",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            $("#contentFav").empty();
            data.forEach(item => {
                var restaurant = item.restaurantDTO
                $("#contentFav").append(`
                    <a href="detail.html" class="text-dark text-decoration-none col-xl-4 col-lg-12 col-md-12">
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
        url: "http://localhost:8481/favourites/food/show",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            $("#contentFav").empty();
            data.forEach(item => {
                var food = item.foodDTO;
                $("#contentFav").append(`
                    <a href="#" class="text-decoration-none col-xl-4 col-md-4 mb-4" data-toggle="modal"
                    data-target="#myitemsModal">
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