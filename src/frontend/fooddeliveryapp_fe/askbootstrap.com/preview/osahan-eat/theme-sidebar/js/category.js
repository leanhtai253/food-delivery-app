const iconColors = ["bg-danger", "bg-primary", "bg-warning"];


$(document).ready(function () {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com/categories/t6",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            let data = response.data;
            if(data.length > 0) {
                $("#titleCategories").removeClass("d-none");
                $("#seeAllCategories").removeClass("d-none");
            }
            for(let i = 0; i < data.length; i++) {
                $("#categories").append(`
                <a href="listing.html" class="text-decoration-none col-xl-2 col-md-4 mb-4">
                    <div class="rounded py-4 bg-white shadow-sm text-center">
                        <i class="mdi ${data[i].image} ${iconColors[i%3]} text-white osahan-icon mx-auto rounded-pill"></i>
                        <h6 class="mb-1 mt-3">${data[i].name}</h6>
                        <p class="mb-0 small">${data[i].count}+ options</p>
                    </div>
                </a>
                `)
            }
        }
    })

    $(document).on("click", "#seeAllCategories", function() {
        $("#categories").empty();
        let accessToken = getCookie("access-token");
        $.ajax({
            method: "GET",
            url: "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com/categories/all",
            headers: { "Authorization": "Bearer " + accessToken },
            success: function (response) {
                let data = response.data;
                for(let i = 0; i < data.length; i++) {
                    $("#categories").append(`
                    <a href="listing.html" class="text-decoration-none col-xl-2 col-md-4 mb-4">
                        <div class="rounded py-4 bg-white shadow-sm text-center">
                            <i class="mdi ${data[i].image} ${iconColors[i%3]} text-white osahan-icon mx-auto rounded-pill"></i>
                            <h6 class="mb-1 mt-3">${data[i].name}</h6>
                            <p class="mb-0 small">${data[i].count}+ options</p>
                        </div>
                    </a>
                    `)
                }
            }
        })
    });
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
