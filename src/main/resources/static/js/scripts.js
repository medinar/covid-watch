$(document).ready(function () {

    $.fn.digits = function () {
        return this.each(function () {
            $(this).text($(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
        })
    }

    $('.covidDataTable').DataTable({
        "order": [2, 'desc']
    });

    $(".digits").digits();
    
});