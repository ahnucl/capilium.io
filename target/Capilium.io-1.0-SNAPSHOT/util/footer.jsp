        <script src="webjars/jquery/3.4.0/jquery.min.js"></script>
        <script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="js/masked.input.js" type="text/javascript"></script>
        <script >
            cpfOptions = {
                onKeyPress: function (val, e, field, options) {
                    field.mask('000.000.000-00'.apply({}, arguments), options);
                }
            };
            $('.mascara-cpf').mask('000.000.000-00', cpfOptions);
        </script>
</html>
