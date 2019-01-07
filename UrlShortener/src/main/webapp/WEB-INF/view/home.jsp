<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>URL Shortener</title>
</head>
<body>
	<h1>My URL Shortener</h1>
	<form name='inputform' id='inputform'>
		URL : <input name='url' type='text' id="url"/>
		 <input type='button' id='send'
			value='Send'>
	</form>
	<br>
	<p>
		Short URL : <a href="" id="hlink"><span id="shorUrl"></span></a>
	</p>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		$('#send').click(function() {
			$.ajax({
				url : 'http://localhost:8080/get/url',
				type : 'post',
				data : $('form').serialize(),
				success : function(data) {
					$('#shorUrl').text(data);
					$("#hlink").prop("href", data)
				}
			})
		});

		$('input[type="text"]').keydown(function() {
		    if (event.keyCode === 13) {
		        event.preventDefault();
		        $.ajax({
					url : 'http://localhost:8080/get/url',
					type : 'post',
					data : $('form').serialize(),
					success : function(data) {
						$('#shorUrl').text(data);
					}
				})
		    }
		});
	</script>
	<br>
	<a href="/login"><button type="button">login</button></a>
</body>
</html>