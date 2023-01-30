
$(document).ready(function() {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://foodappapi-env.eba-7inbxjki.ap-northeast-1.elasticbeanstalk.com/restaurant/t6",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            if(data.length > 0) {
                $("#titleRestaurant").removeClass("d-none")
                $("#seeAllRestaurant").removeClass("d-none")
            }
            data.forEach(item => {
                $("#restaurants").append(`
                    <a href="detail.html" itemId=${item.id} class="text-dark text-decoration-none col-xl-4 col-lg-12 col-md-12 restaurantItem">
                        <div class="bg-white shadow-sm rounded d-flex align-items-center p-1 mb-4 osahan-list">
                            <div class="bg-light p-3 rounded">
                                <img src="img/restaurant/${item.image}" class="img-fluid" />
                            </div>
                            <div class="mx-3 py-2 w-100">
                                <p class="mb-2 text-black">${item.name}</p>
                                <p class="small mb-2">
                                    <i class="mdi mdi-star text-warning mr-1"></i>
                                    <span class="font-weight-bold text-dark">${item.rating}</span>
                                </p>
                                <p class="mb-0 text-muted d-flex align-items-center">
                                    <span class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i> Free
                                        delivery</span>
                                    <span class="small ml-auto">${item.nationality}</span>
                                </p>
                            </div>
                        </div>
                    </a>
                `)
            })
        }
    })

    $(document).on("click", "#seeAllRestaurant", function() {
        $("#restaurants").empty();
        let accessToken = getCookie("access-token");
        $.ajax({
            method: "GET",
            url: "http://foodappapi-env.eba-7inbxjki.ap-northeast-1.elasticbeanstalk.com/restaurant/all",
            headers: { "Authorization": "Bearer " + accessToken },
            success: function (response) {
                let data = response.data;
                data.forEach(item => { //
                    $("#restaurants").append(`
                        <a href="detail.html" itemId=${item.id} class="text-dark text-decoration-none col-xl-4 col-lg-12 col-md-12 restaurantItem">
                            <div class="bg-white shadow-sm rounded d-flex align-items-center p-1 mb-4 osahan-list">
                                <div class="bg-light p-3 rounded">
                                    <img src="img/restaurant/${item.image}" class="img-fluid" />
                                </div>
                                <div class="mx-3 py-2 w-100">
                                    <p class="mb-2 text-black">${item.name}</p>
                                    <p class="small mb-2">
                                        <i class="mdi mdi-star text-warning mr-1"></i>
                                        <span class="font-weight-bold text-dark">${item.rating}</span>
                                    </p>
                                    <p class="mb-0 text-muted d-flex align-items-center">
                                        <span class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i> Free
                                            delivery</span>
                                        <span class="small ml-auto">${item.nationality}</span>
                                    </p>
                                </div>
                            </div>
                        </a>
                    `)
                })
            }
        })
    });
    $(document).on("click", ".restaurantItem", function() {
        // console.log($(this).itemId)
        setCookie("viewRestaurantId",$(this).attr("itemId"))
        setCookie("viewResCatId",1)
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
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
