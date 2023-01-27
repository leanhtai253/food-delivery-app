
$(document).ready(function() {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://localhost:8481/restaurant/t6",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            data.forEach(item => {
                $("#restaurants").append(`
                    <a href="detail.html" class="text-dark text-decoration-none col-xl-4 col-lg-12 col-md-12">
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