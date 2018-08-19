(function ( $ ) {
    $(document).ready(function() {
        $.ajax({
            method:"GET",
            url: "rest/user/all",
            success: function (data) {
                console.log(data);
            }
        })
    });

});

