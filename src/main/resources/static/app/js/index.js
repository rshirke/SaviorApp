/**
 * Author : Rohan Shirke
 */
$(function () {
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken"
//    var $notLoggedIn = $("#notLoggedIn");
//    var $loggedIn = $("#loggedIn").hide();
//    var $response = $("#response");
//    var $login = $("#login");
//    var $userInfo = $("#userInfo").hide();

    var $login = $("#login");
    var $postlogin = $("#postlogin").hide();
    var $bodyx = $("#bodyx");
    var $body1 = $("#body1").hide();
    var $footer = $("#footer");
    

    // FUNCTIONS =============================================================
    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }

    function doLogin(loginData) {
        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
            	//user successfully logged in !!
                setJwtToken(data.token);
                $bodyx.hide();
                $body1.show();
                $login.hide();
                $postlogin.show();
                $footer.hide();
                //$notLoggedIn.hide();
                //showTokenInformation()
                //showUserInformation();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    } 
    
    
    function searchBanks(searchData) {
        $.ajax({
            url: "/bloodbanks/getallbb1",
            type: "POST",
            data: JSON.stringify(searchData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
            	//data returned from the server about the banks!!
                //need to draw a table of blood banks
            	
            	var $table1 = $userInfo.find("#table1");

            	$table1.append($("<table>")); 
            	$table1.append($("<tr><th>Bank Name</th><th>quantity</th><th>address</th></tr>"));
            	
                var $bloodbanksList = $("");
                data.bbLists.forEach(function (bloodbank) {
                	$bloodbanksList.append($("<tr>"));
                	$bloodbanksList.append($("<td>").text(bloodbank.bbName));
                	$bloodbanksList.append($("</td>"));
                	$bloodbanksList.append($("<td>").text(bloodbank.quantity));
                	$bloodbanksList.append($("</td>"));
                	$bloodbanksList.append($("<td>").text(bloodbank.address));
                	$bloodbanksList.append($("</td>"));
                	$bloodbanksList.append($("</tr>"));
                });
                
                var $tablefinal = $("</table>");
                $bloodbanksList.append($tablefinal);
                $table1.append($bloodbanksList);
            	
                
                ///rly we need to put the map and put it in the page
            	
//            	setJwtToken(data.token);
//                $bodyx.hide();
//                $body1.show();
//                $login.hide();
//                $postlogin.show();
//                $footer.hide();
                //$notLoggedIn.hide();
                //showTokenInformation()
                //showUserInformation();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }

    function doLogout() {
        removeJwtToken();
        $login.show();
        $userInfo
            .hide()
            .find("#userInfoBody").empty();
        $loggedIn
            .hide()
            .attr("title", "")
            .empty();
        $notLoggedIn.show();
    }

    function createAuthorizationTokenHeader() {
        var token = getJwtToken();
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }

    function showUserInformation() {
        $.ajax({
            url: "/user",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                var $userInfoBody = $userInfo.find("#userInfoBody");

                $userInfoBody.append($("<div>").text("Username: " + data.username));
                $userInfoBody.append($("<div>").text("Email: " + data.email));

                var $authorityList = $("<ul>");
                data.authorities.forEach(function (authorityItem) {
                    $authorityList.append($("<li>").text(authorityItem.authority));
                });
                var $authorities = $("<div>").text("Authorities:");
                $authorities.append($authorityList);

                $userInfoBody.append($authorities);
                $userInfo.show();
            }
        });
    }

    function showTokenInformation() {
        $loggedIn
            .text("Token: " + getJwtToken())
            .attr("title", "Token: " + getJwtToken())
            .show();
    }

    function showResponse(statusCode, message) {
        $response
            .empty()
            .text("status code: " + statusCode + "\n-------------------------\n" + message);
    }

    // REGISTER EVENT LISTENERS =============================================================
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });
    
    $("#searchBanks").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var searchData = {
            bloodGroup: $form.find('input[name="bgroup"]').val(),
            bloodQuantity: $form.find('input[name="bquantity"]').val()
        };

        searchBanks(searchData);
    });

    $("#logoutButton").click(doLogout);

    $("#exampleServiceBtn").click(function () {
        $.ajax({
            url: "/persons",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            	//showResponse(jqXHR.status, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#adminServiceBtn").click(function () {
        $.ajax({
            url: "/protected",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $loggedIn.click(function () {
        $loggedIn
                .toggleClass("text-hidden")
                .toggleClass("text-shown");
    });

    // INITIAL CALLS =============================================================
    if (getJwtToken()) {
        $login.hide();
        $notLoggedIn.hide();
        showTokenInformation();
        showUserInformation();
    }
});