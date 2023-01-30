
$(document).ready(function () {
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com/order/upcoming",
        headers: { "Authorization": "Bearer " + accessToken },
        success: function (response) {
            console.log(response);
            let data = response.data;
            if(data.length > 0) {
                $("#titleOrderUpComing").removeClass("d-none");
                $("#filterOrderUpComing").removeClass("d-none");
            }
            $("#orderUpComing").empty();
            data.forEach(item => {
                var date = new Date(item.estimateShip)
                console.log(date);
                var dateFormat = date.getDate() + "/" + (date.getMonth() + 1)  + "/" + date.getFullYear()
                $("#orderUpComing").append(`
                    <div class="col-md-6 col-lg-4">
                        <div class="bg-white shadow-sm p-3 rounded mb-4">
                            <div class="d-flex align-items-center mb-3">
                                <p class="font-weight-bold text-black mb-0">#ORDER${item.id}</p>
                                <p class="badge badge-light mb-0 ml-auto"><i class="mdi ${item.status == "On the way" ? "mdi-map-clock" : "mdi-timelapse"}"></i> ${item.status}</p>
                            </div>
                            <div class="d-flex align-items-center mb-4">
                                <div>
                                    <p class="mb-0 bg-light rounded p-2 osahan-icon"><i
                                            class="mdi mdi-clock-outline"></i></p>
                                </div>
                                <div class="ml-3">
                                    <p class="mb-0">Estimated arrival</p>
                                    <p class="font-weight-bold mb-0 text-dark h5">${dateFormat}</p>
                                </div>
                                <div class="ml-auto">
                                    <a class="btn btn-primary text-white" data-toggle="modal"
                                        data-target="#trackModal">Track</a>
                                </div>
                            </div>
                            <div class="row m-0">
                                <div class="col-2 p-0">
                                    <div class="progress osahan-progress">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: 100%"
                                            aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                                            data-toggle="tooltip" data-placement="top" title="Order Confirmed">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-4 px-1">
                                    <div class="progress osahan-progress">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: 100%"
                                            aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                                            data-toggle="tooltip" data-placement="top" title="Order Packed"></div>
                                    </div>
                                </div>
                                <div class="col-6 p-0">
                                    <div class="progress osahan-progress">
                                        <div class="progress-bar" role="progressbar" style="width: 50%"
                                            aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"
                                            data-toggle="tooltip" data-placement="top" title="On the way"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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