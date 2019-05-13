<script src="js/masked.input.js" type="text/javascript"></script>
<script >
    cpfOptions = {
        onKeyPress: function (val, e, field, options) {
            field.mask('000.000.000-00'.apply({}, arguments), options);
        }
    };
    foneOptions = {
        onKeyPress: function (val, e, field, options) {
            field.mask('(00) 00000-0000'.apply({}, arguments), options);
        }
    };
    $('.mascara-fone').mask('(00) 00000-0000', foneOptions);
    $('.mascara-cpf').mask('000.000.000-00', cpfOptions);
</script>
