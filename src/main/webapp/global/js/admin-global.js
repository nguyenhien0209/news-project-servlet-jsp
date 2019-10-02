$(document).ready(function () {
    enableOrDisableDeleteAll();
    autoCheckBoxChil();
    autoCheckBoxParent();
});

function enableOrDisableDeleteAll() {
    $('input[type=checkbox]').click(function () {
        if($('input[type=checkbox]:checked').length > 0) {
            $('#btnDelete').prop('disabled', false);
        } else {
            $('#btnDelete').prop('disabled', true);
        }
    });
}

function autoCheckBoxChil() {
    $('#checkAll').change(function () {
        if((this).checked) {
            $(this).closest('table').find('tbody input[type=checkbox]').prop('checked', true);
        } else {
            $(this).closest('table').find('tbody input[type=checkbox]').prop('checked', false);
            $('#btnDelete').prop('disabled', true);
        }
    });
}

function autoCheckBoxParent() {
    var totalCheckBoxChild = $('#checkAll').closest('table').find('tbody input[type=checkbox]').length;
    //$('#checkAll').closest('table').find('tbody input[type=checkbox]').each(function () {
    $('#checkAll').closest('table').find('tbody input[type=checkbox]').on('change', function () {
        var totalCheckboxChecked = $('#checkAll').closest('table').find('tbody input[type=checkbox]:checked').length;
        if(totalCheckboxChecked == totalCheckBoxChild) {
            $('#checkAll').prop('checked', true);
        } else {
            $('#checkAll').prop('checked', false);
        }
    })
    //})
}