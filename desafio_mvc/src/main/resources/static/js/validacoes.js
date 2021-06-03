$("#estado").change(function() {

	let municipio = $('#municipio');
	municipio.empty();

	const url = '/home/bairro//listarPorEstado?codigoEstado=' + $("#estado").val();

	$.getJSON(url, function(data) {
		$.each(data, function(key, objeto) {
			municipio.append($('<option></option>').attr('value', objeto.codigo).text(objeto.nomeMunicipio));
		})
	});
})

validaMunicipio = () => {
	let estado = $('#estado').val();
	if (estado == null)
		$('#validaMunicipio').modal('show');
}

validaBairro = () => {

	let estado = $('#estado').val();
	let municipio = $('#municipio').val();


	if (estado == null) {
		$('#validaBairro').modal('show', function(event) {
			var modal = $(this);
			modal.find('.modal-body span').html('Cadastre um <strong>Estado</strong> primeiro');
		});
	}else if (municipio == null) {
	   $('#validaBairro').modal('show', function(event) {
		   var modal = $(this);
		   modal.find('.modal-body span').html('Cadastre um <strong>Município</strong> primeiro');
	   });
   }
   
}

