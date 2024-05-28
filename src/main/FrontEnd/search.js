$(document).on("click", "#butt", function () {
    let inputs = form.getElementsByTagName('input');
    for (let input of inputs) {
        if (input.checked) {
            var name = $("#name").val();
            $.ajax({
                type: 'POST',
                url: "file:///C:/Users/Daria/Documents/Pizza.html",
                data: {
                    "name": name
                },
                success: function (data) {
                    $(".answer").html(data);
                    console.log(data);
                    var html = '<div>';
                    $.each(data, function (index, value) {
                        html += '<h1>';
                        html += '<p>' + value.name + '</p>';
                        html += '<a href=' + value.mameHTML +/>';
                        html += '</h1>';
                        html += '</div>';
                        $('div').html(html);
                    });
                },
                error: function (data) {
                    console.log(data);
                    alert(data.responseJSON.message);
                }
            });
            return false;
        }
    }
});