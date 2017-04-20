$(document).ready(function() {
	var $computerName = $('#computerName');
	var $introduced = $('#introduced');
	var $discontinued = $('#discontinued');
	var $submit = $('#submit');

	var $regexName = /^[a-zA-Z0-9 ]*$/;
	var $nameOK = false;

	$computerName.keyup(function() {

		var $name = $(this).val();

		if (!$regexName.test($name) || $name.length < 5) {
			$(this).css({
				borderColor : 'red',
				color : 'red'
			});
			$nameOK = false;
		} else {
			$(this).css({
				borderColor : 'green',
				color : 'green'
			});
			$nameOK = true;
		}
	});

	$submit.click(function(e) {
		return $nameOK;
	})

});
