var DEFAULT_CONFIRM_MESSAGE = "Confirm?";

/**
 * 
 * @param {type} message
 * @param {type} f
 */
function doConfirm(message, f) {
    var $div = $('<div>');
    $div.append($('#modal-fragment').html());
    $div.find('.modal-body p').text(message);
    $('body').append($div);
    $div.find('.modal').modal('show').on('hidden.bs.modal', function () {
        $div.remove();
    });
    $div.find('.modal').find('.btn-primary').click(function(e) {
        e.preventDefault();
        f();
        $div.find('.modal').modal('hide');
    });
}

/**
 * 
 * @param {type} displayOnly
 * @param {type} size
 */
function doCreateRatings(displayOnly, size) {
    $('input.rating-loading').rating({
        displayOnly: displayOnly,
        size: size,
        step: displayOnly ? 0.5 : 1,
        emptyStar: '<i class="far fa-star text-success"></i>',
        filledStar: '<i class="fas fa-star text-success"></i>',
        showClear: false,
        showCaption: false
    });
}

/**
 * 
 */
function doConfigureFormValidation() {
    $.validate({
        modules: 'security',
        validateOnBlur: false,
        onElementValidate: function (valid, $el) {
            if (valid) {
                $($el.data('validation-error-msg-container')).closest('.form-group').addClass('d-none');
            } else {
                $($el.data('validation-error-msg-container')).closest('.form-group').removeClass('d-none');
            }
        }
    });
}

/**
 * 
 */
function doConfigureInputMasking() {
    $('[data-inputmask]').inputmask();
}