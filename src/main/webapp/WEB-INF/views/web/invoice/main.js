$(document).ready(function (){
    $('.js-example-basic').select2();

    $('#deviceSelect').select2({
        placeholder: 'Tìm kiếm hoa...',
        allowClear: true,
        minimumInputLength: 1,
        ajax: {
            url: '${searchFlower}',
            dataType: 'json',
            delay: 250,
            data: function (params) {
                let query = {
                    keyword: params.term
                }
                return query;
            },
            processResults: function (data) {
                let options = data.map(function (item) {
                    return {
                        text: item.name,
                        id: item.id
                    };
                });
                return {
                    results: options
                };
            },
            cache: true
        }
    });
})