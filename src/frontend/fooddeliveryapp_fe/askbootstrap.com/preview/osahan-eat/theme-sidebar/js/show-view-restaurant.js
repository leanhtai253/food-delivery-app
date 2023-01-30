let hostResDetail = "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com"
$(document).ready(function(){
    if (getCookie("viewRestaurantId").length > 0) {
        restaurantId = getCookie("viewRestaurantId");
        categoryId = getCookie("viewResCatId");
        apiGetFoodsByCategory(parseInt(categoryId), parseInt(restaurantId))
        $.ajax({
            method: "GET",
            url: hostResDetail + "/restaurant" + `/view/${restaurantId}`,
            headers: {"Authorization": "Bearer " + getCookie("access-token")},
            success: function(resp){
                let data = resp.data
                $("#restaurantName").text(data.name)
                $("#restaurantDesc").text(data.description)
                $("#restaurantRating").text(data.rating)
                $("#restaurantImg").attr("src", `img/restaurant/${data.image}`)
            }
        })
        $.ajax({
            method: "GET",
            url: hostResDetail + "/categories" + `/${restaurantId}`,
            headers: {"Authorization": "Bearer " + getCookie("access-token")},
            success: function(resp){
                let data = resp.data
                console.log("Categories: ")
                console.log(data)
                console.log(typeof(categoryId))
                data.forEach(async (item) => {
                    console.log(typeof(item.id))
                    if (item.id.toString() === categoryId) {
                        $("#restaurantCategories").append(`
                            <li class="nav-item mr-2" role="presentation">
                                <a categoryId=${item.id} class="nav-link border-0 btn btn-light active" id="categoryTabItem" data-toggle="tab"
                                    href="#popular" role="tab" aria-controls="popular" aria-selected="true">${item.name}</a>
                            </li>`)
                    } else {
                        $("#restaurantCategories").append(`
                        <li class="nav-item mr-2" role="presentation">
                            <a categoryId=${item.id} class="nav-link border-0 btn btn-light" id="categoryTabItem" data-toggle="tab"
                                href="#popular" role="tab" aria-controls="popular" aria-selected="true">${item.name}</a>
                        </li>`)}
                })
            }
        })
        $(document).on("click", "#categoryTabItem", function(data) {
            setCookie("viewResCatId", $(this).attr("categoryId"))
            location.reload()
        })
        $(document).on("click",".foodItem", function(){
            console.log($(this).attr("foodItem"))
            foodItemId = parseInt($(this).attr("foodItem"))
            $.ajax({
                method: "GET",
                url: hostResDetail + `/food/${foodItemId}`,
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
    }
})

async function apiGetFoodsByCategory(idCat, idRes) {
    $.ajax({
        method: "GET",
        url: hostResDetail + `/food/${idCat}/${idRes}`,
        headers: {"Authorization": "Bearer " + getCookie("access-token")},
    }).done( function(resp) {
        let data = resp.data
        console.log(data)
        data.forEach((item)=>{
            $("#foodTabPane").append(`
            <div class="row foodsByCat">
                                    <a foodItem=${item.number} href="#" class="foodItem text-decoration-none text-dark col-lg-3 col-md-6 mb-4"
                                        data-toggle="modal" data-target="#foodDetailModal">
                                        <img src="img/food/${item.image}" class="img-fluid rounded">
                                        <div class="d-flex align-items-center mt-3 mb-2">
                                            <p class="text-black h6 m-0">${item.name}</p>
                                            <span class="badge badge-success ml-auto"><i
                                                    class="mdi mdi-cash"></i>${item.price} 000 VND</span>
                                        </div>
                                        <p class="small mb-2"><i class="mdi mdi-star text-warning"></i> <span
                                                class="font-weight-bold text-dark ml-1">${item.rating}</span>(1,873) <i
                                                class="mdi mdi-silverware-fork-knife ml-2 mr-1"></i> ${item.category} <i
                                                class="mdi mdi-motorbike ml-2 mr-2"></i>45
                                            - 55 min
                                        </p>
                                    </a>
                                </div>
            `)
        })
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
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
