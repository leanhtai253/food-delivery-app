const areas = ["Asian", "America"];

$(document).ready(function() {
    let hostFood = "http://foodappapi-env.eba-7inbxjki.ap-northeast-1.elasticbeanstalk.com"
    areas.forEach(area => {
        callApiAndRender(area);
    })
    $(document).on("click",".foodItem", function(){
        console.log($(this).attr("foodItem"))
        foodItemId = parseInt($(this).attr("foodItem"))
        $.ajax({
            method: "GET",
            url: hostFood + `/food/${foodItemId}`,
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

function callApiAndRender(area) {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://foodappapi-env.eba-7inbxjki.ap-northeast-1.elasticbeanstalk.com/food/t6/" + area,
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            if(data.length > 0) {
                $("#title" + area + "Food").removeClass("d-none")
                $("#seeAll" + area + "Food").removeClass("d-none")
            }
            data.forEach(item => {
                $("#food" + area).append(`
                    <a foodItem=${item.id} href="#" class="foodItem text-decoration-none col-xl-4 col-md-4 mb-4" data-toggle="modal"
                    data-target="#foodDetailModal">
                        <img src="img/food/${item.image}" class="img-fluid rounded" />
                        <div class="d-flex align-items-center mt-3">
                            <p class="text-black h6 m-0">${item.name}</p>
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