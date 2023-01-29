const areas = ["Asian", "America"];

$(document).ready(function() {
    areas.forEach(area => {
        callApiAndRender(area);
    })
})

function callApiAndRender(area) {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://localhost:8481/food/t6/" + area,
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            data.forEach(item => {
                $("#food" + area).append(`
                    <a href="#" class="text-decoration-none col-xl-4 col-md-4 mb-4" data-toggle="modal"
                    data-target="#myitemsModal">
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